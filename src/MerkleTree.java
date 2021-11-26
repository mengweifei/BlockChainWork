import java.util.*;
import cn.hutool.crypto.SecureUtil;


public class MerkleTree {
    private MerkleTree left;        //left child
    private MerkleTree right;       //right child
    String root;

    public MerkleTree(){            //constructor
        this.left = null;
        this.right = null;
        this.root = "";
    }

    public MerkleTree(List txList){ //constructor based input-info list
        List<MerkleTree> MerkleTrees = new ArrayList<>();
        for(int i=0; i<txList.size(); i++){
            String temp = (String) txList.get(i);
            MerkleTree merkleTemp = new MerkleTree();
            merkleTemp.setRoot(SecureUtil.sha256(temp));
            MerkleTrees.add(merkleTemp);
        }
        while(MerkleTrees.size() != 1){
//            System.out.println(MerkleTrees);
//            for(int i=0; i<MerkleTrees.size(); i++)
//                System.out.print(MerkleTrees.get(i).getRoot() + "  ");
//            System.out.println();
            int length = MerkleTrees.size();
            int count = length / 2;
            int still = length % 2;
            while (count>0){
                MerkleTree temp = new MerkleTree();
                temp.setRoot( SecureUtil.sha256(MerkleTrees.get(0).getRoot() + MerkleTrees.get(1).getRoot()));
                temp.setLeft(MerkleTrees.get(0));
                MerkleTrees.remove(0);
                temp.setRight(MerkleTrees.get(0));
                MerkleTrees.remove(0);
                MerkleTrees.add(temp);
                count -=1;
            }
            if (still == 1){
                MerkleTrees.add(MerkleTrees.get(0));
                MerkleTrees.remove(0);
            }
        }
        this.left = MerkleTrees.get(0).left;
        this.right = MerkleTrees.get(0).right;
        this.root = MerkleTrees.get(0).root;

    }

    public void levelOrder(){           // 层次遍历Merkle Tree
        Queue<MerkleTree> queue = new LinkedList<MerkleTree>();
        queue.offer(this);
        while(queue.size()>0){
            //System.out.println(((LinkedList<MerkleTree>) queue).getFirst());
            if(((LinkedList<MerkleTree>) queue).getFirst().getLeft()!=null)
                queue.offer(((LinkedList<MerkleTree>) queue).getFirst().getLeft());
            if(((LinkedList<MerkleTree>) queue).getFirst().getRight()!=null)
                queue.offer(((LinkedList<MerkleTree>) queue).getFirst().getRight());
            queue.poll();
        }
    }

    public MerkleTree getLeft() {
        return left;
    }

    public MerkleTree getRight() {
        return right;
    }

    public String getRoot() {
        return root;
    }

    public void setLeft(MerkleTree left) {
        this.left = left;
    }

    public void setRight(MerkleTree right) {
        this.right = right;
    }

    public void setRoot(String root) {
        this.root = root;
    }


}

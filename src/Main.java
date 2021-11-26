import java.util.ArrayList;

import java.util.List;

/**

 * Created by andyfeng on 2017/12/20.

 */

public class Main {
        public static void main(String [] args) {

                List tempTxList = new ArrayList();
                int N=1000000;
                for (int i=0; i<N; i++){
                    tempTxList.add(i+"");
                }
                MerkleTree merkleTrees = new MerkleTree(tempTxList);
               // MyBloomFilter filter = new MyBloomFilter();
                //filter.init(tempTxList);
                //System.out.println(merkleTrees.getRoot());
                long startTime = System.currentTimeMillis(); //程序开始时间
                merkleTrees.levelOrder();
                long endTime = System.currentTimeMillis();
                System.out.println("程序运行时间： "+ (endTime - startTime)+ " ms");
                System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        }

}
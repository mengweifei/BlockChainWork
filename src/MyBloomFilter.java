import java.util.BitSet;
import java.util.List;

public class MyBloomFilter {

     //一个长度为10 亿的比特位
    private static final int DEFAULT_SIZE = 256 << 22;

     // 相当于构建n(n为数据个数)个不同的hash算法
    private static HashFunction[] functions;

     // 初始化布隆过滤器的 bitmap
    private static BitSet bitset = new BitSet(DEFAULT_SIZE);

    public static void init(List txList){
        functions = new HashFunction[txList.size()];
    }

      //添加数据  @param value 需要加入的值
    public static void add(String value) {
        if (value != null) {
            for (HashFunction f : functions) {
                //计算 hash 值并修改 bitmap 中相应位置为 true
                bitset.set(f.hash(value), true);
            }
        }
    }

    /**
     * 判断相应元素是否存在
     * @param value 需要判断的元素  * @return 结果
     */
    public static boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (HashFunction f : functions) {
            ret = bitset.get(f.hash(value));
            //一个 hash 函数返回 false 则跳出循环
            if (!ret) {
                break;
            }
        }
        return ret;
    }
}


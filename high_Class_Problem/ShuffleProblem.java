package high_Class_Problem;

import java.util.Scanner;

/*
 * 完美洗牌问题
 * 给你一个长度为2*n的数组[L1,L2,...Ln,R1,R2...Rn],要求空间时间复杂度为O(1)
 * 将变动后的数组以[R1,L1,R2,L2,...Rn,Ln]这种方式排列，时间复杂度最低可以变成O(nlog3(n))
 * 1.思想是循环替换
 * 2.长度为3^k-1的数组，循环起始点为1,3,9,...,3^(k-1)   k:0,1,2...
 */
public class ShuffleProblem {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        shuffle(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        System.out.println(sb.toString().trim());
    }
      
    //获得的新位置在数组中第几位
    public static int getNewIndex(int i, int len) {
        if (i <= len / 2)
            return 2 * i;
        else
            return 2 * (i - len/2) - 1;
    }
      
    public static void reverse(int[] arr, int L, int R) {
        while (L < R) {
            int tmp = arr[L];
            arr[L++] = arr[R];
            arr[R--] = tmp;
        }
    }
      
    public static void reversePart(int[] arr, int L, int M, int R) {
        reverse(arr, L, M);
        reverse(arr, M+1, R);
        reverse(arr, L, R);
    }
      
    /*
    下标连推，即多米诺骨牌效应，从某一位开始，使其顶掉新位置上的数，再让被顶掉
    的数去顶它的新位置上的数
    若数组长度为2*N == 3^k - 1, 则共有k个多米诺骨牌效应,且出发位置为1，3，9...3^(k-1)
    */
    public static void cycles(int[] arr, int start, int len, int k) {
        for(int i = 0, trigger = 1; i < k; i++, trigger *= 3) {
            int preVal = arr[start + trigger - 1];
            int cur = getNewIndex(trigger, len);
            while (cur != trigger) {
                int tmp = arr[start + cur - 1];
                arr[start + cur - 1] = preVal;
                preVal = tmp;
                cur = getNewIndex(cur, len);
            }
            arr[start + cur - 1] = preVal;
        }
    }
      
    public static void shuffle (int[] arr) {
    	System.out.println("arr.length="+arr.length+" arr.length&1="+(arr.length & 1));
        if(arr != null || arr.length != 0 || (arr.length%2) == 0)
            shuffle2(arr, 0, arr.length-1);
    }
      
    /*
    在arr[L...R]上做洗牌，整体策略为：
    假设共2*6=12个数，先在 L1, L2, ..., R3, R4 这8个数上做下标连推，
    然后分别在L5, R5；L6, R6 这两组数上做下标连推，目的是为了使每次做下标连推
    的子数组数量都满足3^k-1. 为了实现下标连推，则必须在之前实现翻转字符串
    比方说在第一次下标连推前，把L5, L6,..., R3, R4左部分设为L5, L6, 有部分设为
    R1..R4, 然后进行翻转
    */
    public static void shuffle2(int[] arr, int L, int R) {
        while(R - L + 1 > 0) {
            int len = R-L+1;
            int base = 3;
            int k = 1;
            //计算<=len且距离len最近的，满足(3^k-1)的数
            //也就是找到最大的k，满足3^k <= len+1
            while(base <= (len+1) / 3) {
                base *= 3;
                k++;
            }
            //当前要解决base-1块，一半就是再除以2
            int half = (base-1) / 2;
            //[L...R]的中点位置
            int mid = (L+R) / 2;
            //要旋转的左部分为[L+half...mid], 右部分为arr[mid+1...mid+half]
            //注意，这里arr下标是从0开始
            reversePart(arr, L + half, mid, mid + half);
            //旋转完成后，从L开始算起，长度为base-1的部分进行下标连续推
            cycles(arr, L, base-1, k);
            L += base - 1;
        }
    }
}

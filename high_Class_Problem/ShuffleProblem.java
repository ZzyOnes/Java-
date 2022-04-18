package high_Class_Problem;

import java.util.Scanner;

/*
 * ����ϴ������
 * ����һ������Ϊ2*n������[L1,L2,...Ln,R1,R2...Rn],Ҫ��ռ�ʱ�临�Ӷ�ΪO(1)
 * ���䶯���������[R1,L1,R2,L2,...Rn,Ln]���ַ�ʽ���У�ʱ�临�Ӷ���Ϳ��Ա��O(nlog3(n))
 * 1.˼����ѭ���滻
 * 2.����Ϊ3^k-1�����飬ѭ����ʼ��Ϊ1,3,9,...,3^(k-1)   k:0,1,2...
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
      
    //��õ���λ���������еڼ�λ
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
    �±����ƣ�������ŵ����ЧӦ����ĳһλ��ʼ��ʹ�䶥����λ���ϵ��������ñ�����
    ����ȥ��������λ���ϵ���
    �����鳤��Ϊ2*N == 3^k - 1, ����k������ŵ����ЧӦ,�ҳ���λ��Ϊ1��3��9...3^(k-1)
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
    ��arr[L...R]����ϴ�ƣ��������Ϊ��
    ���蹲2*6=12���������� L1, L2, ..., R3, R4 ��8���������±����ƣ�
    Ȼ��ֱ���L5, R5��L6, R6 �������������±����ƣ�Ŀ����Ϊ��ʹÿ�����±�����
    ������������������3^k-1. Ϊ��ʵ���±����ƣ��������֮ǰʵ�ַ�ת�ַ���
    �ȷ�˵�ڵ�һ���±�����ǰ����L5, L6,..., R3, R4�󲿷���ΪL5, L6, �в�����Ϊ
    R1..R4, Ȼ����з�ת
    */
    public static void shuffle2(int[] arr, int L, int R) {
        while(R - L + 1 > 0) {
            int len = R-L+1;
            int base = 3;
            int k = 1;
            //����<=len�Ҿ���len����ģ�����(3^k-1)����
            //Ҳ�����ҵ�����k������3^k <= len+1
            while(base <= (len+1) / 3) {
                base *= 3;
                k++;
            }
            //��ǰҪ���base-1�飬һ������ٳ���2
            int half = (base-1) / 2;
            //[L...R]���е�λ��
            int mid = (L+R) / 2;
            //Ҫ��ת���󲿷�Ϊ[L+half...mid], �Ҳ���Ϊarr[mid+1...mid+half]
            //ע�⣬����arr�±��Ǵ�0��ʼ
            reversePart(arr, L + half, mid, mid + half);
            //��ת��ɺ󣬴�L��ʼ���𣬳���Ϊbase-1�Ĳ��ֽ����±�������
            cycles(arr, L, base-1, k);
            L += base - 1;
        }
    }
}

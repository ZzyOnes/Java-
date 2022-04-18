package high_Class_Problem;

import java.util.HashMap;

/*
 * ����һ�����飬�ҵ������黮�ֳ�ŵ�ɶ�Ȼ��ʹ�ã�����Ϊ0�Ķ���࣬��������Ϊ0�Ķ���
 */
public class MostEOR {

	public static int mostEOR(int[] arr) {
		int xor = 0;
		//dp[i] ��ʾarr[0...i]ʱ���Ļ����ж��ٸ�
		int[] dp = new int[arr.length];
		//key :��0������ĳ��ǰ׺����
		//value :���ǰ׺���ͳ��ֵ�����λ��
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(0,-1);
		//��ʼ��dp[i],dp[0]Ĭ��Ϊ0
		for(int i=0;i<arr.length;i++) {
			xor ^= arr[i]; //��¼0...i�ϵ�����
			if(map.containsKey(xor)) {//���map�м�¼��������һ��������ڼ����һ������Ϊ0
				int pre = map.get(xor);//�õ�����λ��
				dp[i] = pre==-1 ? 1:(dp[pre]+1);//���pre==-1˵��arr[0]=0,
			}
			if(i>0) {
				dp[i] = Math.max(dp[i-1], dp[i]);//�Ƚ�����dp[i-1]��ֵȡ������һ��
			}
			map.put(xor,i);//����xor������λ��
		}
		return dp[arr.length-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

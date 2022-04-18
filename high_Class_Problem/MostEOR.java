package high_Class_Problem;

import java.util.HashMap;

/*
 * 给你一个数组，找到将数组划分成诺干段然后使得，异或和为0的段最多，返回异或和为0的段数
 */
public class MostEOR {

	public static int mostEOR(int[] arr) {
		int xor = 0;
		//dp[i] 表示arr[0...i]时最多的划分有多少个
		int[] dp = new int[arr.length];
		//key :从0出发的某个前缀异或和
		//value :这个前缀异或和出现的最晚位置
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(0,-1);
		//开始填dp[i],dp[0]默认为0
		for(int i=0;i<arr.length;i++) {
			xor ^= arr[i]; //记录0...i上的异或和
			if(map.containsKey(xor)) {//如果map有记录，表明第一种情况存在即最后一段异或和为0
				int pre = map.get(xor);//得到它的位置
				dp[i] = pre==-1 ? 1:(dp[pre]+1);//如果pre==-1说明arr[0]=0,
			}
			if(i>0) {
				dp[i] = Math.max(dp[i-1], dp[i]);//比较其与dp[i-1]的值取最大的那一个
			}
			map.put(xor,i);//更新xor的最新位置
		}
		return dp[arr.length-1];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

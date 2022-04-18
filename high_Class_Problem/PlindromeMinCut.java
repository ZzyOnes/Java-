package high_Class_Problem;
/*
 * 给你一个字符串，返回将这个字符串分成每个子串都是回文字符串的最少部分数
 */
public class PlindromeMinCut {
	
	public static int getMinCut(String str, boolean[][] dp) {
		if(str.length()==0||str==null) {
			return 0;
		}
		if(str.length()==1) {
			return 1;
		}
		return process(str.toCharArray(), 0,dp);
	}

	// 该递归返回str[i...]的最少部分数
	private static int process(char[] str, int i,boolean[][] dp) {
		if(i==str.length) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		//遍历每个str[i...end]是回文+process(str,end+1,dp)的情况
		for(int end=i;end<str.length;end++) {
			if(dp[i][end]) {
				ans = Math.min(ans,1+process(str,end+1,dp));
			}
		}
			return ans;
	}
	//验证s到e位置上是不是回文字符串  该方法时间负载度为O(n)可以优化
	//进行优化,建立一个dp[i][j]数组存放str中i到j是不是回文字符串
	public static boolean[][] valid(char[] str) {
		boolean[][] dp = new boolean[str.length][str.length];
		//初始化
		for(int i=0;i<dp.length;i++) {
			dp[i][i] = true;
			if(i+1<dp.length) {
				if(str[i]==str[i+1]) {
					dp[i][i+1] = true;
				}
			}
		}
		//填表
		for(int i=dp.length-3;i>=0;i--) {
			for(int j=dp.length-1;j>=i+2;j--) {
				if(str[i]==str[j]) {
					dp[i][j] = dp[i+1][j-1];
				}
			}
		}
		return dp;
	}
	public static void main(String[] args) {
		String testStr = "acdcdcdad";
		boolean[][] dp = valid(testStr.toCharArray());
		System.out.print(getMinCut(testStr,dp)-1);
	}

}

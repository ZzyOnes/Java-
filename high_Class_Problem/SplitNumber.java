package high_Class_Problem;
/*
 * 给你一个数，返回这个数裂开的方法数如：4=1+1+1+1 ; 1+1+2 ; 1+3 ; 2+2 ; 4+0 共5种裂开方案
 */
public class SplitNumber {
	
	public static int getWays(int n) {
		if(n<=0) {
			return 0;
		}else if(n==1) {
			return 1;
		}else {
			return process(1,n);
		}
	}
	//pre裂开的前一个部分
	//rest还剩多少值去裂开,裂开的第一个部分不能比pre小
	//返回裂开的方法数
	public static int process(int pre,int rest) {
		if(rest==0) {
			return 1;
		}
		if(pre>rest) {
			return 0;
		}
		int ways = 0;
		for(int i=pre;i<=rest;i++) { //i就是rest第1个裂开的部分值是多少
			ways += process(i,rest-i);
		}
		return ways;
	}

	//反法2直接暴力改动态规划  , dp[pre][rest]的值代表：还剩rest需要裂开其第一部分必须大于等于pre的方法数 
	public static int ways2(int n) {
		if(n<1) {
			return 0;
		}
		int[][] dp = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			dp[i][0] = 1;
		}
		//开始填表，从下往上，从左往右填
		for(int pre=n;pre>0;pre--) {
			for(int rest=pre;rest<=n;rest++) {
				//遍历过程可以进行斜率优化
				for(int i=pre;i<=rest;i++ ) {
					dp[pre][rest] += dp[i][rest-i];
				}
			}
		}
		return dp[1][n];
	}
	//进行斜率优化
	public static int ways3(int n) {
		if(n<1) {
			return 0;
		}
		int[][] dp = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			dp[i][0] = 1;
		}
		
		//开始填表，从下往上，从左往右填,由于会数组越界所以初始化多填一下
		for(int pre=n;pre>0;pre--) {
			for(int rest=pre;rest<=n;rest++) {
				if(pre==n) dp[pre][rest] = 1;
				else
				dp[pre][rest] = dp[pre+1][rest] + dp[pre][rest-pre];
			}
		}
		return dp[1][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test = 15;
		System.out.println(getWays(test)+"  "+ ways2(test)+"  "+ways3(test));
	}

}

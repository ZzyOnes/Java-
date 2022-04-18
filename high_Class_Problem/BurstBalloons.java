package high_Class_Problem;
/*
 * 给你一个数组代表每个位置上气球的得分
 * 假设打爆的气球位置为i其分数为X, 左边离其最近一个没爆的气球得分为Y,右边离其最近一个没爆的气球为Z
 * 那么其得分为：Y*X*Z 如果左边气球都打爆则得分为：X*Z 如果右边气球都打爆则得分为：Y*X
 * 如果两边气球都打爆则得分为：X
 * 求所有气球都打爆时最大的得分为多少  
 */
public class BurstBalloons {
	
	public static int maxCoins1(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int N = arr.length;
		int[] help = new int[N+2];
		help[0] = 1; help[N+1] = 1;
		for(int i=0;i<N;i++) {
			help[i+1] = arr[i];
		}
		return process(help,1,N);
	}

	//假设L-1和R+1的气球一定没爆
	//process(arr,L,R)返回打爆L到R上的所有气球时的最大得分
	public static int process(int[] arr, int L, int R) {
		if(L==R) {
			return arr[L]*arr[L-1]*arr[R+1];
		}
		int max = Math.max(arr[L]*arr[L-1]*arr[R+1]+process(arr,L+1,R),//最后打爆的是L位置
				           arr[R]*arr[L-1]*arr[R+1]+process(arr,L,R-1));//最后打爆的是R位置
		for(int i=L+1;i<R;i++) {//最后打爆的是i位置
			max = Math.max(max,arr[i]*arr[L-1]*arr[R+1]+process(arr,L,i-1)+process(arr,i+1,R));
		}
		return max;
	}
	
	//改动态规划
	public static int maxCoins2(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int N = arr.length;
		int[] help = new int[N+2];
		help[0] = 1; help[N+1] = 1;
		for(int i=0;i<N;i++) {
			help[i+1] = arr[i];
		}
		int[][] dp = new int[N][N];
		//初始化
		for(int i=0;i<N;i++) {
			dp[i][i] = help[i+1]*help[i]*help[i+2];
		}
		for(int L=N-2;L>=0;L--) {
			for(int R=L+1;R<=N-1;R++) {
				int max = Math.max(help[L+1]*help[L]*help[R+2]+dp[L+1][R],//最后打爆的是L位置
				           help[R+1]*help[L]*help[R+2]+dp[L][R-1]);//最后打爆的是R位置  
				for(int i=L+1;i<R;i++) {//最后打爆的是i位置
					max = Math.max(max,help[i+1]*help[L]*help[R+2]+dp[L][i-1]+dp[i+1][R]);
				}
				dp[L][R] = max;
			}
		}
		return dp[0][N-1];
	}

	public static void main(String[] args) {
		int[] test = {3,2,5,65};
		System.out.print(maxCoins1(test)+" "+maxCoins2(test));

	}

}

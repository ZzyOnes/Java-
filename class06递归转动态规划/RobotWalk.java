package class06递归转动态规划;
//先试出递归方法-->加缓存计划搜索把以算出的数结果保存下来-->分析缓存表找到表依赖进行动态规划设计


//一共1~N个位置
//开始位置S
//结束位置e
//机器人必须走的步数k

public class RobotWalk {
	
	public static int walkWay1(int N,int s,int e,int k) {
		return f1(N,e,k,s);
	}
//N个位置，结束位置E，还剩rest步，当前在cur位置，放回方法数
	public static int f1(int N,int E,int rest,int cur) {
		if(rest==0) {
			return cur==E ? 1:0;
		}
		//rest>0,还要继续走
		if(cur==1) {//必须往右走
			return f1(N,E,rest-1,2);
		}
		if(cur==N) {//必须往左走
			return f1(N,E,rest-1,N-1);
		}
		return f1(N,E,rest-1,cur-1)+f1(N,E,rest-1,cur+1);
		
	}
	
	
	//将walkWay2进行优化，加缓存  (计划搜索)
	public static int walkWay2(int N,int E,int S,int k) {
		int[][] dp = new int[k+1][N+1];//表示还剩i步，的第j个位置
		for(int i=0;i<=k;i++) {//将dp初始化
			for(int j=0;j<=N;j++) {
				dp[i][j] = -1;
			}
		}
		return f2(N,E,k,S,dp);
	}
	public static int f2(int N,int E,int rest,int cur,int[][] dp) {
		if(dp[rest][cur]!=-1) {//如果之前算过就直接返回之前的值不要再重新算了
			return dp[rest][cur];
		}
		//缓存没命中
		if(rest==0) {
			dp[rest][cur] = cur==E ? 1:0;//在return之前将答案记录在格子里再返回
			//return dp[rest][cur];
		}
		//rest>0,还要继续走
		else if(cur==1) {//必须往右走
			dp[rest][cur] = f2(N,E,rest-1,2,dp);//在return之前将答案记录在格子里再返回
			//return dp[rest][cur];
		}
		else if(cur==N) {//必须往左走
			dp[rest][cur] = f2(N,E,rest-1,N-1,dp);//在return之前将答案记录在格子里再返回
			//return dp[rest][cur];
		}
		else {
			dp[rest][cur] = f2(N,E,rest-1,cur-1,dp)+f2(N,E,rest-1,cur+1,dp);//在return之前将答案记录在格子里再返回
		}
		return dp[rest][cur];
	}
	
	
//动态规划进行优化,在f2方法中建表找到依赖进行求解。（不需要看题目，题目的逻辑已在递归实现。直接在优化后的方法进行动态规划）
	public static int dpWay(int N,int E,int S,int k) {
		int[][] dp = new int[k+1][N+1];//dp[][0]用不了
		dp[0][E] = 1;//还剩0步，在最终位置的方法为1
		for(int rest = 1;rest <= k;rest++) {
			for( int cur = 1;cur <= N;cur++) {
				if(cur == 1) {
					dp[rest][cur] = dp[rest-1][2];
				}
				else if(cur == N) {
					dp[rest][cur] = dp[rest-1][N-1];
				}
				else {
					dp[rest][cur] = dp[rest-1][cur-1]+dp[rest-1][cur+1];
				}
			}
		}
		return dp[k][S];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

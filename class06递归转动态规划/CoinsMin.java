package class06递归转动态规划;

public class CoinsMin {
	
	public static int minCoins1(int[] arr,int aim) {
		return process1(arr,0,aim);
	}
	//arr[index....]的硬币组成rest这么多钱，最少的硬币数量返回
	//return -1 表示这种方案凑不出aim   return 0 表示凑出了aim并返回
	public static int process1(int[] arr,int index,int rest ) {
		if(rest<0) {
			return -1;
		}
		if(rest == 0) {
			return 0;
		}
		//rest>0
		if(index == arr.length) {
			return -1;
		}
		int p1 = process1(arr,index+1,rest);//没选index号硬币
		int p2 = process1(arr,index+1,rest-arr[index]);//选了index号硬币的后续最少硬币数
		if(p1==-1&&p2==-1) {
			return -1;
		}else {
			if(p1==-1) {
				return p2+1;
			}
			if(p2==-1) {
				return p1;
			}
			return Math.min(p1,p2+1);
		}
		/*
		 * //rest>0,并且我也有硬币 return
		 * Math.min(process(arr,index+1,rest),1+process(arr,index+1,rest-arr[index]));
		 * -1会干扰结果就改成上面的方法
		 */
	}

	//优化，计划搜索  其中index和rest两个可变参数做一个二维表dp[][]
	//index:0~arr.length  rest<=aim
	public static int minCoins2(int[] arr,int aim) {
		int[][] dp = new int[arr.length+1][aim+1];
		for(int i=0;i<=arr.length;i++) {
			for(int j=0;j<=aim;j++) {
				dp[i][j] = -16384;//初始化，-16384表示这个状态没算过；-1表示算过无效解
			}
		}
		return process2(arr,0,aim,dp);
	}
	public static int process2(int[] arr,int index,int rest,int[][] dp ) {
		/*无法用dp表示rest<0
		 * if(rest<0) { return -1; }
		*/
		if(rest<0) { 
			return -1; 
		}
		if(dp[index][rest]!=-16384) {//该位置算过(优化将已算过的数直接返回)
			return dp[index][rest];
		}
		//返回之前记录在格子里
		if(rest == 0) {
			dp[index][rest] = 0;
			return 0;
		}
		//rest>0
		if(index == arr.length) {
			dp[index][rest] = -1;
			return -1;
		}
		int p1 = process2(arr,index+1,rest,dp);//没选index号硬币
		int p2 = process2(arr,index+1,rest-arr[index],dp);//选了index号硬币的后续最少硬币数
		if(p1==-1&&p2==-1) {
			dp[index][rest] = -1;
			//return dp[index][rest];
		}else {
			if(p1==-1) {
				dp[index][rest] = p2+1;
				//return dp[index][rest];
			}
			if(p2==-1) {
				dp[index][rest] = p1;
				//return dp[index][rest];
			}else {
				dp[index][rest] =  Math.min(p1,p2+1);
			}
		}
		return dp[index][rest];
	}
	
	//改成动态规划。分析dp搜索表找出依赖关系：当前位置(index,rest)的值依赖(index+1,rest)和(index+1,rest-arr[index])的值
	public static int minCoins3(int[] arr,int aim) {
		int N = arr.length;
		int[][] dp = new int[N+1][aim+1];
		for(int row=0;row<=N;row++) {//先初始化填值
			dp[row][0] = 0;
		}
		for(int col=1;col<=aim;col++) {//先初始化填值
			dp[N][col] = -1;
		}
		for(int index = N-1;index>=0;index--) {//以这样的方式填格子
			for(int rest=1;rest<=aim;rest++) {
				int p1 = dp[index+1][rest];//没选index号硬币
				int p2 = -1;//选了index号硬币的后续最少硬币数
				if(rest-arr[index]>=0) {//防止rest-arr[index]越界
					p2 = dp[index+1][rest-arr[index]];
				}
				if(p1==-1&&p2==-1) {
					dp[index][rest] = -1;
				}else {
					if(p1==-1) {
						dp[index][rest] = p2+1;
					}
					if(p2==-1) {
						dp[index][rest] = p1;
					}else {
						dp[index][rest] = Math.min(p1,p2+1);
					}
				}
			}
		}
		return dp[0][aim];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

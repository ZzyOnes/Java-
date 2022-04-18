package high_Class_Problem;
/*
 * 给你一个矩阵，元素有正有负。蛇从第一列任意位置出发初始长度为0，有三种走法：向右下，向右，向右上行走
 * 遇到正数蛇长度加上该值，遇到负数蛇长度减去该值
 * 蛇有一次能力，将负数变为正数，一但蛇的长度为负数则游戏结束
 * 问：一局游戏中蛇能够达到的最大长度是多少？
 */

public class SnakeGame {
	
	public static class Info{
		public int no; //不用能力
		public int yes; //用能力
		public Info(int n,int y) {
			this.no = n;
			this.yes = y;
		}
	}
	
	//方法一：暴力递归方法
	public static int snake1(int[][] matrix) {
		int ans = Integer.MIN_VALUE;
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Info cur = f1(matrix,i,j);
				ans = Math.max(ans, Math.max(cur.no, cur.yes));
			}
		}
		return ans;
	}
	
	//蛇到达(rwo,col)位置时的最大长度信息
	public static Info f1(int[][] Matrix, int row, int col) {
		if(col==0) {//初始情况
			return new Info(Matrix[row][col],-Matrix[row][col]);
		}
		int preNo = -1; //之前的旅程中没用能力的最大长度
		int preYes = -1; //之前的旅程中用了一次能力的最大长度
		//第一种情况从左上而来
		if(row>0) {
			Info leftUp = f1(Matrix,row-1,col-1);
			if(leftUp.no >= 0) {
				preNo = leftUp.no;
			}
			if(leftUp.yes >= 0) {
				preYes = leftUp.yes;
			}
		}
		//第二种情况从左而来
		Info left = f1(Matrix,row,col-1);
		if(left.no >= 0) {
			preNo = Math.max(preNo, left.no);
		}
		if(left.yes >= 0) {
			preYes = Math.max(preYes, left.yes);
		}
		//第三种情况从左下而来
		if(row<Matrix.length-1) {
			Info leftDown = f1(Matrix,row+1,col-1);
			if(leftDown.no >= 0) {
				preNo = Math.max(preNo, leftDown.no);
			}
			if(leftDown.yes >= 0) {
				preYes = Math.max(preYes, leftDown.yes);
			}
		}
		int yes = -1;
		int no = -1;
		//本层的信息
		if(preYes >= 0) {
			yes = preYes + Matrix[row][col];
		}
		if(preNo >= 0) {
			yes = Math.max(yes, preNo-Matrix[row][col]);
			no = preNo + Matrix[row][col];
		}
		return new Info(no,yes);
	}
	
	
	//方法二：在递归方法中加缓存，暴力改动态规划。记忆化搜索
	public static int snake2(int[][] matrix) {
		int ans = Integer.MIN_VALUE;
		Info[][] dp = new Info[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				Info cur = f2(matrix,i,j,dp);
				ans = Math.max(ans, Math.max(cur.no, cur.yes));
			}
		}
		return ans;
	}
	
	//蛇到达(rwo,col)位置时的最大长度信息
	public static Info f2(int[][] Matrix, int row, int col, Info[][] dp) {
		//如果dp[row][col]不为空，之前算过直接返回
		if(dp[row][col]!=null) {
			return dp[row][col];
		}
		
		if(col==0) {//初始情况
			dp[row][col] = new Info(Matrix[row][col],-Matrix[row][col]);//加缓存
			return dp[row][col];
		}
		int preNo = -1; //之前的旅程中没用能力的最大长度
		int preYes = -1; //之前的旅程中用了一次能力的最大长度
		//第一种情况从左上而来
		if(row>0) {
			Info leftUp = f2(Matrix,row-1,col-1,dp);
			if(leftUp.no >= 0) {
				preNo = leftUp.no;
			}
			if(leftUp.yes >= 0) {
				preYes = leftUp.yes;
			}
		}
		//第二种情况从左而来
		Info left = f2(Matrix,row,col-1,dp);
		if(left.no >= 0) {
			preNo = Math.max(preNo, left.no);
		}
		if(left.yes >= 0) {
			preYes = Math.max(preYes, left.yes);
		}
		//第三种情况从左下而来
		if(row<Matrix.length-1) {
			Info leftDown = f2(Matrix,row+1,col-1,dp);
			if(leftDown.no >= 0) {
				preNo = Math.max(preNo, leftDown.no);
			}
			if(leftDown.yes >= 0) {
				preYes = Math.max(preYes, leftDown.yes);
			}
		}
		int yes = -1;
		int no = -1;
		//本层的信息
		if(preYes >= 0) {
			yes = preYes + Matrix[row][col];
		}
		if(preNo >= 0) {
			yes = Math.max(yes, preNo-Matrix[row][col]);
			no = preNo + Matrix[row][col];
		}
		dp[row][col] = new Info(no,yes);
		return dp[row][col];
	}
	
	//第三种方法，动态规划
	public static int Process(int[][] matrix) {
		Info[][] dp = new Info[matrix.length][matrix[0].length];
		int ans = Integer.MIN_VALUE;
		//初始化，填第一列
		for(int row=0;row<dp.length;row++) {
			dp[row][0] = new Info(matrix[row][0],-matrix[row][0]);
			ans = Math.max(ans, Math.max(dp[row][0].no, dp[row][0].yes));
		}
		//开始填表
		for(int col=1;col<dp[0].length;col++) {
			for(int row=0;row<dp.length;row++) {
				if(row==0) {
					int preNo = -1;
					int preYes = -1;
					if(dp[row][col-1].no>=0) {//第一种情况
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row+1][col-1].no>=0) {//第二种情况
						preNo = Math.max(preNo, dp[row+1][col-1].no);
					}
					if(dp[row+1][col-1].yes>=0) {
						preYes = Math.max(preYes, dp[row+1][col-1].yes);
					}
					int no = -1;
					int yes = -1;
					if(preYes>=0) {
						yes = preYes + matrix[row][col];
					}
					if(preNo>=0) {
						yes = Math.max(yes, preNo-matrix[row][col]);
						no = preNo + matrix[row][col];
					}
					dp[row][col] = new Info(no,yes);
					ans = Math.max(ans, Math.max(dp[row][col].yes, dp[row][col].no));
				}
				else if(row==dp.length-1) {
					int preNo = -1;
					int preYes = -1;
					if(dp[row][col-1].no>=0) {//第一种情况
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row-1][col-1].no>=0) {//第二种情况
						preNo = Math.max(preNo, dp[row-1][col-1].no);
					}
					if(dp[row-1][col-1].yes>=0) {
						preYes = Math.max(preYes, dp[row-1][col-1].yes);
					}
					int no = -1;
					int yes = -1;
					if(preYes>=0) {
						yes = preYes + matrix[row][col];
					}
					if(preNo>=0) {
						yes = Math.max(yes, preNo-matrix[row][col]);
						no = preNo + matrix[row][col];
					}
					dp[row][col] = new Info(no,yes);
					ans = Math.max(ans, Math.max(dp[row][col].yes, dp[row][col].no));
				}else {
					int preNo = -1;
					int preYes = -1;
					if(dp[row][col-1].no>=0) {//第一种情况
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row+1][col-1].no>=0) {//第二种情况
						preNo = Math.max(preNo, dp[row+1][col-1].no);
					}
					if(dp[row+1][col-1].yes>=0) {
						preYes = Math.max(preYes, dp[row+1][col-1].yes);
					}
					if(dp[row-1][col-1].no>=0) {//第三种情况
						preNo = Math.max(preNo, dp[row-1][col-1].no);
					}
					if(dp[row-1][col-1].yes>=0) {
						preYes = Math.max(preYes, dp[row-1][col-1].yes);
					}
					int no = -1;
					int yes = -1;
					if(preYes>=0) {
						yes = preYes + matrix[row][col];
					}
					if(preNo>=0) {
						yes = Math.max(yes, preNo-matrix[row][col]);
						no = preNo + matrix[row][col];
					}
					dp[row][col] = new Info(no,yes);
					ans = Math.max(ans, Math.max(dp[row][col].yes, dp[row][col].no));
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {
				{-100,1000,10000},
				{-200,-2000,20000},
				{-300,-3000,30000},
		};
		System.out.println(snake2(matrix));
		System.out.println(Process(matrix));
	}

}

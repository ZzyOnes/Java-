package high_Class_Problem;
/*
 * ����һ������Ԫ�������и����ߴӵ�һ������λ�ó�����ʼ����Ϊ0���������߷��������£����ң�����������
 * ���������߳��ȼ��ϸ�ֵ�����������߳��ȼ�ȥ��ֵ
 * ����һ����������������Ϊ������һ���ߵĳ���Ϊ��������Ϸ����
 * �ʣ�һ����Ϸ�����ܹ��ﵽ����󳤶��Ƕ��٣�
 */

public class SnakeGame {
	
	public static class Info{
		public int no; //��������
		public int yes; //������
		public Info(int n,int y) {
			this.no = n;
			this.yes = y;
		}
	}
	
	//����һ�������ݹ鷽��
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
	
	//�ߵ���(rwo,col)λ��ʱ����󳤶���Ϣ
	public static Info f1(int[][] Matrix, int row, int col) {
		if(col==0) {//��ʼ���
			return new Info(Matrix[row][col],-Matrix[row][col]);
		}
		int preNo = -1; //֮ǰ���ó���û����������󳤶�
		int preYes = -1; //֮ǰ���ó�������һ����������󳤶�
		//��һ����������϶���
		if(row>0) {
			Info leftUp = f1(Matrix,row-1,col-1);
			if(leftUp.no >= 0) {
				preNo = leftUp.no;
			}
			if(leftUp.yes >= 0) {
				preYes = leftUp.yes;
			}
		}
		//�ڶ�������������
		Info left = f1(Matrix,row,col-1);
		if(left.no >= 0) {
			preNo = Math.max(preNo, left.no);
		}
		if(left.yes >= 0) {
			preYes = Math.max(preYes, left.yes);
		}
		//��������������¶���
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
		//�������Ϣ
		if(preYes >= 0) {
			yes = preYes + Matrix[row][col];
		}
		if(preNo >= 0) {
			yes = Math.max(yes, preNo-Matrix[row][col]);
			no = preNo + Matrix[row][col];
		}
		return new Info(no,yes);
	}
	
	
	//���������ڵݹ鷽���мӻ��棬�����Ķ�̬�滮�����仯����
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
	
	//�ߵ���(rwo,col)λ��ʱ����󳤶���Ϣ
	public static Info f2(int[][] Matrix, int row, int col, Info[][] dp) {
		//���dp[row][col]��Ϊ�գ�֮ǰ���ֱ�ӷ���
		if(dp[row][col]!=null) {
			return dp[row][col];
		}
		
		if(col==0) {//��ʼ���
			dp[row][col] = new Info(Matrix[row][col],-Matrix[row][col]);//�ӻ���
			return dp[row][col];
		}
		int preNo = -1; //֮ǰ���ó���û����������󳤶�
		int preYes = -1; //֮ǰ���ó�������һ����������󳤶�
		//��һ����������϶���
		if(row>0) {
			Info leftUp = f2(Matrix,row-1,col-1,dp);
			if(leftUp.no >= 0) {
				preNo = leftUp.no;
			}
			if(leftUp.yes >= 0) {
				preYes = leftUp.yes;
			}
		}
		//�ڶ�������������
		Info left = f2(Matrix,row,col-1,dp);
		if(left.no >= 0) {
			preNo = Math.max(preNo, left.no);
		}
		if(left.yes >= 0) {
			preYes = Math.max(preYes, left.yes);
		}
		//��������������¶���
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
		//�������Ϣ
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
	
	//�����ַ�������̬�滮
	public static int Process(int[][] matrix) {
		Info[][] dp = new Info[matrix.length][matrix[0].length];
		int ans = Integer.MIN_VALUE;
		//��ʼ�������һ��
		for(int row=0;row<dp.length;row++) {
			dp[row][0] = new Info(matrix[row][0],-matrix[row][0]);
			ans = Math.max(ans, Math.max(dp[row][0].no, dp[row][0].yes));
		}
		//��ʼ���
		for(int col=1;col<dp[0].length;col++) {
			for(int row=0;row<dp.length;row++) {
				if(row==0) {
					int preNo = -1;
					int preYes = -1;
					if(dp[row][col-1].no>=0) {//��һ�����
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row+1][col-1].no>=0) {//�ڶ������
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
					if(dp[row][col-1].no>=0) {//��һ�����
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row-1][col-1].no>=0) {//�ڶ������
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
					if(dp[row][col-1].no>=0) {//��һ�����
						preNo = dp[row][col-1].no;
					}
					if(dp[row][col-1].yes>=0) {
						preYes = dp[row][col-1].yes;
					}
					if(dp[row+1][col-1].no>=0) {//�ڶ������
						preNo = Math.max(preNo, dp[row+1][col-1].no);
					}
					if(dp[row+1][col-1].yes>=0) {
						preYes = Math.max(preYes, dp[row+1][col-1].yes);
					}
					if(dp[row-1][col-1].no>=0) {//���������
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

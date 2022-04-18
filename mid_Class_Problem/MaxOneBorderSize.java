package mid_Class_Problem;

import java.util.Scanner;

//找到二维数组中边界为一的最大正方形边长
public class MaxOneBorderSize {
	

	public static int maxAllOneBorder(int[][] m) {
		
		//预处理矩阵
		int N = m.length;
		int M = m[0].length;
		int[][] down = new int[N][M];//下方有多少个连续的一
	    int[][] right= new int[N][M];//右方有多少个连续的一
		for(int j=0;j<M;j++) {
			for(int i=N-1;i>=0;i--) {
				if(i==N-1) {
					down[i][j]=m[i][j]==1?1:0;
				}if(i<N-1) {
					if(m[i][j]==1) {
						down[i][j]=down[i+1][j]+1;
					}else {
						down[i][j]=0;
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=M-1;j>=0;j--) {
				if(j==M-1) {
					right[i][j]=m[i][j]==1?1:0;
				}if(j<M-1) {
					if(m[i][j]==1) {
						right[i][j]=right[i][j+1]+1;
					}else {
						right[i][j]=0;
					}
				}
			}
		}
		int max=1;
		for(int row=0;row<N;row++) {
			for(int col=0;col<M;col++) {
				int border=Math.min(N-row-1, M-col-1);
				if(border>=1) {
					for(int b=1;b<=border;b++) {
						if(down[row][col]>=b&&right[row][col]>=b&&down[col][col+b]>=b&&right[row+b][col]>=b) {
							max=b>max?b:max;
						}
					}
				}
			}
		}
		return max+1;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] m = new int[5][5];
		Scanner sc = new Scanner(System.in);
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				int cur = sc.nextInt();
				m[i][j]=cur;
			}
		}
		int border = maxAllOneBorder(m);
		System.out.println(border);
	}

}

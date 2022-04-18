package mid_Class_Problem;

import java.util.Scanner;

/*
 * 根据公式   | F(n),F(n-1)| = |F(1),F(2)|*| 1  1 |^n-2  来求解斐波那契数列数列
 *大                                                                          | 1  0 |
 *  
 */
public class Fibonacci {

	public static int Fi(int n) {
		if(n<1) {
			return 0;
		}
		if(n==1||n==2) {
			return 1;
		}
		int[][] base = {
				{1,1},{1,0}
		};
		int[][] res = matrixPower(base,n-2);
		return res[0][0] + res[1][0];
	}
	
	public static int[][] matrixPower(int[][] matrix, int n){
		int[][] res = new int[matrix.length][matrix[0].length];
		for(int i=0;i<matrix.length;i++) {
			res[i][i]=1;//生成单位矩阵
		}
		int[][] temp = matrix;
		/*for(;n!=0;n>>=1) {
			if((n&1)!=0) {
				res = multiMatrix(res,temp);
			}
			temp = multiMatrix(temp,temp);
		}*/
		while(n!=0) {
			if(n%2!=0) {
				res = multiMatrix(res,temp);
			}
			temp = multiMatrix(temp,temp);
			n/=2;
		}
		return res;
	}

	public static int[][] multiMatrix(int[][] a, int[][] b) {
		int[][] ans = new int[a.length][b[0].length];
		for(int i=0;i<a.length;i++) {
			for(int k=0;k<b[0].length;k++) {
				for(int j=0;j<b.length;j++) {
					ans[i][k]+=a[i][j]*b[j][k];
				}
			}
		}
		return ans;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int res = Fi(n);
		System.out.println(res);
	}
}

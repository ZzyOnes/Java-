package class02;

import java.util.Scanner;

//将给定的正方形矩阵的元素旋转90°。
public class PrintMatrix {
	
	public static void Print(int[][] Matrix) {
		int tR=0;
		int tC=0;
		int dR=Matrix.length - 1;
		int dC=Matrix.length - 1;
		while(dR>tR) {
			rotateEdge(Matrix,tR++,tC++,dR--,dC--);
		}
		for(int i=0;i<Matrix.length;i++) {
			for(int j=0;j<Matrix.length;j++) {
				System.out.print(Matrix[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	
	public static void rotateEdge(int[][] m,int tR,int tC,int dR,int dC) {
		int times=dR-tR;
		int temp=0;
		for(int i=0;i<times;i++) {
			temp = m[dR-i][tC];
			m[dR-i][tC] = m[dR][dC-i];
			m[dR][dC-i] = m[tR+i][dC];
			m[tR+i][dC] = m[tR][tC+i];
			m[tR][tC+i] = temp;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		int [][]Matrix = new int[number][number];
		for(int i=0;i<number;i++) {
			for(int j=0;j<number;j++) {
				Matrix[i][j]=sc.nextInt();
			}
		}
		Print(Matrix);
	}

}

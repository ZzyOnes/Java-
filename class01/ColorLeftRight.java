package class01;

import java.util.Scanner;

//左侧应全为R,右侧应全为G
public class ColorLeftRight {

	public static int minPaint(String s) {
		char[] str = s.toCharArray();
		int n = str.length;
		//定义辅组数组统计0~L上G的个数,L+1~n上R的个数
		int numG=0,numR=0;
		int arrG[] = new int[n];
		int arrR[] = new int[n];
		for(int i=0;i<n;i++) {
			if(str[i]=='G') {
				numG++;
			}
			arrG[i]=numG;
		}
		for(int j=n-1;j>=0;j--) {
			if(str[j]=='R') {
				numR++;
			}
			arrR[j]=numR;
		}
		int min=n;
		for(int L=0;L<n;L++) {//枚举左侧部分的大小为L,右侧部分大小为n-L
			if(L==0) {
				//统计一共有多少个G,全染上R
				if(min>arrG[n-1])
				min = arrG[n-1];
			}
			if(L==n-1) {
				//统计一共有多少个R,全染上G
				if(min>arrR[0])
				min = arrR[0];
			}else {
				//统计str[0..L]一共有多少个G,全染上R；统计str[L+1..n]一共有多少个R，全染上G
				int temp = arrG[L]+arrR[L+1];
				if(min>temp)
					min = temp;
			}
		}	
		return min;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int min = minPaint(s);
		System.out.print(min);
	}

}

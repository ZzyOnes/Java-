package class01;

import java.util.Scanner;

//���ӦȫΪR,�Ҳ�ӦȫΪG
public class ColorLeftRight {

	public static int minPaint(String s) {
		char[] str = s.toCharArray();
		int n = str.length;
		//���帨������ͳ��0~L��G�ĸ���,L+1~n��R�ĸ���
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
		for(int L=0;L<n;L++) {//ö����ಿ�ֵĴ�СΪL,�Ҳಿ�ִ�СΪn-L
			if(L==0) {
				//ͳ��һ���ж��ٸ�G,ȫȾ��R
				if(min>arrG[n-1])
				min = arrG[n-1];
			}
			if(L==n-1) {
				//ͳ��һ���ж��ٸ�R,ȫȾ��G
				if(min>arrR[0])
				min = arrR[0];
			}else {
				//ͳ��str[0..L]һ���ж��ٸ�G,ȫȾ��R��ͳ��str[L+1..n]һ���ж��ٸ�R��ȫȾ��G
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

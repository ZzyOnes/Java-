package mid_Class_Problem;

import java.util.Scanner;

//给你一串字符串'.'代表需点灯'X'代表墙不能放灯，一盏灯能照亮3个位置，问：照亮所有的'.'需要几盏灯
public class Light {
	
	//贪心法
	public static int minLight(String s) {
		if(s==null||s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int cur=0;//满3个'.'放一盏灯 ans++ 或者 cur记录了超过一个'.'且下一个位置为'x' ans++。ans加完后cur变为0从新记录
		int ans=0;
		for(int i=0;i<str.length;i++) {
			if(str[i]=='.')cur++;
			if(str[i]=='x')cur=0;
			if(cur==3||(cur>=1&&(i+1==str.length||str[i+1]=='x'))) {
				ans++;
				cur=0;
			}
		}
		return ans;
	}

	//动态规划法
	public static int procees(String s) {
		if(s==null||s.equals("")) {
			return 0;
		}
		int n=s.length();
		int[] a = new int[n];
		char[] str = s.toCharArray();
		if(str[0]=='.') {
			a[0]=1;a[1]=1;
		}else {
			if(str[1]=='.') {
				a[0]=0;a[1]=1;
			}
		}
		for(int i=2;i<n;i++) {
			if(str[i]=='x') {
				a[i]=a[i-1];
			}
			if(str[i]=='.'&&str[i-1]=='x') {
				a[i]=a[i-2]+1;
			}
			if(str[i]=='.'&&str[i-1]=='.') {
				if(i-3>=0) {
					a[i]=a[i-3]+1;
				}
				else {
					a[i]=1;
				}
			}
		}
		return a[n-1];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int ans1 = minLight(s);
		int ans2 = procees(s);
		System.out.println(ans1+" "+ans2);
	}

}

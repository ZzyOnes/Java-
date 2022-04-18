package 组合数学;
import java.util.Scanner;
public class Permutation {
	static Scanner sc = new Scanner(System.in);
	static int n = sc.nextInt();
	static Num[] num1 = new Num[n];
	public static class Num{
		int num;
		int dir;
		public Num(int n,int d) {
			this.num = n;
			this.dir = d;
		}
	}
	public static void PrintNum(int n) {
		for(int i=0;i<n;i++) {
			System.out.print(num1[i].num);
		}
		System.out.println();
	}
	public static int FindMaxIndex(int n) {
		int Max = 0;
		int index = -1;
		for(int i=0;i<n;i++) {
			int neighbor = i+num1[i].dir;
			if(neighbor>=0&&neighbor<n) {
				if(num1[i].num>num1[neighbor].num&&num1[i].num>Max) {
					Max = num1[i].num;
					index = i;
				}
			}
		}
		return index;
	}
	public static void swap(int a,int b) {
		int temp = a;
		a = b;
		b = temp;
	}
	public static void swapNeig(int index) {
		int neighbor = num1[index].dir+index;
		int temp = num1[index].num;
		 num1[index].num = num1[neighbor].num; 
		 num1[neighbor].num = temp; 
		 int temp1 =num1[index].dir; 
		 num1[index].dir = num1[neighbor].dir; 
		 num1[neighbor].dir =temp1;
		/*
		 * swap(num1[index].num,num1[neighbor].num);
		 * swap(num1[index].dir,num1[neighbor].dir);
		 */
	}
	public static void changDir(int index,int n) {
		int Max = num1[index].num;
		for(int i=0;i<n;i++) {
			if(num1[i].num>Max) {
				num1[i].dir = num1[i].dir==-1? 1:-1;
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("请勿输入超过9的数");
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++) {
			num1[i] = new Num(i+1,-1);
		}
		int index = FindMaxIndex(n);
		int number=1;
		while(index>=0) {
			PrintNum(n);
			changDir(index,n);
			swapNeig(index);
			index = FindMaxIndex(n);
			number++;
		}
		PrintNum(n);
		System.out.println("总共有"+number+"个排列");
	}
}

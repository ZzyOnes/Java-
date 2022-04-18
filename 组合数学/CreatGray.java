package 组合数学;
import java.util.Scanner;
public class CreatGray {

	public static String[] createGrayCode1(int n) {
		String[] codes = new String[2<<n-1];
		createGrayCode1(codes,n);
		return codes;
	}
	public static int GetArrayOneNumIsOdd(int n,int[] arr) {
		int num=0;
		for(int i=0;i<n;i++) {
			if(arr[i]==1)num++;
		}
		return num%2;
	}
	public static void ChangeNum(int n,int[] arr) {
		for(int i=n-1;i>=0;i--) {
			if(arr[i]==1) {
				if(i-1>=0)
				arr[i-1]=arr[i-1]==0?1:0;
				return;
			}
		}
	}
	public static void PrintArray(int n,int[] arr) {
		for(int i=0;i<n;i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
	public static void createGrayCode1(String[] codes,int n) {
		if(n==1) {
			codes[0] = "0";
			codes[1] = "1";
		}else {
			createGrayCode1(codes,n-1);
			int len = 2<<n-1;
			int half = len>>1;
			for(int i=len-1,j=0;i>=0;i--) {
				codes[i] = i<half ? "0"+codes[--j]:"1"+codes[j++];
			}
		}
		
	}
	public static void main(String[] args) {  
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		/*递归方法
		 * String[] codes = createGrayCode1(n); for(int i=0;i<2<<n-1;i++) {
		 * System.out.println(codes[i]); }
		 */
		//非递归方法
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=0;
		}
		int number = 2<<n-1;
		while(number>0) {
			PrintArray(n,arr);
			int flag=GetArrayOneNumIsOdd(n,arr);
			if(flag==0) {
				arr[n-1]=arr[n-1]==1?0:1;
			}else {
				ChangeNum(n,arr);
			}
			number--;
		}
	}

}

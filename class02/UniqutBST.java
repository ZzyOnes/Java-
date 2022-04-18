package class02;
import java.util.Scanner;
public class UniqutBST {
	//�ݹ鷽��
	public static int Process(int n) {
		if(n==0)return 1;
		if(n==1)return 1;
		if(n==2)return 2;
		int res=0;
		for(int leftNum=0;leftNum<=n-1;leftNum++) {
			int leftWays=Process(leftNum);
			int rightWays=Process(n-leftNum-1);
			res+=leftWays*rightWays;
		}
		return res;
	}
	//��̬�滮����
	public static int numTrees(int n) {
		if(n<2)return 1;
		int[] dp = new int[n+1];
		dp[0]=1;
		for(int i=1;i<n+1;i++) {//�ܹ�i���ڵ�
			for(int j=0;j<=i-1;j++) {//���j���ڵ�
				dp[i]+=dp[i-j-1]*dp[j];
			}
		}
		return dp[n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.print(numTrees(n));
	}

}

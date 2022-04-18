package high_Class_Problem;
/*
 * ����һ���������ÿ��λ��������ĵ÷�
 * ����򱬵�����λ��Ϊi�����ΪX, ����������һ��û��������÷�ΪY,�ұ��������һ��û��������ΪZ
 * ��ô��÷�Ϊ��Y*X*Z ���������򶼴���÷�Ϊ��X*Z ����ұ����򶼴���÷�Ϊ��Y*X
 * ����������򶼴���÷�Ϊ��X
 * ���������򶼴�ʱ���ĵ÷�Ϊ����  
 */
public class BurstBalloons {
	
	public static int maxCoins1(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int N = arr.length;
		int[] help = new int[N+2];
		help[0] = 1; help[N+1] = 1;
		for(int i=0;i<N;i++) {
			help[i+1] = arr[i];
		}
		return process(help,1,N);
	}

	//����L-1��R+1������һ��û��
	//process(arr,L,R)���ش�L��R�ϵ���������ʱ�����÷�
	public static int process(int[] arr, int L, int R) {
		if(L==R) {
			return arr[L]*arr[L-1]*arr[R+1];
		}
		int max = Math.max(arr[L]*arr[L-1]*arr[R+1]+process(arr,L+1,R),//���򱬵���Lλ��
				           arr[R]*arr[L-1]*arr[R+1]+process(arr,L,R-1));//���򱬵���Rλ��
		for(int i=L+1;i<R;i++) {//���򱬵���iλ��
			max = Math.max(max,arr[i]*arr[L-1]*arr[R+1]+process(arr,L,i-1)+process(arr,i+1,R));
		}
		return max;
	}
	
	//�Ķ�̬�滮
	public static int maxCoins2(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int N = arr.length;
		int[] help = new int[N+2];
		help[0] = 1; help[N+1] = 1;
		for(int i=0;i<N;i++) {
			help[i+1] = arr[i];
		}
		int[][] dp = new int[N][N];
		//��ʼ��
		for(int i=0;i<N;i++) {
			dp[i][i] = help[i+1]*help[i]*help[i+2];
		}
		for(int L=N-2;L>=0;L--) {
			for(int R=L+1;R<=N-1;R++) {
				int max = Math.max(help[L+1]*help[L]*help[R+2]+dp[L+1][R],//���򱬵���Lλ��
				           help[R+1]*help[L]*help[R+2]+dp[L][R-1]);//���򱬵���Rλ��  
				for(int i=L+1;i<R;i++) {//���򱬵���iλ��
					max = Math.max(max,help[i+1]*help[L]*help[R+2]+dp[L][i-1]+dp[i+1][R]);
				}
				dp[L][R] = max;
			}
		}
		return dp[0][N-1];
	}

	public static void main(String[] args) {
		int[] test = {3,2,5,65};
		System.out.print(maxCoins1(test)+" "+maxCoins2(test));

	}

}

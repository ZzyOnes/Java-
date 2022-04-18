package high_Class_Problem;
/*
 * ����һ����������������ѿ��ķ������磺4=1+1+1+1 ; 1+1+2 ; 1+3 ; 2+2 ; 4+0 ��5���ѿ�����
 */
public class SplitNumber {
	
	public static int getWays(int n) {
		if(n<=0) {
			return 0;
		}else if(n==1) {
			return 1;
		}else {
			return process(1,n);
		}
	}
	//pre�ѿ���ǰһ������
	//rest��ʣ����ֵȥ�ѿ�,�ѿ��ĵ�һ�����ֲ��ܱ�preС
	//�����ѿ��ķ�����
	public static int process(int pre,int rest) {
		if(rest==0) {
			return 1;
		}
		if(pre>rest) {
			return 0;
		}
		int ways = 0;
		for(int i=pre;i<=rest;i++) { //i����rest��1���ѿ��Ĳ���ֵ�Ƕ���
			ways += process(i,rest-i);
		}
		return ways;
	}

	//����2ֱ�ӱ����Ķ�̬�滮  , dp[pre][rest]��ֵ������ʣrest��Ҫ�ѿ����һ���ֱ�����ڵ���pre�ķ����� 
	public static int ways2(int n) {
		if(n<1) {
			return 0;
		}
		int[][] dp = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			dp[i][0] = 1;
		}
		//��ʼ����������ϣ�����������
		for(int pre=n;pre>0;pre--) {
			for(int rest=pre;rest<=n;rest++) {
				//�������̿��Խ���б���Ż�
				for(int i=pre;i<=rest;i++ ) {
					dp[pre][rest] += dp[i][rest-i];
				}
			}
		}
		return dp[1][n];
	}
	//����б���Ż�
	public static int ways3(int n) {
		if(n<1) {
			return 0;
		}
		int[][] dp = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			dp[i][0] = 1;
		}
		
		//��ʼ����������ϣ�����������,���ڻ�����Խ�����Գ�ʼ������һ��
		for(int pre=n;pre>0;pre--) {
			for(int rest=pre;rest<=n;rest++) {
				if(pre==n) dp[pre][rest] = 1;
				else
				dp[pre][rest] = dp[pre+1][rest] + dp[pre][rest-pre];
			}
		}
		return dp[1][n];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test = 15;
		System.out.println(getWays(test)+"  "+ ways2(test)+"  "+ways3(test));
	}

}

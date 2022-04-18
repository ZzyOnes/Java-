package class06�ݹ�ת��̬�滮;
//���Գ��ݹ鷽��-->�ӻ���ƻ���������������������������-->����������ҵ����������ж�̬�滮���


//һ��1~N��λ��
//��ʼλ��S
//����λ��e
//�����˱����ߵĲ���k

public class RobotWalk {
	
	public static int walkWay1(int N,int s,int e,int k) {
		return f1(N,e,k,s);
	}
//N��λ�ã�����λ��E����ʣrest������ǰ��curλ�ã��Żط�����
	public static int f1(int N,int E,int rest,int cur) {
		if(rest==0) {
			return cur==E ? 1:0;
		}
		//rest>0,��Ҫ������
		if(cur==1) {//����������
			return f1(N,E,rest-1,2);
		}
		if(cur==N) {//����������
			return f1(N,E,rest-1,N-1);
		}
		return f1(N,E,rest-1,cur-1)+f1(N,E,rest-1,cur+1);
		
	}
	
	
	//��walkWay2�����Ż����ӻ���  (�ƻ�����)
	public static int walkWay2(int N,int E,int S,int k) {
		int[][] dp = new int[k+1][N+1];//��ʾ��ʣi�����ĵ�j��λ��
		for(int i=0;i<=k;i++) {//��dp��ʼ��
			for(int j=0;j<=N;j++) {
				dp[i][j] = -1;
			}
		}
		return f2(N,E,k,S,dp);
	}
	public static int f2(int N,int E,int rest,int cur,int[][] dp) {
		if(dp[rest][cur]!=-1) {//���֮ǰ�����ֱ�ӷ���֮ǰ��ֵ��Ҫ����������
			return dp[rest][cur];
		}
		//����û����
		if(rest==0) {
			dp[rest][cur] = cur==E ? 1:0;//��return֮ǰ���𰸼�¼�ڸ������ٷ���
			//return dp[rest][cur];
		}
		//rest>0,��Ҫ������
		else if(cur==1) {//����������
			dp[rest][cur] = f2(N,E,rest-1,2,dp);//��return֮ǰ���𰸼�¼�ڸ������ٷ���
			//return dp[rest][cur];
		}
		else if(cur==N) {//����������
			dp[rest][cur] = f2(N,E,rest-1,N-1,dp);//��return֮ǰ���𰸼�¼�ڸ������ٷ���
			//return dp[rest][cur];
		}
		else {
			dp[rest][cur] = f2(N,E,rest-1,cur-1,dp)+f2(N,E,rest-1,cur+1,dp);//��return֮ǰ���𰸼�¼�ڸ������ٷ���
		}
		return dp[rest][cur];
	}
	
	
//��̬�滮�����Ż�,��f2�����н����ҵ�����������⡣������Ҫ����Ŀ����Ŀ���߼����ڵݹ�ʵ�֡�ֱ�����Ż���ķ������ж�̬�滮��
	public static int dpWay(int N,int E,int S,int k) {
		int[][] dp = new int[k+1][N+1];//dp[][0]�ò���
		dp[0][E] = 1;//��ʣ0����������λ�õķ���Ϊ1
		for(int rest = 1;rest <= k;rest++) {
			for( int cur = 1;cur <= N;cur++) {
				if(cur == 1) {
					dp[rest][cur] = dp[rest-1][2];
				}
				else if(cur == N) {
					dp[rest][cur] = dp[rest-1][N-1];
				}
				else {
					dp[rest][cur] = dp[rest-1][cur-1]+dp[rest-1][cur+1];
				}
			}
		}
		return dp[k][S];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

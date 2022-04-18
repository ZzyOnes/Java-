package class06�ݹ�ת��̬�滮;

public class CoinsMin {
	
	public static int minCoins1(int[] arr,int aim) {
		return process1(arr,0,aim);
	}
	//arr[index....]��Ӳ�����rest��ô��Ǯ�����ٵ�Ӳ����������
	//return -1 ��ʾ���ַ����ղ���aim   return 0 ��ʾ�ճ���aim������
	public static int process1(int[] arr,int index,int rest ) {
		if(rest<0) {
			return -1;
		}
		if(rest == 0) {
			return 0;
		}
		//rest>0
		if(index == arr.length) {
			return -1;
		}
		int p1 = process1(arr,index+1,rest);//ûѡindex��Ӳ��
		int p2 = process1(arr,index+1,rest-arr[index]);//ѡ��index��Ӳ�ҵĺ�������Ӳ����
		if(p1==-1&&p2==-1) {
			return -1;
		}else {
			if(p1==-1) {
				return p2+1;
			}
			if(p2==-1) {
				return p1;
			}
			return Math.min(p1,p2+1);
		}
		/*
		 * //rest>0,������Ҳ��Ӳ�� return
		 * Math.min(process(arr,index+1,rest),1+process(arr,index+1,rest-arr[index]));
		 * -1����Ž���͸ĳ�����ķ���
		 */
	}

	//�Ż����ƻ�����  ����index��rest�����ɱ������һ����ά��dp[][]
	//index:0~arr.length  rest<=aim
	public static int minCoins2(int[] arr,int aim) {
		int[][] dp = new int[arr.length+1][aim+1];
		for(int i=0;i<=arr.length;i++) {
			for(int j=0;j<=aim;j++) {
				dp[i][j] = -16384;//��ʼ����-16384��ʾ���״̬û�����-1��ʾ�����Ч��
			}
		}
		return process2(arr,0,aim,dp);
	}
	public static int process2(int[] arr,int index,int rest,int[][] dp ) {
		/*�޷���dp��ʾrest<0
		 * if(rest<0) { return -1; }
		*/
		if(rest<0) { 
			return -1; 
		}
		if(dp[index][rest]!=-16384) {//��λ�����(�Ż������������ֱ�ӷ���)
			return dp[index][rest];
		}
		//����֮ǰ��¼�ڸ�����
		if(rest == 0) {
			dp[index][rest] = 0;
			return 0;
		}
		//rest>0
		if(index == arr.length) {
			dp[index][rest] = -1;
			return -1;
		}
		int p1 = process2(arr,index+1,rest,dp);//ûѡindex��Ӳ��
		int p2 = process2(arr,index+1,rest-arr[index],dp);//ѡ��index��Ӳ�ҵĺ�������Ӳ����
		if(p1==-1&&p2==-1) {
			dp[index][rest] = -1;
			//return dp[index][rest];
		}else {
			if(p1==-1) {
				dp[index][rest] = p2+1;
				//return dp[index][rest];
			}
			if(p2==-1) {
				dp[index][rest] = p1;
				//return dp[index][rest];
			}else {
				dp[index][rest] =  Math.min(p1,p2+1);
			}
		}
		return dp[index][rest];
	}
	
	//�ĳɶ�̬�滮������dp�������ҳ�������ϵ����ǰλ��(index,rest)��ֵ����(index+1,rest)��(index+1,rest-arr[index])��ֵ
	public static int minCoins3(int[] arr,int aim) {
		int N = arr.length;
		int[][] dp = new int[N+1][aim+1];
		for(int row=0;row<=N;row++) {//�ȳ�ʼ����ֵ
			dp[row][0] = 0;
		}
		for(int col=1;col<=aim;col++) {//�ȳ�ʼ����ֵ
			dp[N][col] = -1;
		}
		for(int index = N-1;index>=0;index--) {//�������ķ�ʽ�����
			for(int rest=1;rest<=aim;rest++) {
				int p1 = dp[index+1][rest];//ûѡindex��Ӳ��
				int p2 = -1;//ѡ��index��Ӳ�ҵĺ�������Ӳ����
				if(rest-arr[index]>=0) {//��ֹrest-arr[index]Խ��
					p2 = dp[index+1][rest-arr[index]];
				}
				if(p1==-1&&p2==-1) {
					dp[index][rest] = -1;
				}else {
					if(p1==-1) {
						dp[index][rest] = p2+1;
					}
					if(p2==-1) {
						dp[index][rest] = p1;
					}else {
						dp[index][rest] = Math.min(p1,p2+1);
					}
				}
			}
		}
		return dp[0][aim];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

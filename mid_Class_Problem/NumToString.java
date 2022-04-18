package mid_Class_Problem;
/*
 * ����һ��������������ת��Ϊ�ַ����ķ�������1��Ӧa....26��Ӧz��
 * ���Ե���λ��ת��Ҳ��������λ�úͲ�ת��
 */
public class NumToString {

	//0..index-1ת����ϲ���ת����ȷ
	//��str[index...]�ж����ַ���
	public static int Way(int num) {
		if(num==0) {
			return 0;
		}
		return process(String.valueOf(num).toCharArray(),0);
	}
	//�ݹ�
	public static int process(char[] str,int index) {
		if(index==str.length) {
			return 1;
		}
		if(str[index]=='0') {
			return 0;
		}
		int rest=process(str,index+1);
		if(index==str.length-1) {//�������λ��û�еڶ��ֿ���
			return rest;
		}
		if((str[index]-'0')*10+(str[index+1]-'0')<27) {
			rest+=process(str,index+2);
		}
		return rest;
	}
	//��̬�滮
	public static int dpways(int num) {
		if(num<1) {
			return 0;
		}
		char[] str = String.valueOf(num).toCharArray();
		int N = str.length;
		int[] dp = new int[N+1];
		dp[N]=1;
		dp[N-1]=str[N-1]=='0'?0:1;
		for(int i=N-2;i>=0;i--) {
			if(str[i]=='0') {
				dp[i]=0;
			}else {
				dp[i]=dp[i+1]+(((str[i]-'0')*10+(str[i+1]-'0'))<27?dp[i+2]:0);
			}
		}
		return dp[0];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package mid_Class_Problem;
/*
 * ����һ���������з��������������
 */
public class ParenthesesDeep {
	//��̬�滮
	public static int maxLength(String s) {
		if (s==null||s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];//dp[i]������iλ�ý�β�������������
		int pre=0;
		int rest=0;
		for(int i=0;i<str.length;i++) {//����ÿһ����iλ�ý�β��������������������
			if(str[i]=='(') {//�������Ž�βdp[i]=0;
				dp[i]=0;
			}else {//�������Ž�β
				pre=i-dp[i-1]-1;
				if(pre>=0&&str[pre]=='(') {//�����ǰ����Ч���ŵ�ǰһ��λ����'(',��ô����С��dp[i-1]+2
					dp[i] = dp[i-1] + 2 + (pre-1>=0?dp[pre-1]:0);//�����⻹Ҫƴ����ǰ�������������
				}else {//����dp[i]Ϊ0
					dp[i]=0;
				}
			}
			rest = Math.max(rest, dp[i]);
		}
		return rest;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package high_Class_Problem;
/*
 * ����һ���ַ��������ؽ�����ַ����ֳ�ÿ���Ӵ����ǻ����ַ��������ٲ�����
 */
public class PlindromeMinCut {
	
	public static int getMinCut(String str, boolean[][] dp) {
		if(str.length()==0||str==null) {
			return 0;
		}
		if(str.length()==1) {
			return 1;
		}
		return process(str.toCharArray(), 0,dp);
	}

	// �õݹ鷵��str[i...]�����ٲ�����
	private static int process(char[] str, int i,boolean[][] dp) {
		if(i==str.length) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		//����ÿ��str[i...end]�ǻ���+process(str,end+1,dp)�����
		for(int end=i;end<str.length;end++) {
			if(dp[i][end]) {
				ans = Math.min(ans,1+process(str,end+1,dp));
			}
		}
			return ans;
	}
	//��֤s��eλ�����ǲ��ǻ����ַ���  �÷���ʱ�为�ض�ΪO(n)�����Ż�
	//�����Ż�,����һ��dp[i][j]������str��i��j�ǲ��ǻ����ַ���
	public static boolean[][] valid(char[] str) {
		boolean[][] dp = new boolean[str.length][str.length];
		//��ʼ��
		for(int i=0;i<dp.length;i++) {
			dp[i][i] = true;
			if(i+1<dp.length) {
				if(str[i]==str[i+1]) {
					dp[i][i+1] = true;
				}
			}
		}
		//���
		for(int i=dp.length-3;i>=0;i--) {
			for(int j=dp.length-1;j>=i+2;j--) {
				if(str[i]==str[j]) {
					dp[i][j] = dp[i+1][j-1];
				}
			}
		}
		return dp;
	}
	public static void main(String[] args) {
		String testStr = "acdcdcdad";
		boolean[][] dp = valid(testStr.toCharArray());
		System.out.print(getMinCut(testStr,dp)-1);
	}

}

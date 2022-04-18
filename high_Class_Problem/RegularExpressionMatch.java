package high_Class_Problem;
/*
 * ����һ���ַ���sȫ��a...z���,�ٸ���һ��ƥ�䴮p���а���.��*
 * ����.����������ǿ��ַ�ƥ�䣬*��������ǰһ���ַ�����ʹ���磺b*���Դ���������b,����Ϊ0��b
 * .*���Դ��������ַ���
 * �ʣ�p������sƥ��ɹ��𣿷���true or false
 */
public class RegularExpressionMatch {
	
	//�ж������s��p�Ƿ���Ч
	public static boolean isValid(char[] s,char[] p) {
		for(int i=0;i<s.length;i++) {
			if(s[i]<'a'||s[i]>'z') {
				return false;
			}
		}
		for(int j=0;j<p.length;j++) {
			if(p[j]=='*') {
				if(j==0) {
					return false;
				}
				if(p[j-1]=='*') {
					return false;
				}
			}
		}
		return true;
	}
	
	//������
	public static boolean matchResult(String s,String p) {
		if(s==null||p==null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] par = p.toCharArray();
		return isValid(str,par)&&process(str,par,0,0);
	}

	//�ж�str��si��ʼ,par��ei��ʼ�ܲ���ƥ��ɹ�
	private static boolean process(char[] str, char[] par, int si, int ei) {
		if(ei==par.length) {
			return si==str.length;
		}
		//��һ����� ei+1��Ϊ*
		if(ei+1==par.length||par[ei+1]!='*') {
			return (si!=str.length&&(str[si]==par[ei]||par[ei]=='.'))&&process(str,par,si+1,ei+1);
		}
		//�ڶ������ei+1Ϊ*
			while(si!=str.length&&(str[si]==par[ei]||par[ei]=='.')) {
				//if(par[ei]=='.')par[ei]=str[si];
				if( process(str,par,si,ei+2)) {
					return true;
				}
				si++;
			}
		return process(str,par,si,ei+2);
	}
	
	//�ݹ�Ķ�̬�滮
	public static boolean matchResult1(String s,String p) {
		if(s==null||p==null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] par = p.toCharArray();
		if(!isValid(str,par))
		return false;
		int M = str.length;
		int N = par.length;
		boolean[][] dp = new boolean[str.length+1][par.length+1];
		//��ʼ��ʼ��
		dp[M][N] = true;
		dp[M-1][N-1] = (str[M-1]==par[N-1])||(par[N-1]=='.');
		for(int ei=N-2;ei>=0;ei=ei-2) {
			if(par[ei+1]=='*')
				dp[M][ei]=true;
			else
				break;
		}
		//��ʼ���
		//�������󣬴�������
		for(int si=M-1;si>=0;si--) {
			for(int ei=N-2;ei>=0;ei--) {
				if(par[ei+1]!='*') {
					dp[si][ei] = (str[si]==par[ei]||par[ei]=='.')&&dp[si+1][ei+1];
				}else {
					int i=si;
					while(i!=M&&(str[i]==par[ei]||par[ei]=='.')) {
						if( dp[i][ei+2]) {
							dp[si][ei]=true;
							break;
						}
						i++;
					}
					if(dp[si][ei]==false) {
						dp[si][ei] = dp[i][ei+2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		String str = "aldddddcssal";
		String par = ".*a.*c.*";
		System.out.print(matchResult(str,par)==matchResult1(str,par));
	}

}

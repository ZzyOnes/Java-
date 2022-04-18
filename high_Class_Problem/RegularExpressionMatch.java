package high_Class_Problem;
/*
 * 给你一个字符串s全由a...z组成,再给你一个匹配串p其中包含.和*
 * 其中.可以与任意非空字符匹配，*可以与其前一个字符联合使用如：b*可以代表任意多个b,可以为0个b
 * .*可以代替任意字符串
 * 问，p可以与s匹配成功吗？返回true or false
 */
public class RegularExpressionMatch {
	
	//判断输入的s和p是否有效
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
	
	//主函数
	public static boolean matchResult(String s,String p) {
		if(s==null||p==null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] par = p.toCharArray();
		return isValid(str,par)&&process(str,par,0,0);
	}

	//判断str从si开始,par从ei开始能不能匹配成功
	private static boolean process(char[] str, char[] par, int si, int ei) {
		if(ei==par.length) {
			return si==str.length;
		}
		//第一种情况 ei+1不为*
		if(ei+1==par.length||par[ei+1]!='*') {
			return (si!=str.length&&(str[si]==par[ei]||par[ei]=='.'))&&process(str,par,si+1,ei+1);
		}
		//第二种情况ei+1为*
			while(si!=str.length&&(str[si]==par[ei]||par[ei]=='.')) {
				//if(par[ei]=='.')par[ei]=str[si];
				if( process(str,par,si,ei+2)) {
					return true;
				}
				si++;
			}
		return process(str,par,si,ei+2);
	}
	
	//递归改动态规划
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
		//开始初始化
		dp[M][N] = true;
		dp[M-1][N-1] = (str[M-1]==par[N-1])||(par[N-1]=='.');
		for(int ei=N-2;ei>=0;ei=ei-2) {
			if(par[ei+1]=='*')
				dp[M][ei]=true;
			else
				break;
		}
		//开始填表
		//从右往左，从下往上
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

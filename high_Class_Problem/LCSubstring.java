package high_Class_Problem;
/*
 * 给你两个字符串str1 str2
 * 返回最长公共子串
 * 1.先动态规划画表
 * 2.再进行空间压缩进行优化
 */
public class LCSubstring {
	

	//1.动态规划 dp[i][j]表示最长公共子串在str1中以i位置结尾在str2中以j位置结尾
	public static String getMaxSubString(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int maxLen = 0;
		int end = -1;
		int[][] dp = new int[str1.length][str2.length];
		//先初始化
		//填第一行
		for(int j=0;j<str2.length;j++) {
			if(str1[0]==str2[j]) {
				dp[0][j]=1;
				if(dp[0][j]>maxLen) {
					maxLen = dp[0][j];
					end = 0;
				}
			}
		}
		//填第一列
		for(int i=1;i<str1.length;i++) {
			if(str2[0]==str1[i]) {
				dp[i][0]=1;
				if(dp[i][0]>maxLen) {
					maxLen = dp[i][0];
					end = i;
				}
			}
		}
		//开始填表
		for(int i=1;i<str1.length;i++) {
			for(int j=1;j<str2.length;j++) {
				if(str1[i]==str2[j]) {
					dp[i][j] = dp[i-1][j-1]+1;
					if(dp[i][j]>maxLen) {
						maxLen = dp[i][j];
						end = i;
					}
				}
			}
		}
		if(end==-1) {
			return null;
		}
		return s1.substring(end-maxLen+1,end+1);
	}
	
	//2.空间压缩
	public static String getSubString(String s1,String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		//斜线开始位置
		int row = 0;
		int col = str2.length-1;
		int end = 0;//最长公共子串在s1中的结尾位置
		int max = 0;
		while(row<str1.length) {
			int i=row;
			int j=col;
			int len = 0;
			while(i<str1.length&&j<str2.length) {
				if(str1[i]!=str2[j]) {
					len = 0;
				}else {
					len++;
				}
				if(len>max) {
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if(col>0) {
				col--;
			}else {
				row++;
			}
		}
		return s1.substring(end-max+1,end+1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test1 = "adupeowpwola;as";
		String test2 = "lskljsks";
		System.out.println(getSubString(test1,test2)+"------------"+getMaxSubString(test1,test2));

	}

}

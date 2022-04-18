package mid_Class_Problem;
/*
 * 给你一串括号序列返回最大的括号深度
 */
public class ParenthesesDeep {
	//动态规划
	public static int maxLength(String s) {
		if (s==null||s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];//dp[i]代表以i位置结尾的括号序列深度
		int pre=0;
		int rest=0;
		for(int i=0;i<str.length;i++) {//遍历每一个以i位置结尾的括号序列求其最大深度
			if(str[i]=='(') {//以左括号结尾dp[i]=0;
				dp[i]=0;
			}else {//以右括号结尾
				pre=i-dp[i-1]-1;
				if(pre>=0&&str[pre]=='(') {//如果它前面有效括号的前一个位置是'(',那么它最小是dp[i-1]+2
					dp[i] = dp[i-1] + 2 + (pre-1>=0?dp[pre-1]:0);//除此外还要拼接它前面的最大括号深度
				}else {//否则dp[i]为0
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

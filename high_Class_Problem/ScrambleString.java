package high_Class_Problem;
/*
 * 给你两个字符串，str1与str2判断是否是旋变串
 */
public class ScrambleString {
	
	public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
		if(str1.length!=str2.length) {
			return false;
		}
		int[] map = new int[256];//统计字符词频
		for(int i=0;i<str1.length;i++) {
			map[str1[i]]++;
		}
		for(int i=0;i<str2.length;i++) {
			if(--map[str2[i]]<0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isScramble1(String s1,String s2) {
		if((s1==null&&s2!=null)||(s1!=null&&s2==null)) {
			return false;
		}
		if(s1==null&&s2==null) {
			return true;
		}
		if(s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if(!sameTypeSameNumber(str1,str2)) {
			return false;
		}
		int N = str1.length;
		return process(str1,str2,0,0,N);
	}

	//str1以L1开头，str2以L2开头，字符个数都为size
	//返回str1[L1...L1+size-1]与str2[L2...L2+size-1]是否为旋变串
	public static boolean process(char[] str1, char[] str2, int L1, int L2, int size) {
		if(size==1) {//此时只有一个元素
			return str1[L1]==str2[L2];
		}
		//枚举每一种情况，有一个计算出互为旋变串就返回true，如果都算不出来就返回false
		for(int leftNumber = 1;leftNumber<size;leftNumber++) {
			if(	//1.不旋转的情况下  str1的左部与str2的左部互为旋变串，且str1的右部与str2的右部互为旋变串
				(process(str1,str2,L1,L2,leftNumber)&&process(str1,str2,L1+leftNumber,L2+leftNumber,size-leftNumber))
				||  //2.旋转的情况下
				(process(str1,str2,L1,L2+size-leftNumber,leftNumber)&&process(str1,str2,L1+leftNumber,L2,size-leftNumber))
					) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 递归改动态规划
	 */
	public static boolean isScramble2(String s1,String s2) {
		if((s1==null&&s2!=null)||(s1!=null&&s2==null)) {
			return false;
		}
		if(s1==null&&s2==null) {
			return true;
		}
		if(s1.equals(s2)) {
			return true;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		if(!sameTypeSameNumber(str1,str2)) {
			return false;
		}
		int N = str1.length;
		return dpCheck(str1,str2);
	}
	
	public static boolean dpCheck(char[] str1, char[] str2) {
		int N = str1.length;
		boolean[][][] dp = new boolean[N][N][N+1];
		for(int L1=0;L1<N;L1++) {
			for(int L2=0;L2<N;L2++) {
				dp[L1][L2][1] = str1[L1]==str2[L2];
			}
		}
		for(int size=2;size<=N;size++) {
			for(int L1=0;L1<=N-size;L1++) {
				for(int L2=0;L2<=N-size;L2++) {
					for(int leftNumber = 1;leftNumber<size;leftNumber++) {
						if(	//1.不旋转的情况下  str1的左部与str2的左部互为旋变串，且str1的右部与str2的右部互为旋变串
							(dp[L1][L2][leftNumber]&&dp[L1+leftNumber][L2+leftNumber][size-leftNumber])
							||  //2.旋转的情况下
							(dp[L1][L2+size-leftNumber][leftNumber]&&dp[L1+leftNumber][L2][size-leftNumber])
								) {
							dp[L1][L2][size] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0][N];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testS1 = "adlcaabce";
		String testS2 = "lcadaabce";
		System.out.println(isScramble1(testS1,testS2));
		System.out.print(isScramble2(testS1,testS2));
	}

}

package high_Class_Problem;
/*
 * ���������ַ�����str1��str2�ж��Ƿ������䴮
 */
public class ScrambleString {
	
	public static boolean sameTypeSameNumber(char[] str1, char[] str2) {
		if(str1.length!=str2.length) {
			return false;
		}
		int[] map = new int[256];//ͳ���ַ���Ƶ
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

	//str1��L1��ͷ��str2��L2��ͷ���ַ�������Ϊsize
	//����str1[L1...L1+size-1]��str2[L2...L2+size-1]�Ƿ�Ϊ���䴮
	public static boolean process(char[] str1, char[] str2, int L1, int L2, int size) {
		if(size==1) {//��ʱֻ��һ��Ԫ��
			return str1[L1]==str2[L2];
		}
		//ö��ÿһ���������һ���������Ϊ���䴮�ͷ���true��������㲻�����ͷ���false
		for(int leftNumber = 1;leftNumber<size;leftNumber++) {
			if(	//1.����ת�������  str1������str2���󲿻�Ϊ���䴮����str1���Ҳ���str2���Ҳ���Ϊ���䴮
				(process(str1,str2,L1,L2,leftNumber)&&process(str1,str2,L1+leftNumber,L2+leftNumber,size-leftNumber))
				||  //2.��ת�������
				(process(str1,str2,L1,L2+size-leftNumber,leftNumber)&&process(str1,str2,L1+leftNumber,L2,size-leftNumber))
					) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * �ݹ�Ķ�̬�滮
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
						if(	//1.����ת�������  str1������str2���󲿻�Ϊ���䴮����str1���Ҳ���str2���Ҳ���Ϊ���䴮
							(dp[L1][L2][leftNumber]&&dp[L1+leftNumber][L2+leftNumber][size-leftNumber])
							||  //2.��ת�������
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

package high_Class_Problem;
/*
 * 给你一个字符串输出它在第几位,或者给你一个数输出它的字符串
 * 如：A~Z:1~26  AA~AZ:27~52  BA~BZ:53~78
 */
public class NumberAndString {
	
	public static String getString(char[] chs, int n ) {
		int cur = 1;
		int base = chs.length;
		int len = 0;
		while(n>=cur) {
			len++;
			n -= cur;
			cur *= base;
		}
		char[] res = new char[len];
		int index = 0;
		int nCur = 0;
		do {
			cur /= base;
			nCur = n/cur;
			res[index++] = getKthCharAtChs(chs, nCur + 1);
			n %= cur;
		}while(index != res.length);
		return String.valueOf(res);
	}

	public static char getKthCharAtChs(char[] chs, int k) {
		if(k<1 || k>chs.length) {
			return 0;
		}
		return chs[k-1];
	}
	
	public static int getNum(char[] chs, String str) {
		char[] strc = str.toCharArray();
		int base = chs.length;
		int cur = 1;
		int res = 0;
		for(int i=strc.length-1;i>=0;i--) {
			res += cur*getNumAtchs(chs,strc[i]);
			cur *= base;
		}
		return res;
	}

	public static int getNumAtchs(char[] chs, char c) {
		int res = -1;
		for(int i=0; i<chs.length ;i++) {
			if(chs[i]==c) {
				res = i+1;
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chs = {
				'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','S','Y','Z'
		};
		int testNum = 2321;
		System.out.println(getString(chs,testNum));
		String testStr = "BAEC";
		System.out.println(getNum(chs,testStr));
		System.out.println(getString(chs,getNum(chs,testStr)));
	}

}

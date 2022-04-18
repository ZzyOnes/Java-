package class04;

public class Manacher {
	
/*	public static int[] manacher(String s) {
		// 1221 -> #1#2#2#1
		char[] str;
		int[] pArr = new int[str.length];
		int R = ?
	    int L = ?
	    for(int i=0;i<str.length;i++) {
	    	if(i在R外部) {
	    		从i位置往两边暴力扩，R变大
	    	}else {
	    		if(i‘回文区域在L..R内) {
	    			pArr[i]=pArr[i’];
	    		}else if(i‘回文区域有一部分在L..R外) {
	    			pArr[i]=2*(R-i)+1;
	    		}else if(i‘回文区域与L押界) {
	    			从R自外的位置往外扩，确定pArr[i]的答案，R可能增大
	    		}
	    	}
	    }
	}
*/
	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length()*2+1];
		int index = 0;
		for(int i=0;i<res.length;i++) {
			res[i] = (i&1)==0?'#':charArr[index++];
		}
		return res;
	}
	
	public static int maxLcpsLength(String s) {
		if(s==null||s.length()==0) {
			return 0;
		}
		char[] str = manacherString(s);
		int[] pArr = new int[str.length];//回文半径数组
		int C = -1;//中心位置
		int R = -1;//回文右边界再往右一个位置，最右有效区域是R-1
		int max = Integer.MIN_VALUE;//扩出有效区域的最大值
		for(int i=0;i<str.length;i++) {//每一个位置都求回文半径
			pArr[i] = R>i ? Math.min(pArr[2*C-i], R-i) : 1;
			while(i+pArr[i]<str.length && i-pArr[i]>-1) {//4种情况都从i位置往外扩看能否变大，其实有两种情况不用扩但扩一次会失败
				if(str[i+pArr[i]]==str[i-pArr[i]])
					pArr[i]++;
				else
					break;
			}
			if(i+pArr[i]>R) {//更新右边界和中心位置
				R = i+pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max-1;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

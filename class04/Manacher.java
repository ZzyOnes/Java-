package class04;

public class Manacher {
	
/*	public static int[] manacher(String s) {
		// 1221 -> #1#2#2#1
		char[] str;
		int[] pArr = new int[str.length];
		int R = ?
	    int L = ?
	    for(int i=0;i<str.length;i++) {
	    	if(i��R�ⲿ) {
	    		��iλ�������߱�������R���
	    	}else {
	    		if(i������������L..R��) {
	    			pArr[i]=pArr[i��];
	    		}else if(i������������һ������L..R��) {
	    			pArr[i]=2*(R-i)+1;
	    		}else if(i������������LѺ��) {
	    			��R�����λ����������ȷ��pArr[i]�Ĵ𰸣�R��������
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
		int[] pArr = new int[str.length];//���İ뾶����
		int C = -1;//����λ��
		int R = -1;//�����ұ߽�������һ��λ�ã�������Ч������R-1
		int max = Integer.MIN_VALUE;//������Ч��������ֵ
		for(int i=0;i<str.length;i++) {//ÿһ��λ�ö�����İ뾶
			pArr[i] = R>i ? Math.min(pArr[2*C-i], R-i) : 1;
			while(i+pArr[i]<str.length && i-pArr[i]>-1) {//4���������iλ�����������ܷ�����ʵ�������������������һ�λ�ʧ��
				if(str[i+pArr[i]]==str[i-pArr[i]])
					pArr[i]++;
				else
					break;
			}
			if(i+pArr[i]>R) {//�����ұ߽������λ��
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

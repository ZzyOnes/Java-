package mid_Class_Problem;

public class StringToKth {
	
	//必须以i号字符开头、总长度为len的子序列有多少个
	public static int g(int i, int len) {
		if(len==1) {
			return 1;
		}
		int sum = 0;
		for(int j=i+1;j<=26;j++) {
			sum+=g(j,len-1);
		}
		return sum;
	}
	//长度为len的子序列有多少个
	public static int f(int len) {
		int sum=0;
		for(int i=1;i<=26;i++) {
			sum+=g(i,len);
		}
		return sum;
	}

	//返回序列在哪个位置
	public static int kth(String s) {
		char[] str = s.toCharArray();
		int sum = 0;
		int len = str.length;
		for(int i=1;i<len;i++) {//将所有长度为len-1的序列个数加上
			sum+=f(i);
		}
		int first = str[0]-'a'+1;//第一个字符
		for(int i=1;i<first;i++) {//将以小于该序列第一个字符为开头，长度为len的序列个数加上
			sum+=g(i,len);
		}
		//将剩下的加上
		int pre=first;
		for(int i=1;i<len;i++) {
			int cur = str[i]-'a'+1;//当前字符
			for(int j=pre+1;j<cur;j++) {//将小于当前字符开头，长度为len-i的序列个数加上
				sum+=g(j,len-i);
			}
			pre=cur;
		}
		//自此该序列之前的所有序列个数都累加完毕，返回该序列的位置
		return sum+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(kth("ac"));

	}

}

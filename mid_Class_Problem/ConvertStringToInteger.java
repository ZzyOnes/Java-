package mid_Class_Problem;

public class ConvertStringToInteger {
	
	public static int convert(String s) {
		if(s == null||s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		if(!isValid(str)) {
			throw new RuntimeException("Can not convert");
		}
		boolean neg = str[0]=='-'?true:false;//判断是正数还是负数
		int minq = Integer.MAX_VALUE/10;
		int minr = Integer.MAX_VALUE%10;
		int res = 0;
		int cur = 0;
		for(int i=neg?1:0;i<str.length;i++) {
			cur = '0'-str[i];//因为负数比正数绝对值大一，所以用负数来判断是否越界
			if(res<minq||(res==minq&&cur<minr)) {
				throw new RuntimeException("Can not convert");
			}
			res = res*10 + cur;
		}
		if(res==Integer.MIN_VALUE&&!neg) {
			throw new RuntimeException("Can not convert");
		}
		return neg?res:-res;
	}
	
	//检查一个字符串是否符合日常数字书写标准
	public static boolean isValid(char[] str) {
		if(str[0]!='-'&&(str[0]<'0'||str[0]>'9')) {
			return false;
		}
		if(str[0]=='-'&&(str.length==1||str[1]==0)) {
			return false;
		}
		if(str[0]=='0'&&str.length>1) {
			return false;
		}
		for(int i=1;i<str.length;i++) {
			if(str[i]<'0'||str[i]>'9')
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test1 = convert("-5698");
		int test2 = convert("5698");
		int test3 = convert("165479879");
		System.out.println(test1+" "+test2+" "+test3+" ");
		int test4 = convert("-56)98");
		int test5 = convert("-5698644978946");
		int test6 = convert("-");
		System.out.println(test4+" "+test5+" "+test6);

	}

}

package class05;

public class GetMax {
	
	//保证参数n不是0就是1,互斥函数
	public static int flip(int n) {//将0变成1，1变成0
		return n^1;
	}
	//n为负数返回0，n为正数返回1
	public static int sign(int n) {
		return flip(n>>31&1);//将符号为与1相与
	}
	
	//a-b结果不溢出的情况可以用这个方法
	public static int getMax1(int a,int b) {
		int c = a - b;
		int scA = sign(c);//a>b,ma就等于1否则为0
		int scB = flip(scA);//b>a,mb就等于1否则为0
		return a*scA + b*scB;
	}

	//a-b结果溢出也能用
	//将if else的形式变成a*sa+b*sb的形式，其中sa与sb互斥
	public static int getMax2(int a,int b) {
		int c = a-b;
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		int difSab = sa^sb;//a和b的符号不同返回1，可能会溢出
		int samSab = flip(difSab);//a和b的符号相同返回1，一定不会溢出
		int returnA = difSab*sa + samSab*sc;//可能溢出的看a的符号位，一定不溢出的看差值的符号位
		int returnB = flip(returnA);
		return a*returnA + b*returnB;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package class05;

public class GetMax {
	
	//��֤����n����0����1,���⺯��
	public static int flip(int n) {//��0���1��1���0
		return n^1;
	}
	//nΪ��������0��nΪ��������1
	public static int sign(int n) {
		return flip(n>>31&1);//������Ϊ��1����
	}
	
	//a-b��������������������������
	public static int getMax1(int a,int b) {
		int c = a - b;
		int scA = sign(c);//a>b,ma�͵���1����Ϊ0
		int scB = flip(scA);//b>a,mb�͵���1����Ϊ0
		return a*scA + b*scB;
	}

	//a-b������Ҳ����
	//��if else����ʽ���a*sa+b*sb����ʽ������sa��sb����
	public static int getMax2(int a,int b) {
		int c = a-b;
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		int difSab = sa^sb;//a��b�ķ��Ų�ͬ����1�����ܻ����
		int samSab = flip(difSab);//a��b�ķ�����ͬ����1��һ���������
		int returnA = difSab*sa + samSab*sc;//��������Ŀ�a�ķ���λ��һ��������Ŀ���ֵ�ķ���λ
		int returnB = flip(returnA);
		return a*returnA + b*returnB;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

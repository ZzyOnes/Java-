package class05;

public class AddMinusMultiDivideByBit {

	//a+b�����
	public static int add(int a,int b) {
		int sum = a;
		while(b != 0) {//��λ��ϢΪ0ʱ����
			sum = a^b;//�޽�λ���
			b = (a&b)<<1;//��λ��Ϣ
			a = sum;
		}
		return sum;
	}
	//a���෴����aȡ����һ
	public static int negNum(int n) {
		return add(1,~n);
	}
	//a-b
	public static int minus(int a,int b) {
		return add(a,negNum(b));
	}
	//a*b
	public static int multi(int a,int b) {
		int res = 0;
		while(b != 0) {
			if((b&1)!= 0) {
				res = add(res,a);
			}
			a = a<<1;
			b = b>>>1;
		}
		return res;
	}
	
	public static boolean isNeg(int n) {
		return n<0;
	}
	//a/b
	public static int divide(int a,int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for(int i=31;i>=0;i=minus(i,1)) {
			if((x>>i)>=y) {
				res |= (1<<i);
				x = minus(x,y<<i);
			}
		}
		return isNeg(a)^isNeg(b) ? negNum(res) : res;//ͬ��Ϊres,���ΪnegNum(res)
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

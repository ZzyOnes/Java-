package mid_Class_Problem;

/*
 * ţ��Բݣ�һ��ֻ�ܳ�4^k�ݲ�
 * �ȳ����Ӯ
 */
public class Cow_eat {
	
    //ɵ�Ϸ���,�ݹ�
	public static String winner1(int n) {
		if(n<5) {
			return (n==0)||(n==2) ? "����":"����";
		}
		int base=1;
		while(base<=n) {
			if(winner1(n-base)=="����") {
				return "����";
			}
			if(base>n/4) {
				break;
			}
			base*=4;
		}
		return "����";
	}
	
	//���ݴ���ó����
	public static String winner2(int n) {
		return n%5==0?"����":(n%5==1?"����":(n%5==2?"����":(n%5==3?"����":(n%5==4?"����":(n%5==0?"����":"����")))));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=50;
		//���
		for(int i=0;i<=n;i++) {
			System.out.println(i+":"+winner1(i));
		}
		for(int i=0;i<=n;i++) {
			System.out.println(i+":"+winner2(i));
		}

	}

}

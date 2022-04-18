package mid_Class_Problem;

/*
 * 牛羊吃草，一次只能吃4^k份草
 * 先吃完的赢
 */
public class Cow_eat {
	
    //傻瓜方法,递归
	public static String winner1(int n) {
		if(n<5) {
			return (n==0)||(n==2) ? "后手":"先手";
		}
		int base=1;
		while(base<=n) {
			if(winner1(n-base)=="后手") {
				return "先手";
			}
			if(base>n/4) {
				break;
			}
			base*=4;
		}
		return "后手";
	}
	
	//根据打表后得出结果
	public static String winner2(int n) {
		return n%5==0?"后手":(n%5==1?"先手":(n%5==2?"后手":(n%5==3?"先手":(n%5==4?"先手":(n%5==0?"后手":"先手")))));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=50;
		//打表
		for(int i=0;i<=n;i++) {
			System.out.println(i+":"+winner1(i));
		}
		for(int i=0;i<=n;i++) {
			System.out.println(i+":"+winner2(i));
		}

	}

}

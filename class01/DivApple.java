//用装6个或8个苹果的袋子装苹果，刚好装下返回最优袋子数否则返回-1
package class01;
import java.util.*;
public class DivApple {
	//分析法
	public static int minBags(int Apple) {
		if(Apple<0) {
			return -1;
		}
		int bag6 = -1;
		int bag8 = Apple/8;
		int rest = Apple - bag8*8;
		while(bag8>=0&&rest<24) {
			if(minBags6(rest)!=-1) {
				bag6 = minBags6(rest);
				break;
			}
			rest = Apple - (--bag8)*8;
		}
		return bag6==-1?-1:bag8+bag6;
	}
	public static int minBags6(int rest) {
		return rest%6==0 ? rest/6 : -1;
	}
	//答表法
	public static int minBagsAwesome(int Apple) {
		if(Apple%2!=0)return -1;
		if(Apple<18) {
			return Apple==0?0:(Apple==6||Apple==8)?1:(Apple==12||Apple==14||Apple==16)?2:-1;
		}
		return (Apple-18)/8+3;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int Apple=1;
		while(Apple!=0) {
			Apple = sc.nextInt();
			System.out.print(minBags(Apple));
		}
	}

}

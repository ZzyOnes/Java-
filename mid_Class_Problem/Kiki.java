package mid_Class_Problem;
/*
 * 主播人气 cur 恰好达到目标aim 所花费的最少money
 * 其中给主播点赞需要花费x 获得人气+2
 * 给主播送礼需要花费y 获得人气*2
 * 私聊需要花费z 获得人气-2
 */
public class Kiki {
	
	//递归模型，没有优化之前条件不足无法退出
	public static int fu(int aim,int cur,int x,int y,int z) {
		if(cur==aim)return 0;
		//点赞
		int d = fu(aim,cur+2,x,y,z)+x;
		//送礼
		int s = fu(aim,cur*2,x,y,z)+y;
		//私聊
		int l = fu(aim,cur-2,x,y,z)+z;
		return Math.min(d,Math.min(s, l));
	}
	
	//进行优化，找到递归出口，1. 找到一个平凡解即：全部进行点赞可以达到目标所花费的money
	// 2. 当前人气不会超过目标人气的一倍
	public static int process(int premoney,int aim, int x, int y, 
			          int z, int cur, int limitAim, int limitMoney ) {
		if(cur==aim) {
			return premoney;
		}
		if(cur<0) {
			return Integer.MAX_VALUE;
		}
		if(cur>limitAim) {
			return Integer.MAX_VALUE;
		}
		if(premoney>limitMoney) {
			return Integer.MAX_VALUE;
		}
		int min = Integer.MAX_VALUE;
		//点赞
		int d = process(premoney+x,aim,x,y,z,cur+2,limitAim,limitMoney);
		if(d!=Integer.MAX_VALUE) {
			min = d;
		}
		//送礼
		int s = process(premoney+y,aim,x,y,z,cur*2,limitAim,limitMoney);
		if(s!=Integer.MAX_VALUE) {
			min = Math.min(min, s);
		}
		//私聊
		int l = process(premoney+z,aim,x,y,z,cur-2,limitAim,limitMoney);
		if(l!=Integer.MAX_VALUE) {
			min = Math.min(min, l);
		}
		return min;
	}
	
	public static int minCoins(int x, int y, int z, int start, int end) {
		if(start>end) {
			return -1;
		}
		return process(0,end,x,y,z,start,2*end,(end-start)/2*x);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

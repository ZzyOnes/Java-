package mid_Class_Problem;
/*
 * �������� cur ǡ�ôﵽĿ��aim �����ѵ�����money
 * ���и�����������Ҫ����x �������+2
 * ������������Ҫ����y �������*2
 * ˽����Ҫ����z �������-2
 */
public class Kiki {
	
	//�ݹ�ģ�ͣ�û���Ż�֮ǰ���������޷��˳�
	public static int fu(int aim,int cur,int x,int y,int z) {
		if(cur==aim)return 0;
		//����
		int d = fu(aim,cur+2,x,y,z)+x;
		//����
		int s = fu(aim,cur*2,x,y,z)+y;
		//˽��
		int l = fu(aim,cur-2,x,y,z)+z;
		return Math.min(d,Math.min(s, l));
	}
	
	//�����Ż����ҵ��ݹ���ڣ�1. �ҵ�һ��ƽ���⼴��ȫ�����е��޿��ԴﵽĿ�������ѵ�money
	// 2. ��ǰ�������ᳬ��Ŀ��������һ��
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
		//����
		int d = process(premoney+x,aim,x,y,z,cur+2,limitAim,limitMoney);
		if(d!=Integer.MAX_VALUE) {
			min = d;
		}
		//����
		int s = process(premoney+y,aim,x,y,z,cur*2,limitAim,limitMoney);
		if(s!=Integer.MAX_VALUE) {
			min = Math.min(min, s);
		}
		//˽��
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

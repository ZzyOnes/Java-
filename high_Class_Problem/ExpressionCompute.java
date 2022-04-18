package high_Class_Problem;

import java.util.LinkedList;

/*
 * ����һ�����ʽ�ַ������������ֵ�磺40*12/(6+2*7)+2/(6-4)  ����25  
 */

public class ExpressionCompute {
	
	public static int getValue(String str) {
		return value(str.toCharArray(),0)[0];
	}
	//��str[...i]�����������ַ�����ֹλ�û������ž�ֹͣ
	//��������ֵ������Ϊ2������
	//0�����ؽ��
	//1: ���ؼ��㵽����һ��λ��
	public static int[] value(char[] str, int i) {
		LinkedList<String> que = new LinkedList<>(); //˫�˶��У���˫�˶��з���������
		int num = 0;
		int[] bra = null;
		while(i<str.length&&str[i]!=')') {
			if(str[i]>='0' && str[i]<='9') {
				num = num*10 + str[i++]-'0';
			}else if(str[i]!='(') {//���������������
				addNum(que , num);
				que.addLast(String.valueOf(str[i++]));
				num = 0;
			}else {
				bra = value(str,i+1);
				num = bra[0];
				i = bra[1]+1;
			}
		}
		addNum(que,num);
		return new int[] {getNum(que),i};
	}

	//˫�˶�������������ȫ���ɼӼ��������ˣ�����ԭʼ˳�����Ӽ��� ���ؼ�����
	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		int num = 0;
		while(!que.isEmpty()) {
			String cur = que.pollFirst();
			if(cur.equals("+")) {
				add = true;
			}else if(cur.equals("-")) {
				add = false;
			}else {
				num = Integer.valueOf(cur);
				res += add ? num:(-num);
			}
		}
		return res;
	}
	//��num�ӵ�ջ��
	public static void addNum(LinkedList<String> que, int num) {
		if(!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();//����ջ��Ԫ��
			if(top.equals("+")||top.equals("-")) {
				que.addLast(top);
			}else {
				cur = Integer.valueOf(que.pollLast());
				num = top.equals("*")?(cur*num):(cur/num);
			}
		}
		que.addLast(String.valueOf(num));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "(40*12/((38-4)-2*7)+2/(3-1))";
		System.out.println(getValue(test));
	}

}

package high_Class_Problem;

import java.util.LinkedList;

/*
 * 给你一个表达式字符串计算出它的值如：40*12/(6+2*7)+2/(6-4)  返回25  
 */

public class ExpressionCompute {
	
	public static int getValue(String str) {
		return value(str.toCharArray(),0)[0];
	}
	//从str[...i]往下算遇到字符串终止位置或右括号就停止
	//返回两个值，长度为2的数组
	//0：返回结果
	//1: 返回计算到了哪一个位置
	public static int[] value(char[] str, int i) {
		LinkedList<String> que = new LinkedList<>(); //双端队列，用双端队列方便计算减法
		int num = 0;
		int[] bra = null;
		while(i<str.length&&str[i]!=')') {
			if(str[i]>='0' && str[i]<='9') {
				num = num*10 + str[i++]-'0';
			}else if(str[i]!='(') {//遇到的是运算符号
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

	//双端队列中所有数字全部由加减号连接了，按照原始顺序计算加减法 返回计算结果
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
	//把num加到栈中
	public static void addNum(LinkedList<String> que, int num) {
		if(!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();//弹出栈顶元素
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

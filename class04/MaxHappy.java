package class04;

import java.util.List;

public class MaxHappy {

	public static class Employee{
		public int happy;//该员工的快乐值
		public List<Employee> nexts;//该员工有哪些下级
	}
	
	public static class Info{
		public int laiMaxHappy;
		public int bulaiMaxHappy;
		
		public Info(int a,int b) {
			this.bulaiMaxHappy = b;
			this.laiMaxHappy = a;
		}
	}
	
	public static int maxHappy(Employee boss) {
		Info headInfo = process(boss);
		return Math.max(headInfo.laiMaxHappy,headInfo.bulaiMaxHappy);
	}
	
	public static Info process(Employee x) {
		if(x.nexts.isEmpty()) {
			return new Info(x.happy,0);
		}
		int lai = x.happy;
		int bulai = 0;
		for(Employee next:x.nexts) {
			Info nextInfo = process(next);
			lai += nextInfo.bulaiMaxHappy;
			bulai += Math.max(nextInfo.bulaiMaxHappy,nextInfo.laiMaxHappy);
		}
		return new Info(lai,bulai);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

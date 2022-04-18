package mid_Class_Problem;
/*
 * 给你一个Job数组和一个ability数组，自有当ability[i]>=Job[i].hard才能获得Job[i].Money，找到每个人能获得的最大money
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ChooseWork {
	
	public class Job{
		public int money;
		public int hard;
		public Job(int m, int h) {
			this.money = m;
			this.hard = h;
		}
	}
	
	public static class JobComparator implements Comparator<Job>{
		@Override
		public int compare(Job o1,Job o2) {
			return o1.hard!=o2.hard ? (o1.hard-o2.hard) : (o2.money-o1.money);
		}
	}
	
	public static int[] getMoney(Job[] job, int[] ability) {
		Arrays.sort(job,new JobComparator());
		//难度为key的工作，最优钱数是多少，有序表
		TreeMap<Integer,Integer> Map = new TreeMap<>();
		Map.put(job[0].hard,job[0].money);
		Job pre = job[0];
		for(int i=1;i<job.length;i++) {
			if(job[i].hard>pre.hard&&job[i].money>pre.money) {
				pre = job[i];
				Map.put(pre.hard, pre.money);
			}
		}
		int[] ans = new int[ability.length];
		for(int i=0;i<ability.length;i++) {
			Integer key = Map.floorKey(ability[i]);
			ans[i] = key==null ? 0 : Map.get(key);
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

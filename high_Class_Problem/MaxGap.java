package high_Class_Problem;
/*
 * 用时间复杂度为O(n)的方法，找到给定数组如果按从小到大排序时相邻两数之差的最大值并返回
 */
public class MaxGap {

	//运用的原理就是只需比较桶与桶之间的数的最大值与最小值，而不需要比较桶内部的值
	public static int maxGap(int[] nums) {
		if(nums==null||nums.length==0) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<len;i++) {
			min = Math.min(min,nums[i]);
			max = Math.max(max,nums[i]);
		}
		if(min==max) {
			return 0;
		}
		boolean[] hasNum = new boolean[len+1]; //表示第i号桶是否来过数字
		int[] maxs = new int[len+1]; //表示第i号桶的最大值
		int[] mins = new int[len+1]; //表示第i号桶的最小值
		int bid = 0;//表示桶号
		//开始进桶
		for(int i=0;i<len;i++) {
			bid = bucket(nums[i],len,min,max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		//开始计数
		int res=0;
		int lastMax = maxs[0];//前一个非空桶的最大值
		for(int i=1;i<len+1;i++) {
			if(hasNum[i]) {//如果来到的桶非空
				res = Math.max(mins[i]-lastMax, res);
				lastMax = maxs[i];
			}
		}
		return res;
	}
	public static int bucket(int num,int len,int min,int max) {
		return (len*(num-min)/(max-min));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

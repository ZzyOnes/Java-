package high_Class_Problem;
/*
 * ��ʱ�临�Ӷ�ΪO(n)�ķ������ҵ����������������С��������ʱ��������֮������ֵ������
 */
public class MaxGap {

	//���õ�ԭ�����ֻ��Ƚ�Ͱ��Ͱ֮����������ֵ����Сֵ��������Ҫ�Ƚ�Ͱ�ڲ���ֵ
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
		boolean[] hasNum = new boolean[len+1]; //��ʾ��i��Ͱ�Ƿ���������
		int[] maxs = new int[len+1]; //��ʾ��i��Ͱ�����ֵ
		int[] mins = new int[len+1]; //��ʾ��i��Ͱ����Сֵ
		int bid = 0;//��ʾͰ��
		//��ʼ��Ͱ
		for(int i=0;i<len;i++) {
			bid = bucket(nums[i],len,min,max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		//��ʼ����
		int res=0;
		int lastMax = maxs[0];//ǰһ���ǿ�Ͱ�����ֵ
		for(int i=1;i<len+1;i++) {
			if(hasNum[i]) {//���������Ͱ�ǿ�
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

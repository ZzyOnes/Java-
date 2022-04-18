package mid_Class_Problem;
//给你一串数组，每次只允许每个位置的数变化为+-1，求数组中所有数都相等时移动的最少步数
//如： [4,0,1,3]->[3,1,2,2]->[2,2,2,2]最少步数为2
public class PackingMachine {

	public static int MinOps(int[] arr) {
		if(arr == null||arr.length == 0) {
			return 0;
		}
		int size = arr.length;
		int sum = 0;
		for(int i=0;i<size;i++) {
			sum += arr[i];
		}
		if(sum%size!=0) {
			return -1;
		}
		int avg = sum/size;
		int leftsum = 0;
		int ans = 0;
		for(int i=0;i<size;i++) {
			//左侧整体需要移动的数量
			int leftrest = leftsum - avg*i;
			//右侧整体需要移动的数量
			int rightrest = sum - leftsum - arr[i] - avg*(size-i-1);
			if(leftrest<0&&rightrest<0) {
				ans = Math.max(ans, Math.abs(leftrest)+Math.abs(rightrest));
			}else {
				ans = Math.max(ans, Math.max(Math.abs(leftrest),Math.abs(rightrest)));
			}
			leftsum+=arr[i];
		}
		return ans;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

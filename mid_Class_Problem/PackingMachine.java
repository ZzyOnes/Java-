package mid_Class_Problem;
//����һ�����飬ÿ��ֻ����ÿ��λ�õ����仯Ϊ+-1���������������������ʱ�ƶ������ٲ���
//�磺 [4,0,1,3]->[3,1,2,2]->[2,2,2,2]���ٲ���Ϊ2
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
			//���������Ҫ�ƶ�������
			int leftrest = leftsum - avg*i;
			//�Ҳ�������Ҫ�ƶ�������
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

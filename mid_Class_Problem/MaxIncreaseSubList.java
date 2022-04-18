package mid_Class_Problem;
//��������������������
public class MaxIncreaseSubList {

	
	//dp[i] ��ʾ��arr[i]��β�������������г���Ϊ����
	public static int[] findMaxSubList(int[] arr) {
		if(arr==null||arr.length==0) {
			return null;
		}
		int n = arr.length;
		int[] dp = new int[n];
		dp[0]=1;
		dp[1]=arr[1]>arr[0]?2:1;
		int Maxdp = dp[1];
		int index = 1;
		int[] result = {Maxdp,index};
		for(int i=1;i<n;i++) {//�������̿����Ż�
			int max = 0;
			int flag = 0;
			for(int j=0;j<=i;j++) {
				if(arr[i]>arr[j]&&dp[j]>max) {
					dp[i] = dp[j] + 1;
					max = dp[j];
					flag = 1;
				}
			}
			if(flag==0) {
				dp[i]=1;
			}
			if(dp[i]>Maxdp) {
				Maxdp=dp[i];
				index=i;
			}
			result[0]=Maxdp;result[1]=index;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {5,4,1,2,8,9,7,10,12,13,13,7,6};
		int[] res = findMaxSubList(test);
		if(res==null)
		System.out.print("����Ϊ��");
		System.out.print("������"+test[res[1]]+"��β�ĵ������������Ϊ"+res[0]+"��λ��"+res[1]);

	}

}

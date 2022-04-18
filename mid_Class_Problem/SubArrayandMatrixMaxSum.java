package mid_Class_Problem;
// 1.�������������������ۼӺ�    2.��������Ӿ��������ۼӺ�
public class SubArrayandMatrixMaxSum {
	//����maxΪ�������ۼӺ�����ҳ����
	public static int arrySumMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		int cur = 0;//�����ۼӺ�
		for(int i=0;i<arr.length;i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur<0?0:cur;//�����ǰ�ۼӺ�С��0��˵��������û�й��ף�Ӧ����0��ʼ
		}
		return max;
	}
	
	//���Ӿ�������ۼӺ�
	public static int matrixSumMax(int[][] m) {
		if(m==null||m.length==0||m[0].length==0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int[] s = null;
		//����ÿ���ӵ�i�е���j�еľ�����ѹ��������s�����������������������ۼӺͣ���󷵻������ۼӺ������Ǹ�
		for(int i=0;i<m.length;i++) {
			 s = new int[m[0].length];
			for(int j=i;j<m.length;j++) {
				int cur = 0;
				for(int k=0;k<s.length;k++) {
					s[k] += m[j][k];//ѹ������ļ���
					cur+=s[k];//��ѹ����ͬʱ����ͬʱ����
					max = Math.max(cur, max);
					cur = cur<0?0:cur;
				}
			}
		}
		return max;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

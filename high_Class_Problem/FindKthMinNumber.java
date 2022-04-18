package high_Class_Problem;
/*
 * ���������������飬�ҵ������������е�kС����
 */
public class FindKthMinNumber {
	
	public static int findKthNum(int[] arr1,int[] arr2,int k) {
		if(arr1==null||arr2==null) {
			throw new RuntimeException("You arr is invalid");
		}
		if(k<1||k>(arr1.length+arr2.length)) {
			throw new RuntimeException("You K is invalid");
		}
		int[] longs = arr1.length>=arr2.length? arr1 : arr2;
		int[] shorts = arr1.length<arr2.length? arr1 : arr2;
		int l = longs.length;
		int s = shorts.length;
		if(k<=s) {
			return getUpMedian(shorts,0,k-1,longs,0,k-1);
		}
		if(k>l) {
			if(shorts[k-1-l]>=longs[l-1]) {
				return shorts[k-1-l];
			}
			if(longs[k-1-s]>=shorts[s-1]) {
				return longs[k-1-s];
			}
			return getUpMedian(shorts,k-l,s-1,longs,k-s,l-1);
		}
		//��3�����
		if(longs[k-s-1]>shorts[s-1]) {
			return longs[k-s-1];
		}
		return getUpMedian(shorts,0,s-1,longs,k-s,k-1);
	}
	//����arr1��arr2�е�����λ��
	//arr1[s1...e1]��arr2[s2...e2]һ��Ҫ�ȳ�
	private static int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
		int mid1=0;
		int mid2=0;
		int offset=0;
		while(s1<e1) {
			mid1=(s1+e1)/2;
			mid2=(s2+e2)/2;
			offset=((e1-s1+1)&1)^1;//����Ϊż��Ϊ1��Ϊ����Ϊ0
			if(arr1[mid1]>arr2[mid2]) {
				e1=mid1;
				s2=mid2+offset;
			}else if(arr1[mid1]<arr2[mid2]) {
				e2=mid2;
				s1=mid1+offset;
			}else {
				return arr1[mid1];
			}
		}
		return Math.min(arr1[mid1], arr2[mid2]);
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

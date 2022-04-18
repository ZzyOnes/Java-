package ���ݽṹ;
/*
 * ��������
 */
public class CountingSort {
	
	//����һ�������һ����K, ��֤�����е�ÿ������������k
	//�������������res���
	public static int[] countingSort1(int[] arr,int k) {
		int[] temp = new int[k+1];
		int[] res = new int[arr.length];
		for(int j=0;j<arr.length;j++) {
			temp[arr[j]]++;
		}
		for(int i=1;i<=k;i++) {
			temp[i] += temp[i-1];
		}
		for(int j=0;j<arr.length;j++) {
			res[--temp[arr[j]]] = arr[j];
		}
		return res;
	}
	
	//����һ�������һ����K, ��֤�����е�ÿ������������k
	public static void countingSort2(int[] arr,int k) {
		int[] temp = new int[k+1];
		for(int j=0;j<arr.length;j++) {
			temp[arr[j]]++;
		}
		int index=0;
		for(int i=0;i<temp.length;i++) {
			while(temp[i]-->0) {
				arr[index++] = i;
			}
		}
	}
	
	public static void PrintArray(int[] res) {
		for(int i=0;i<res.length;i++) {
			System.out.print(res[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test1 = {2,5,3,0,2,3,0,3,4,1,5};
		int[] res1 = countingSort1(test1,5);
		int[] test2 = {2,5,3,0,2,3,0,3,4,1,5,8,7};
		countingSort2(test2,8);
		PrintArray(test2);
		PrintArray(res1);
	}

}

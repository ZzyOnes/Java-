package 数据结构;
/*
 * 计数排序
 */
public class CountingSort {
	
	//给定一个数组和一个数K, 保证数组中的每个数都不超过k
	//将排完序的数组res输出
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
	
	//给定一个数组和一个数K, 保证数组中的每个数都不超过k
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

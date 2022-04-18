package 数据结构;

public class SelectSort {
	
	public static void selectSort(int[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			int key = arr[i];
			int min = key;
			int index = 0;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]<min) {
					min = arr[j];
				    index = j;
				}
			}
			if(index!=0) {
				arr[i] = min;
				arr[index] = key;
			}
			printArr(arr,i+1);
		}
	}
	
	public static void printArr(int[] arr,int n) {
		System.out.println("第"+n+"次交换结果" );
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {5,2,4,6,1,3,6,8,8,7,2};
		selectSort(test);
	}

}

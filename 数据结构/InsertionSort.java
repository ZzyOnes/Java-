package 数据结构;

public class InsertionSort {
	
	public static void insertionSort(int[] arr) {
		for(int j=1;j<arr.length;j++) {
			int key = arr[j];
			int i = j-1;
			while(i>=0&&arr[i]<key) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key; 
			printArr(arr,j);
		}
	}
	
	public static void printArr(int[] arr,int n) {
		System.out.println("第"+n+"次交换结果" );
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	//递归版本
	//将a,b排好顺序
	public static void insertSort(int[] arr, int a, int b, int num) {
		if(a==b) {
			return ;
		}
		insertSort(arr,a,b-1, num-1);
		process(arr,a,b);
		printArr(arr,num-1);
		return ;
	}
	//arr[a...b-1]有序，将arr[b]插入其中
	public static void process(int[] arr, int a, int b) {
		int temp = arr[b];
		int i=b-1;
		for(;i>=a;i--) {
			if(arr[i]>temp) {
				arr[i+1] = arr[i];
			}else {
				break ;
			}
		}
		arr[i+1] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {5,2,4,6,1,3};
		insertionSort(test);
		int[] test1 = {5,2,4,6,1,3};
		insertSort(test1,0,test1.length-1, test1.length);
	}
}

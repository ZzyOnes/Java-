package 数据结构;
import 数据结构.CreatRandomArray;
public class MergeSort {
	
	public static int merge(int[] arr,int p,int q,int r) {
		int n1 = q-p+1; //左边个数
		int n2 = r-q; //右边个数
		int reverse = 0;
		int[] L = new int[n1+1];
		int[] R = new int[n2+1];
		for(int i=0;i<n1;i++) {
			L[i] = arr[p+i];
		}
		for(int i=0;i<n2;i++) {
			R[i] = arr[q+1+i];
		}
		L[n1] = Integer.MAX_VALUE;//哨兵
		R[n2] = Integer.MAX_VALUE;
		int i=0;int j=0;
		for(int k=p;k<=r;k++) {
			if(R[j]<L[i]) {
				arr[k] = R[j++];
				reverse += n1-i;
			}else {
				arr[k] = L[i++];
			}
		}
		return reverse;
	}
	
	
	//返回的为逆序数
	public static int mergeSort(int[] arr,int p,int r,int num) {
		if(p==r) {
			return 0;
		}
		int q = (p+r)/2;
		int left = mergeSort(arr,p,q,num+1);
		int right = mergeSort(arr,q+1,r,num+1);
		int reverse = merge(arr,p,q,r);
		printArr(arr,num);
		return left+right+reverse;
	}

	public static void printArr(int[] arr,int n) {
		System.out.println("倒数第"+n+"次归并结果" );
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreatRandomArray cra = new CreatRandomArray();
		int[] test = {1,2,3,4,5,6,7,8,9};
		float res = 0;
		for(int i=0;i<100;i++) {
			cra.creatRandomArr(test);
			int reverseNum = mergeSort(test,0,test.length-1,1);
			res += reverseNum/1.00;
		}
		res /= 100.00;
		System.out.println("其逆序数期望为："+res);
	}

}

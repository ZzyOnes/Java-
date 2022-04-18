package 数据结构;

import java.util.Arrays;
import java.util.Random;

/*
 * 快速排序
 */
public class QuickSort {
	
	public static void quickSort(int[] arr,int p,int r) {
		if(p>=r) {
			return ;
		}
		int q = partition(arr,p,r);
		quickSort(arr,p,q-1);
		quickSort(arr,q+1,r);
		return ;
	}

	//以arr[r]作为基数，将arr[r]放入最终位置然后放回其下标
	public static int partition(int[] arr, int p, int r) {
		int i = p-1;
		for(int j=p;j<r;j++) {
			if(arr[j]<arr[r]) {
				i++;
				swap(arr,i,j);
			}
		}
		i++;
		swap(arr,i,r);
		return i;
	}
	
	//随机快速排序
	public static int randomPartition(int[] arr,int p, int r) {
		Random rand = new Random();
		swap(arr,rand.nextInt(r-p)+p,r);
		return partition(arr,p,r);
	}
	public static void randomQuickSort(int[] arr,int p,int r) {
		if(p>=r) {
			return ;
		}
		while(p<r) { //尾递归
			int q = randomPartition(arr,p,r);
			randomQuickSort(arr,p,q-1);
			p = q + 1;
		}
		//randomQuickSort(arr,q+1,r);
		return ;
	}
	
	//最早的快排版本hoarePartition
	public static int hoarePartition(int[] arr,int p,int r) {
		int x = arr[p];
		int i = p;
		int j = r;
		while(true) {
			while(arr[j]>=x) {
				j--;
			}
			while(arr[i]<=x) {
				i++;
			}
			if(i<j) {
				swap(arr,i,j);
			}else {
				swap(arr,p,j);
				return j;
			}
		}
	}
	public static void quickSort2(int[] arr,int p,int r) {
		if(p>=r) {
			return ;
		}
		int q = hoarePartition(arr,p,r);
		quickSort(arr,p,q-1);
		quickSort(arr,q+1,r);
		return ;
	}
	
	//加强版快速排序，左边为小于arr[r] 中间为等于arr[r] 右边为大于arr[r]
	//以arr[r]作为基数，将arr[r]放入最终位置然后返回等于arr[r]的闭区间
	public static int[] partition_(int[] arr, int p, int r) {
			int i = p-1;
			int mid = p-1;
			for(int j=p;j<r;j++) {
				if(arr[j]<arr[r]) {
					i++;
					mid++;
					if(i==mid)
						swap(arr,i,j);
					else if(mid==j&&i!=p)
						swapTree(arr,i-1,mid-1,j);
					else if(mid==j&&i==p)
						swap(arr,i,j);
					else
						swapTree(arr,i,mid,j);
				} 
				else if(arr[j]==arr[r]) {
					mid++;
					swap(arr,mid,j);
				}
			}
			i++;
			mid++;
			swap(arr,mid,r);
			return new int[]{i,mid};
	}
	
	//随机快速排序加强版
	public static int[] randomPartition_(int[] arr,int p, int r) {
			Random rand = new Random();
			swap(arr,rand.nextInt(r-p)+p,r);
			return partition_(arr,p,r);
	}
	
	public static void randomQuickSort_(int[] arr,int p,int r) {
		if(p>=r) {
			return ;
		}
		int[] res = randomPartition_(arr,p,r);
		randomQuickSort_(arr,p,res[0]-1);
		randomQuickSort_(arr,res[1]+1,r);
		return ;
	}		
	
	public static void swapTree(int[] arr,int i,int j,int k) {
		int temp = arr[i];
		arr[i] = arr[k];
		arr[k] = arr[j];
		arr[j] = temp;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test1 = {2,1,5,9,8,7,6,5,4,3,2,1,52,5,2,5,4,7};
		quickSort2(test1,0,test1.length-1);
		int[] test2 = {2,1,5,9,8,7,6,5,4,3,2,1,52,5,2,5,4,7};
		randomQuickSort(test2,0,test2.length-1);
		int[] test3 = {2,1,5,9,8,7,6,5,-1,1004,3,2,1,52,5,2,5,4,7};
		int[] test4 = new int[100];
		Random rand = new Random();
		for(int i=0;i<100;i++) {
			test4[i] = rand.nextInt(100);
			if(rand.nextBoolean()) {
				test4[i] = -test4[i];
			}
		}
		randomQuickSort_(test3,0,test3.length-1);
		randomQuickSort_(test4,0,test4.length-1);
		System.out.println(Arrays.toString(test1));
		System.out.println(Arrays.toString(test2));
		System.out.println(Arrays.toString(test3));
		System.out.println(Arrays.toString(test4));
	}
}

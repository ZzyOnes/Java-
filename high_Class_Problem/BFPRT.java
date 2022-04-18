package high_Class_Problem;

import java.util.Arrays;

/*
 * 给你一个无序数组，求第k小的数
 */
public class BFPRT {
	
	public static int bfprt(int[] arr, int k) {
		return select(arr,0,arr.length-1,k-1);
	}
	
	//在arr[begin...end]范围上，如果排序的话求i位置上的数是谁
	//i一定在begin到end 的范围上
	public static int select(int[] arr,int begin,int end,int i) {
		if(begin==end) {
			return arr[begin];
		}
		//分组+选出组内的中位数组成新的newarr+选出新的newarr的上中位数pivot
		int pivot = medianOfMedians(arr,begin,end);
		//根据pivot做划分值 <p =P >p
		//pivotRange[0]区域的左边界
		//pivotRange[1]区域的右边界
		int[] pivotRange = partition(arr,begin,end,pivot);
		if(i>=pivotRange[0]&&i<=pivotRange[1]) {
			return arr[i];
		}else if(i<pivotRange[0]) {
			return select(arr,begin,pivotRange[0],i);
		}else {
			return select(arr,pivotRange[1],end,i);
		}
	}
	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin -1;
		int cur = begin;
		int big = end + 1;
		while(cur != big) {
			if(arr[cur] < pivotValue) {
				swap(arr,++small,cur++);
			}else if(arr[cur] > pivotValue) {
				swap(arr,--big,cur);
			}else {
				cur++;
			}
		}
		int[] pivotRange = new int[2];
		pivotRange[0] = small;
		pivotRange[1] = big;
		return pivotRange;
	}
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end-begin+1;
		int offset = num%5==0 ? 0:1;
		int[] mArr = new int[num/5+offset];
		for(int i=0;i<mArr.length;i++) {
			int beginI = begin + i*5;
			int endI = beginI+4;
			mArr[i] = getMedian(arr,beginI,Math.min(end,endI));
		}
		return select(mArr,0,mArr.length-1,mArr.length/2);
	}
	public static int getMedian(int[] arr, int beginI, int endI) {
		insertionSort(arr,beginI,endI);
		int mid = (endI + beginI + 1)/2;
		return arr[mid];
	}
	public static void insertionSort(int[] arr, int beginI, int endI) {
		int[] temp = new int[endI-beginI+1];
		for(int i=0;i<temp.length;i++) {
			temp[i] = arr[beginI+i];
		}
		Arrays.sort(temp);
		for(int i=0;i<temp.length;i++) {
			arr[beginI+i] = temp[i];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] testArr = {6,58,97,654,5,4,2,8,1,1,4};
		int k = 5;
		System.out.print(bfprt(testArr,k));
	}

}

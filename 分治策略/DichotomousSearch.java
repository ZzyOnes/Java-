package 分治策略;
/*
 * 二分查找递归方法
 */
public class DichotomousSearch {

	//arr已经是重小到大排序了
	public static int diSearch(int[] arr,int a,int b,int target) {
		if(a>b) {
			return -1;
		}
		int mid = (a+b)/2;
		if(arr[mid]==target) {
			return mid;
		}
		if(a==b) {
			return -1;
		}
		if(arr[mid]>target) {
			return diSearch(arr,a,mid-1,target);
		}else {
			return diSearch(arr,mid+1,b,target);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1,3,4,6,7,9,45,49,48,213,321};
		System.out.print(diSearch(test,0,test.length-1,321));
		System.out.print(diSearch(test,0,test.length-1,8));
	}

}

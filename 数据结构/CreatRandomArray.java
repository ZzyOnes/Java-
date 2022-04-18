package 数据结构;
import java.util.Random;
/*
 * 产生随机数组   
 * 产生i到n之间的随机数包括i  Random rand = new Random(); rand.nextInt(22)+5;
 */
public class CreatRandomArray {
	
	//创建随机数组
	public void creatRandomArr(int[] arr) {
		int n = arr.length;
		Random rand = new Random();
		for(int i=0;i<n;i++) {
			swap(arr,i,rand.nextInt(n-i)+i);
		}
	}
	
	public static void swap(int[] arr,int a,int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void printArr(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}
	
	//从n集合随机挑取m子集 , n中元素不一样
	public static void randomSample(int[] arr,int[] temp,int n,int m,int i) {
		if(m==0) {
			return ;
		}
		Random rand = new Random();
		randomSample(arr,temp,n-1,m-1,i+1);
		int j = rand.nextInt(n);
		if(isExit(temp,arr[j])) {
			temp[i] = arr[n-1];
		}else {
			temp[i] = arr[j];
		}
		return ;
	}
	//判断数组元素是否存在
	public static boolean isExit(int[] arr,int x) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==x) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1,3,4,6,7,9,45,49,48,213,321};
		int m = test.length/2;
		int[] temp = new int[m];
		randomSample(test,temp,test.length,m,0);
		printArr(temp);
	}

}

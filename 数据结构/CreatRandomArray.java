package ���ݽṹ;
import java.util.Random;
/*
 * �����������   
 * ����i��n֮������������i  Random rand = new Random(); rand.nextInt(22)+5;
 */
public class CreatRandomArray {
	
	//�����������
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
	
	//��n���������ȡm�Ӽ� , n��Ԫ�ز�һ��
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
	//�ж�����Ԫ���Ƿ����
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

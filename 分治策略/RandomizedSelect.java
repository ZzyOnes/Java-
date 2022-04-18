package ���β���;

import java.util.Random;

/*
 * �ҵ���iС����, ������Ԫ�ػ����������㷨������ʱ�临�Ӷ�ΪO(n)
 */
public class RandomizedSelect {
	
	//�ҵ�����arr�е�iС����
	public static int randomSelect(int[] arr,int p,int r,int i) {
		if(i>(r-p+1)||i<1) {
			return Integer.MIN_VALUE;
		}
		if(p==r) {
			return arr[p];
		}
		int q = selectPartition(arr,p,r);
		int k = q-p+1;
		if(k==i) {
			return arr[q];
		}
		if(i<k) {
			return randomSelect(arr,p,q-1,i);
		}else {
			return randomSelect(arr,q+1,r,i-k);
		}
	}
	
	//ѭ���汾
	public static int randomSelect_(int[] arr,int p,int r,int i) {
		if(i>(r-p+1)) {
			return Integer.MIN_VALUE;
		}
		while(r>=p) {
			if(p==r) {
				return arr[p];
			}
			int q = randomPartition(arr,p,r);
			int k = q-p+1;
			if(k==i) {
				return arr[q];
			}
			if(i<k) {
				r = q-1;
			}else {
				p = q+1;
				i = i-k;
			}
		}
		return arr[p];
	}
	
	public static int randomPartition(int[] arr,int p,int r) {
		Random rand = new Random();
		int temp = rand.nextInt(r-p)+p;
		swap(arr,temp,r);
		return partition(arr,p,r);
	}
	
	public static int selectPartition(int[] arr,int p, int r) {
		int temp = select(arr,p,r);
		swap(arr,temp,r);
		return partition(arr,p,r);
	}
	
	public static int partition(int[] arr,int p,int r) {
		int i = p-1;
		for(int j=p;j<r;j++) {
			if(arr[j]<=arr[r]) {
				i++;
				swap(arr,i,j);
			}
		}
		i++;
		swap(arr,i,r);
		return i;
	}
	
	public static void swap(int[] arr,int a,int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	//��select�������������������λ�����ֽ�
	//�ҵ�arr��p��r����λ�����±�
	public static int select(int[] arr,int p,int r) {
		if(r-p+1<5) {
			return (p+r)/2;
		}
		//5��Ϊһ��
		int len = (r-p+1)/5;
		int[] temp = new int[len+1];
		for(int i=0;i<len;i++) {
			insertionSort(arr,p+i*5,p+i*5+4);
			temp[i] = arr[p+i*5+2];
		}
		insertionSort(arr,p+len*5,r);
		temp[len] = arr[(r+p+len*5)/2];
		return select(temp,0,len);
	}
	
	public static void insertionSort(int[] arr,int low,int hight) {
		for(int j=low+1;j<=(hight-low+1);j++) {
			int key = arr[j];
			int i = j-1;
			while(i>=low&&arr[i]>key) {
				arr[i+1] = arr[i];
				i--;
			}
			arr[i+1] = key; 
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {5,6,8,4,3,5,3,1,6,8,9,1,1,8};
		System.out.println(randomSelect(test,0,test.length-1,test.length/2));
		System.out.println(randomSelect_(test,0,test.length-1,test.length/2));
		System.out.println(select(test,0,test.length-1));
	}

}

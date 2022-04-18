package ���ݽṹ;

/*
 * ���ȶ��У��ö�ʵ��  S����Ϊ��
 * maxHeapInsert(s,x) ��Ԫ��x���뼯��S������ѣ���
 * minHeapInsert(s,x) ��Ԫ��x���뼯��S��С���ѣ���
 * heapMaxImum(s) ���ؼ���S�о��������ֵ�Ԫ��
 * heapExtractMax(s) ȥ��������S�еľ��������ֵ�Ԫ��
 * heapIncreaseKey(s,x,k) ��Ԫ��x�Ĺؼ���ֵ���ӵ�k, �������k��ֵ��С��x��ԭ�ؼ���
 */
public class PriorityQueue {
	
	
	public static class PQueue{
		int MAX = 1000;
		int[] arr = new int[MAX];
		int heapSize = 0;
		
	}
	
	public static int heapMaxImum(PQueue queue) {
		if(queue.heapSize==0) {
			System.out.println("���ȶ���Ϊ��");
			return Integer.MIN_VALUE;
		}
		return queue.arr[0];
	}
	
	public static int heapExtractMax(PQueue queue) {
		if(queue.heapSize<1) {
			System.out.println("���ȶ���Ϊ��");
			return Integer.MIN_VALUE;
		}
		int max = queue.arr[0];
		queue.arr[0] = queue.arr[queue.heapSize-1];
		queue.heapSize = queue.heapSize - 1;
		maxHeapify(queue.arr,0,queue.heapSize);
		return max;
	}
	//����i��Ԫ�ص����ȼ���Ϊkey
	public static void heapIncreaseKey(PQueue queue, int i, int key) {
		if(i>=queue.heapSize) {
			System.out.println("Ԫ�ز�����");
			return ;
		}
		if(key<=queue.arr[i]) {
			System.out.println("�¼ӵ����ȼ���С");
			return ;
		}
		//queue.arr[i] = key;
		int root = (i+1)/2-1;
		while(root>=0&&i>0&&queue.arr[root]<key) {
//			swap(queue.arr,root,i);
//			i = root;
//			root = (i+1)/2-1;
			queue.arr[i] = queue.arr[root];
			i = root;
			root = (i+1)/2-1;
		}
		queue.arr[i] = key;
	}
	//���ؼ���key ���뵽���ȶ�����
	public static void MaxHeapInsert(PQueue queue, int key) {
		if(queue.heapSize==queue.MAX) {
			System.out.println("����Ԫ�����������滻��Сkey");
			queue.arr[queue.heapSize-1] = key;
			int i = queue.heapSize-1;
			int root = (i+1)/2-1;
			while(root>=0&&i>0&&queue.arr[root]<queue.arr[i]) {
				swap(queue.arr,root,i);
				i = root;
				root = (i+1)/2-1;
			}
			return ;
		}
		queue.heapSize += 1;
		queue.arr[queue.heapSize-1] = Integer.MIN_VALUE;
		heapIncreaseKey(queue,queue.heapSize-1,key);
	}
	public static void maxHeapify(int[] arr,int i,int heapSize) {
		int l = 2*i+1;
		int r = l+1;
		int big = i;
		if(l<heapSize&&arr[l]>arr[i]) {
			big = l;
		}
		if(r<heapSize&&arr[r]>arr[big]) {
			big = r;
		}
		if(big!=i) {
			swap(arr,big,i);
			maxHeapify(arr,big,heapSize);
		}
	}
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	public static void PrintArr(int[] arr, int size) {
		System.out.print("��������Ԫ��Ϊ��");
		for(int i=0;i<size;i++) {
			System.out.print(" "+arr[i]);
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {7,8,9,3,4,5,6,7,8,12};
		PQueue prioritQueue = new PQueue();
		for(int i=0;i<test.length;i++) {
			MaxHeapInsert(prioritQueue,test[i]);
		}
		System.out.println("���keyΪ��"+heapMaxImum(prioritQueue)+"  ���ȶ��й��У�"+prioritQueue.heapSize+"��Ԫ��");
		PrintArr(prioritQueue.arr,prioritQueue.heapSize);
		heapIncreaseKey(prioritQueue, 9, 16);
		System.out.print("��10��Ԫ�ظ�Ϊ16��");
		PrintArr(prioritQueue.arr,prioritQueue.heapSize);
	}

}

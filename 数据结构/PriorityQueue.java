package 数据结构;

/*
 * 优先队列，用堆实现  S集合为堆
 * maxHeapInsert(s,x) 把元素x插入集合S（大根堆）中
 * minHeapInsert(s,x) 把元素x插入集合S（小根堆）中
 * heapMaxImum(s) 返回集合S中具有最大键字的元素
 * heapExtractMax(s) 去掉并返回S中的具有最大键字的元素
 * heapIncreaseKey(s,x,k) 将元素x的关键字值增加到k, 这里假设k的值不小于x的原关键字
 */
public class PriorityQueue {
	
	
	public static class PQueue{
		int MAX = 1000;
		int[] arr = new int[MAX];
		int heapSize = 0;
		
	}
	
	public static int heapMaxImum(PQueue queue) {
		if(queue.heapSize==0) {
			System.out.println("优先队列为空");
			return Integer.MIN_VALUE;
		}
		return queue.arr[0];
	}
	
	public static int heapExtractMax(PQueue queue) {
		if(queue.heapSize<1) {
			System.out.println("优先队列为空");
			return Integer.MIN_VALUE;
		}
		int max = queue.arr[0];
		queue.arr[0] = queue.arr[queue.heapSize-1];
		queue.heapSize = queue.heapSize - 1;
		maxHeapify(queue.arr,0,queue.heapSize);
		return max;
	}
	//将第i号元素的优先级变为key
	public static void heapIncreaseKey(PQueue queue, int i, int key) {
		if(i>=queue.heapSize) {
			System.out.println("元素不存在");
			return ;
		}
		if(key<=queue.arr[i]) {
			System.out.println("新加的优先级较小");
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
	//将关键字key 插入到优先队列中
	public static void MaxHeapInsert(PQueue queue, int key) {
		if(queue.heapSize==queue.MAX) {
			System.out.println("队列元素已满！已替换较小key");
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
		System.out.print("集合所有元素为：");
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
		System.out.println("最大key为："+heapMaxImum(prioritQueue)+"  优先队列共有："+prioritQueue.heapSize+"个元素");
		PrintArr(prioritQueue.arr,prioritQueue.heapSize);
		heapIncreaseKey(prioritQueue, 9, 16);
		System.out.print("将10号元素改为16后");
		PrintArr(prioritQueue.arr,prioritQueue.heapSize);
	}

}

package mid_Class_Problem;

import java.util.HashMap;
import java.util.Scanner;

/*
 * 实时更新所有单词中词频最高的前k个单词
 */
public class TopKTimes {

	//堆上放的东西是node类型的实例
	public static class Node{
		public String str;
		public int times;
		
		public Node(String s, int t) {
			this.str = s;
			this.times = t;
		}
	}
	
	public static class TopKRecord{
		//词频表
		private HashMap<String,Node> strNodeMap;
		//小根堆
		private Node[] heap;
		private int heapSize;
		//Node在堆上的位置表
		private HashMap<Node,Integer> nodeIndexMap;
		
		public TopKRecord(int K) {
			this.heap = new Node[K];
			heapSize = 0;//目前位置堆有多少个单词
			this.strNodeMap = new HashMap<String,Node>();
			this.nodeIndexMap = new HashMap<Node,Integer>();
		}
		
		public void add(String str) {
			Node cur = null;
			int preIndex = -1;
			if(!strNodeMap.containsKey(str)) {//如果新加的单词没出现过
				cur = new Node(str,1);
				strNodeMap.put(str, cur);//统计新单词的词频
				nodeIndexMap.put(cur, -1);//默认其不在堆上
			}else {
				cur = strNodeMap.get(str);//如果之前有该单词则查出来
				cur.times++;//词频加一
				preIndex = nodeIndexMap.get(cur);//找到该单词之前的位置
			}
			if(preIndex==-1) {//如果该单词不在堆上分两种情况
				if(heapSize == heap.length) {//堆满了
					if(cur.times>heap[0].times) {//如果该单词的词频大于堆顶单词的词频则
						nodeIndexMap.put(heap[0],-1);//将堆顶下架
						nodeIndexMap.put(cur,0);//新单词挂在堆顶
						heap[0] = cur;
						heapify(0,heapSize);//调整堆结构，从上往下调
					}
				}else {//堆没满
					nodeIndexMap.put(cur,heapSize);//将单词挂在堆尾
					heap[heapSize] = cur;
					heapInsert(heapSize++);//调整堆结构，从下往上调
				}
			}else {//原本就在堆上
				heapify(preIndex,heapSize);//只是增加了词频，往下调
			}
		}
		//打印堆中单词
		public void printTopK() {
			System.out.println("Top :");
			for(int i=0; i<heap.length; i++) {
				if(heap[i]==null) {
					break ;
				}
				System.out.print("str :"+heap[i].str+"   ");
				System.out.println("times :"+heap[i].times);
			}
		}
		private void heapInsert(int index) {
			while(index != 0) {
				int parent = (index-1)/2;
				if(heap[index].times<heap[parent].times) {
					swap(parent,index);
					index = parent;
				}else {
					break;
				}
			}
		}
		
		private void heapify(int index,int heapSize) {
			int l = index*2+1;
			int r = index*2+2;
			int smallest = index;
			while(l<heapSize) {
				if(heap[l].times<heap[index].times) {
					smallest = l;
				}
				if(r<heapSize && heap[r].times<heap[smallest].times  ) {
					smallest = r;
				}
				if(smallest==index) {
					break ;
				}else {
					swap(smallest,index);
				}
				index = smallest;
				l = index*2+1;
				r = index*2+2;
			}
		}
		
		private void swap(int index1,int index2) {
			nodeIndexMap.put(heap[index1],index2);//更新单词位置表
			nodeIndexMap.put(heap[index2],index1);
			Node temp = heap[index1];
			heap[index1] = heap[index2];//交换堆中单词
			heap[index2] = temp;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String temp;
		TopKRecord topK = new TopKRecord(3);//输出词频最高的前三个单词
		for(int i=0;i<20;i++) {
			temp = sc.nextLine();
			topK.add(temp);
		}
	    topK.printTopK();
	}
}

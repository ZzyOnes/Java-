package mid_Class_Problem;

import java.util.HashMap;
import java.util.Scanner;

/*
 * ʵʱ�������е����д�Ƶ��ߵ�ǰk������
 */
public class TopKTimes {

	//���ϷŵĶ�����node���͵�ʵ��
	public static class Node{
		public String str;
		public int times;
		
		public Node(String s, int t) {
			this.str = s;
			this.times = t;
		}
	}
	
	public static class TopKRecord{
		//��Ƶ��
		private HashMap<String,Node> strNodeMap;
		//С����
		private Node[] heap;
		private int heapSize;
		//Node�ڶ��ϵ�λ�ñ�
		private HashMap<Node,Integer> nodeIndexMap;
		
		public TopKRecord(int K) {
			this.heap = new Node[K];
			heapSize = 0;//Ŀǰλ�ö��ж��ٸ�����
			this.strNodeMap = new HashMap<String,Node>();
			this.nodeIndexMap = new HashMap<Node,Integer>();
		}
		
		public void add(String str) {
			Node cur = null;
			int preIndex = -1;
			if(!strNodeMap.containsKey(str)) {//����¼ӵĵ���û���ֹ�
				cur = new Node(str,1);
				strNodeMap.put(str, cur);//ͳ���µ��ʵĴ�Ƶ
				nodeIndexMap.put(cur, -1);//Ĭ���䲻�ڶ���
			}else {
				cur = strNodeMap.get(str);//���֮ǰ�иõ���������
				cur.times++;//��Ƶ��һ
				preIndex = nodeIndexMap.get(cur);//�ҵ��õ���֮ǰ��λ��
			}
			if(preIndex==-1) {//����õ��ʲ��ڶ��Ϸ��������
				if(heapSize == heap.length) {//������
					if(cur.times>heap[0].times) {//����õ��ʵĴ�Ƶ���ڶѶ����ʵĴ�Ƶ��
						nodeIndexMap.put(heap[0],-1);//���Ѷ��¼�
						nodeIndexMap.put(cur,0);//�µ��ʹ��ڶѶ�
						heap[0] = cur;
						heapify(0,heapSize);//�����ѽṹ���������µ�
					}
				}else {//��û��
					nodeIndexMap.put(cur,heapSize);//�����ʹ��ڶ�β
					heap[heapSize] = cur;
					heapInsert(heapSize++);//�����ѽṹ���������ϵ�
				}
			}else {//ԭ�����ڶ���
				heapify(preIndex,heapSize);//ֻ�������˴�Ƶ�����µ�
			}
		}
		//��ӡ���е���
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
			nodeIndexMap.put(heap[index1],index2);//���µ���λ�ñ�
			nodeIndexMap.put(heap[index2],index1);
			Node temp = heap[index1];
			heap[index1] = heap[index2];//�������е���
			heap[index2] = temp;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String temp;
		TopKRecord topK = new TopKRecord(3);//�����Ƶ��ߵ�ǰ��������
		for(int i=0;i<20;i++) {
			temp = sc.nextLine();
			topK.add(temp);
		}
	    topK.printTopK();
	}
}

package high_Class_Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BuildingOutline {
	
	//�����߶ȱ仯�Ķ���
	public static class Node{
		public int x; //x���ϵ�ֵ
		public boolean isAdd; //trueΪ����߶ȣ�falseΪɾ���߶�
		public int h; //�߶�
		
		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}
	
	//����Ƚ���
	public static class NodeComparator implements Comparator<Node>{
		@Override
		public int compare(Node o1, Node o2) {
			if(o1.x!=o2.x) {
				return o1.x - o2.x;
			}
			if(o1.isAdd!=o2.isAdd) {
				return o1.isAdd ? -1:1;
			}
			return 0;
		}
	}

	//ȫ����������
	//������¥�����߶ȱ�
	public static List<List<Integer>> bulidingOutline(int[][] Matrix){
		Node[] nodes = new Node[Matrix.length*2];
		//ÿһ����¥����������������߶ȱ仯�Ķ���
		for(int i=0; i<Matrix.length; i++) {
			nodes[i*2] = new Node(Matrix[i][0],true,Matrix[i][2]);
			nodes[i*2+1] = new Node(Matrix[i][1],false,Matrix[i][2]);
		}
		
		//�������߶ȱ仯�Ķ����չ涨�������������
		Arrays.sort(nodes,new NodeComparator());
		
		//TreeMap����java�еĺ�����ṹֱ�ӵ����������Ū
		TreeMap<Integer,Integer> mapHeightTimes = new TreeMap<>();
		TreeMap<Integer,Integer> mapXHeight = new TreeMap<>();
		
		//��ʼ��дmapHeightTimes������mapXheight
		for(int i=0;i<nodes.length;i++) {
			if(nodes[i].isAdd) {//�����ǰ�Ǽ������
				if(!mapHeightTimes.containsKey(nodes[i].h)) {//���û�м���ø߶�
					mapHeightTimes.put(nodes[i].h, 1);
				}else {
					mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h)+1);
				}
			}else {//�����ǰ������ɾ��
				if(mapHeightTimes.get(nodes[i].h)==1) {
					mapHeightTimes.remove(nodes[i].h);
				}else {//�����ǰ�߶ȴ�������1���һ
					mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h)-1);
				}
			}
			
			//����mapHeightTimes�����߶�����mapXHeight��
			if(mapHeightTimes.isEmpty()) {//���mapHeightTimesΪ�գ�˵�����߶�Ϊ0
				mapXHeight.put(nodes[i].x,0);
			}else {
				mapXHeight.put(nodes[i].x,mapHeightTimes.lastKey());
			}
		}
		
		//res���������飬ÿһ��List<Integer>����һ�������ߣ��п�ʼλ�ã�����λ�ã��߶�
		List<List<Integer>> res = new ArrayList<>();
		//һ���������ߵĿ�ʼλ��
		int start = 0;
		//֮ǰ�ĸ߶�
		int preHeight = 0;
		for(Entry<Integer,Integer> entry : mapXHeight.entrySet()) {
			//��ǰλ��
			int currX = entry.getKey();
			//��ǰ�����߶�
			int curMaxHeight = entry.getValue();
			if(preHeight!=curMaxHeight) {
				if(preHeight!=0) {
					res.add(new ArrayList<>(Arrays.asList(start,currX,preHeight)));
				}
				start = currX;
				preHeight = curMaxHeight;
			}
		}
		return res;
	}
	
	public static void PrintResult(List<List<Integer>> res) {
		System.out.print("{");
		for(int j=0;j<res.size();j++) {
			List<Integer> value = res.get(j);
			System.out.print("{");
			for(int i=0; i<value.size();i++) {
				if(i!=0) {
					System.out.print(", ");
				}
				System.out.print(value.get(i));
			}
			System.out.print("}");
			if(j!=res.size()-1) {
				System.out.println(",");
			}
		}
		System.out.print("}");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] matrix = {
				{2,5,6},{1,7,4},{4,6,7},{3,6,5},
				{10,13,2},{9,11,3},{12,14,4},{10,12,5}
		};

		List<List<Integer>> res = bulidingOutline(matrix);
		PrintResult(res);
	}

}

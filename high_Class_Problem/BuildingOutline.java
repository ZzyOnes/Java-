package high_Class_Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BuildingOutline {
	
	//描述高度变化的对象
	public static class Node{
		public int x; //x轴上的值
		public boolean isAdd; //true为加入高度，false为删除高度
		public int h; //高度
		
		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}
	
	//构造比较器
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

	//全程主流操作
	//构建大楼的最大高度表
	public static List<List<Integer>> bulidingOutline(int[][] Matrix){
		Node[] nodes = new Node[Matrix.length*2];
		//每一个大楼轮廓数组产生两个高度变化的对象
		for(int i=0; i<Matrix.length; i++) {
			nodes[i*2] = new Node(Matrix[i][0],true,Matrix[i][2]);
			nodes[i*2+1] = new Node(Matrix[i][1],false,Matrix[i][2]);
		}
		
		//把描述高度变化的对象按照规定的排序策略排序
		Arrays.sort(nodes,new NodeComparator());
		
		//TreeMap就是java中的红黑树结构直接当作有序表来弄
		TreeMap<Integer,Integer> mapHeightTimes = new TreeMap<>();
		TreeMap<Integer,Integer> mapXHeight = new TreeMap<>();
		
		//开始填写mapHeightTimes来更新mapXheight
		for(int i=0;i<nodes.length;i++) {
			if(nodes[i].isAdd) {//如果当前是加入操作
				if(!mapHeightTimes.containsKey(nodes[i].h)) {//如果没有加入该高度
					mapHeightTimes.put(nodes[i].h, 1);
				}else {
					mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h)+1);
				}
			}else {//如果当前操作是删除
				if(mapHeightTimes.get(nodes[i].h)==1) {
					mapHeightTimes.remove(nodes[i].h);
				}else {//如果当前高度次数大于1则减一
					mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h)-1);
				}
			}
			
			//根据mapHeightTimes的最大高度设置mapXHeight表
			if(mapHeightTimes.isEmpty()) {//如果mapHeightTimes为空，说明最大高度为0
				mapXHeight.put(nodes[i].x,0);
			}else {
				mapXHeight.put(nodes[i].x,mapHeightTimes.lastKey());
			}
		}
		
		//res代表结果数组，每一个List<Integer>代表一个轮廓线，有开始位置，结束位置，高度
		List<List<Integer>> res = new ArrayList<>();
		//一个新轮廓线的开始位置
		int start = 0;
		//之前的高度
		int preHeight = 0;
		for(Entry<Integer,Integer> entry : mapXHeight.entrySet()) {
			//当前位置
			int currX = entry.getKey();
			//当前的最大高度
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

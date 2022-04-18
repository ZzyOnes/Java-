package high_Class_Problem;

import java.util.HashMap;
import java.util.Random;

/*
 * 随机生成一个二叉树，返回最大的搜索二叉树拓扑结构的结点个数
 */
public class BiggestBSTTopologyInTree {

	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int v) {
			this.value = v;
		}
	}
	
	public static int bstTopoSize1(Node head) {
		if(head == null) {
			return 0;
		}
		int max = maxTopo(head,head);
		max = Math.max(max, bstTopoSize1(head.left));
		max = Math.max(max, bstTopoSize1(head.right));
		return max;
	}
	
	//返回以h为头，n结点是否在以h为头的线索二叉数上，如果是返回其最大拓扑结点个数
	public static int maxTopo(Node h, Node n) {
		if(h!=null&&n!=null&&isBSTNode(h,n,n.value)) {
			return maxTopo(h,n.left)+maxTopo(h,n.right)+1;
		}
		return 0;
	}

	//看能否以value按照拓扑结构找到n
	public static boolean isBSTNode(Node h, Node n, int value) {
		if(h==null) {
			return false;
		}
		if(h==n) {
			return true;
		}
		return isBSTNode(h.value>value?h.left:h.right,n,value);
	}

	//拓扑贡献记录
	public static class Record{
		public int l; //左树贡献个数
		public int r; //右树贡献个数
		
		public Record(int l,int r) {
			this.l = l;
			this.r = r;
		}
	}
	
	//初始化map
	public static HashMap<Node, Record> origialMap(Node h,HashMap<Node, Record> map){
		if(h==null) {
			return map;
		}
		if(h.left==null&&h.right==null) {
			Record r = new Record(0,0);
			map.put(h,r);
		}else if(h.left!=null&&h.right!=null){
			int lv = h.value>h.left.value ? 1:0;
			int rv = h.value<h.right.value ? 1:0;
			Record r = new Record(lv,rv);
			map.put(h,r);
		}else if(h.left==null&&h.right!=null) {
			int rv = h.value<h.right.value ? 1:0;
			Record r = new Record(0,rv);
			map.put(h,r);
		}else {
			int lv = h.value>h.left.value ? 1:0;
			Record r = new Record(lv,0);
			map.put(h,r);
		}
		map = origialMap(h.left,map);
		map = origialMap(h.right,map);
		return map;
	}
	
	public static int bstTopoSize2(Node head) {
		HashMap<Node, Record> map = new HashMap<>();
		 map = origialMap(head,map);
		return posOrder(head,map);
	}

	public static int posOrder(Node h, HashMap<Node, Record> map) {
		if(h==null) {
			return 0;
		}
		int ls = posOrder(h.left,map);
		int rs = posOrder(h.right,map);
		//加工出本层的信息，跟新map
		modifyMap(h.left,h.value,map,true);
		modifyMap(h.right,h.value,map,false);
		Record lr = map.get(h.left);
		Record rr = map.get(h.right);
		int lbst = lr==null ? 0:lr.l+lr.r+1;
		int rbst = rr==null ? 0:rr.l+rr.r+1;
		return Math.max(lbst+rbst+1,Math.max(ls, rs));
	}

	//更新map
	public static int modifyMap(Node n, int v, HashMap<Node, Record> m, boolean s) {
		if(n==null||(!m.containsKey(n))) {
			return 0;
		}
		Record r = m.get(n);
		if((s&&n.value>v)||((!s)&&n.value<v)) {
			m.remove(n);
			return r.l+r.r+1;
		}else {
			int minus = modifyMap(s?n.right:n.left,v,m,s);
			if(s) {
				r.r = r.r-minus;
			}else {
				r.l = r.l-minus;
			}
			m.put(n,r);
			return minus;
		}
	}
	
	public static Node createBiTree(Node head,int n) {
		if(n==1) {
			return head;
		}
		if(head==null) {
			Random rand = new Random();
			head = new Node(rand.nextInt(100)+1);
			head.left = createBiTree(head.left,n-1);
			head.right = createBiTree(head.right,n-1);
		}
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = null;
		head = createBiTree(head,12);
		int res1 = bstTopoSize1(head);
		int res2 = bstTopoSize2(head);
		System.out.print(res1+"  "+res2);
	}

}

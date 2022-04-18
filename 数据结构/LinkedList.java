package 数据结构;
/*
 * 双向循环链表
 */
public class LinkedList {
	
	public static class Node{
		int num;
		Node pre;
		Node next;
		public Node(int n) {
			this.num = n;
		}
	}
	
//	public static class Link{
//		Node head;
//		public Link() {
//			head = new Node(head);
//		}
//		//将关键字为n的结点插入preNode之后
//		public Link(Node preNode,int n) {
//			head.num = n;
//			if(preNode.next == preNode.pre) {
//				this.head.pre = preNode;
//				this.head.next = null;
//				preNode.next = this.head;
//			}else {
//				this.head.pre = preNode;
//				this.head.next = preNode.next;
//				preNode.next = this.head;
//				this.head = this.head.next.pre;
//			}
//		}
//	}
	
	//寻找关键字为k的所有对象并返回
	public static Node[] listSearch(Node head, int k) {
		Node x = head.next;
		Node[] all = new Node[100]; 
		int i = 0;
		if(x.num == k) {
			all[i++] = x;
		}
		while(x.next != head.next) {
			x = x.next;
			if(x.num==k) all[i++] = x;
		}
		return i != 0 ? all : null ;
	}
	
	//链表的插入 头插法
	public static void listInsert(Node head, Node x) {
		if(head.next == null) {
			head.next = x;
			x.next = x;
			x.pre = x;
		}
		else {
			x.pre = head.next.pre;
			head.next.pre.next = x;
			x.next = head.next;
			head.next.pre = x;
			head.next = x;
		}
	}

	//链表的删除,删除结点x
	public static void listDelete(Node head, Node x) {
		if(x==null) {
			System.out.println("要删除的结点不存在！");
			return;
		}
		if(x==head.next) {
			head.next = x.next;
		}
		x.pre.next = x.next;
		x.next.pre = x.pre;
		x.pre = null;x.next = null;
//		if(x!=head.next) {
//			x.pre.next = x.next;
//			x.next.pre = x.pre;
//			x.pre = null;x.next = null;
//		}else { // 要删除的是头结点
//			x.pre.next = x.next;
//			x.next.pre = x.pre;
//			head.next = x.next;
//			x.next = null;x.pre = null;
//		}
	}
	public static void listDeleteAll(Node head, Node[] all) {
		if(all==null) {
			System.out.println("要删除的结点不存在！");
			return;
		}
		for(int i=0;i<100;i++) {
			if(all[i]==null)return;
			listDelete(head,all[i]);
		}
	}
	
	//链表的打印
	public static void printLink(Node nil) {
		Node cur = nil.next;
		if(cur!=null) {
			System.out.print(cur.num+" ");
			while(cur.next != nil.next) {
				System.out.print(cur.next.num+" ");
				cur = cur.next;
			}
		}
		else {
			System.out.print("链表为空");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node(-1); //哨兵
		int[] num1 = {4,5,6,8,7,10,23,8,5};
		for(int i=0;i<num1.length;i++) {
			Node n = new Node(num1[i]);
			listInsert(head, n);
		}
		printLink(head);
		listDeleteAll(head,listSearch(head,5));
		printLink(head);
		listDeleteAll(head,listSearch(head,6));
		printLink(head);
		Node n = new Node(100);
		listInsert(head, n);
		printLink(head);
	}

}

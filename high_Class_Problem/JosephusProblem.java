package high_Class_Problem;
/*
 * 约瑟夫环问题：
 * 问最后活下来的人是谁，要求时间复杂度为O(n)
 */
public class JosephusProblem {
	
	public static class Node{
		Node next;
		String pepole;
		
		public Node(String p) {
			this.pepole = p;
		}
	}
	
	public static Node josephusKill2(Node head,int m) {
		if(head==null||head.next==head||m<1) {
			return head;
		}
		Node cur = head.next;
		int temp = 1;
		while(cur!=head) {
			temp++;
			cur = cur.next;
		}
		temp = getLiveNum(temp,m);//通过函数直接算出哪个结点会活
		while(--temp!=0) {
			head = head.next;
		}
		return head;
	}

	//得到一共i个节点，数到m就会死，最终活下来的人的编号是多少
	//老编号 = (新编号+m-1)%i+1
	public static int getLiveNum(int i,int m) {
		if(i==1) {
			return 1;
		}
		return (getLiveNum(i-1,m)+m-1)%i+1;
	}
	
	//n个人围成一圈，依次循环取用arr中的数字
	//杀n-1轮返回活的人的编号
	public static int Live(int n,int[] arr) {
		return num(n,arr,0);
	}
	
	//还剩i个人，当前取用数字从index位置开始
	//返回活着的人的编号
	public static int num(int i,int[] arr,int index) {
		if(i==1) {
			return 1;
		}
		//老 = (新+m-1)%i+1
		return (num(i-1,arr,nextIndex(index,arr.length))+arr[index]+1)%i+1;
	}
	//返回index的下一个位置
	public static int nextIndex(int index,int size) {
		return index==size-1?0:index+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

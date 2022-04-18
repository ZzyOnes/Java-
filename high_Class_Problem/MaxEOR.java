package high_Class_Problem;
/*
 * ����һ�����飬���������и�������������������������
 */
public class MaxEOR {
	
	//���������������������� ��ͼ��İ汾
	public static int maxEOR1(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int ans=0;
		//������arr[i]��β�������������������Ϊ���٣����볢�����е�i
		for(int i=0;i<arr.length;i++) {
			//����������start��ͷ��������
			for(int start=0;start<=i;start++) {
				int sum = arr[start];
				for(int k=start+1;k<=i;k++) {
					sum ^= arr[k];
				}
				ans = Math.max(ans, sum);
			}
		}
		return ans;
	}

	//����arr[0...i]������^arr[0...start-1]������=arr[start...i]����������
	public static int maxEOR2(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		//�������������¼����0...i������
		int[] sum0i = new int[arr.length];
		sum0i[0] = arr[0];
		for(int i=1;i<arr.length;i++) {
			sum0i[i] = sum0i[i-1]^arr[i];
		}
		int ans=0;
		int sum=0;
		//������arr[i]��β�������������������Ϊ���٣����볢�����е�i
		for(int i=0;i<arr.length;i++) {
			//����������start��ͷ��������
			for(int start=0;start<=i;start++) {//���ѭ������ʣ���ˣ���Ҫ����ǰ׺��ʡ�����ѭ��
				if(start==0)sum=sum0i[i];
				else sum = sum0i[i]^sum0i[start-1];
				ans = Math.max(ans, sum);
			}
		}
		return ans;
	}
	/*
	 * ǰ׺�����٣����0...i����֮ǰ��������ǰ׺���ϣ�����ֱ�ӵõ���һ��[0...start-1]��[0...i]����������
	 */
	//����ǰ׺��
	public static class Node{
		Node[] nexts = new Node[2];//·��0��1
	}
	
	//������ǰ׺���ͼ���ǰ׺��
	public static class numTree{
		public  Node head = new Node();
		//����num����ǰ׺��
		public void add(int num) {
			Node cur = head;
			for(int move=31;move>=0;move--) {
				int path = (num>>move)&1;
				cur.nexts[path] = cur.nexts[path]==null?new Node():cur.nexts[path];
				cur = cur.nexts[path];
			}
		}
		//sum��ϣ��������·�����eor����
		public int maxXor(int sum) {
			Node cur = head;
			int res = 0;//���Ľ������
			for(int move=31;move>=0;move--) {
				int path = (sum>>move)&1;
				int best = move==31 ? path:(path^1);
				best = cur.nexts[best]==null ? (best^1):best;
				res|=(path^best)<<move;
				cur = cur.nexts[best];
			}
			return res;
		}
	}
	//������
	public static int maxXorSubarray(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int max=0;
		int sum=0;
		numTree nt = new numTree();
		nt.add(0);
		for(int i=0;i<arr.length;i++) {
			sum^=arr[i]; //0...i������
			max = Math.max(max,sum);
			max = Math.max(max,nt.maxXor(sum));
			nt.add(sum);
		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {5,-7,8,9,-23,-656,-540,56};
		System.out.println(maxEOR1(test));
		System.out.println(maxEOR2(test));
		System.out.println(maxXorSubarray(test));
	}

}

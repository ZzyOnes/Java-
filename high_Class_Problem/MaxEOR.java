package high_Class_Problem;
/*
 * 给你一个数组，里面有正有负，求其所有子数组的最大异或和
 */
public class MaxEOR {
	
	//返回所有子数组的最大异或和 最低级的版本
	public static int maxEOR1(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int ans=0;
		//尝试以arr[i]结尾的所有子数组最大异或和为多少，必须尝试所有的i
		for(int i=0;i<arr.length;i++) {
			//尝试所有以start开头的子数组
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

	//依据arr[0...i]的异或和^arr[0...start-1]的异或和=arr[start...i]的异或和升级
	public static int maxEOR2(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		//建立辅助数组记录所有0...i的异或和
		int[] sum0i = new int[arr.length];
		sum0i[0] = arr[0];
		for(int i=1;i<arr.length;i++) {
			sum0i[i] = sum0i[i-1]^arr[i];
		}
		int ans=0;
		int sum=0;
		//尝试以arr[i]结尾的所有子数组最大异或和为多少，必须尝试所有的i
		for(int i=0;i<arr.length;i++) {
			//尝试所有以start开头的子数组
			for(int start=0;start<=i;start++) {//这个循环还是剩不了，需要构建前缀树省略这个循环
				if(start==0)sum=sum0i[i];
				else sum = sum0i[i]^sum0i[start-1];
				ans = Math.max(ans, sum);
			}
		}
		return ans;
	}
	/*
	 * 前缀树加速，如果0...i及其之前的异或和在前缀树上，可以直接得到哪一个[0...start-1]与[0...i]异或起来最大
	 */
	//构建前缀树
	public static class Node{
		Node[] nexts = new Node[2];//路径0或1
	}
	
	//把所有前缀异或和加入前缀树
	public static class numTree{
		public  Node head = new Node();
		//将数num加入前缀树
		public void add(int num) {
			Node cur = head;
			for(int move=31;move>=0;move--) {
				int path = (num>>move)&1;
				cur.nexts[path] = cur.nexts[path]==null?new Node():cur.nexts[path];
				cur = cur.nexts[path];
			}
		}
		//sum最希望遇到的路径最大eor返回
		public int maxXor(int sum) {
			Node cur = head;
			int res = 0;//最后的结果返回
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
	//主函数
	public static int maxXorSubarray(int[] arr) {
		if(arr==null||arr.length==0) {
			return 0;
		}
		int max=0;
		int sum=0;
		numTree nt = new numTree();
		nt.add(0);
		for(int i=0;i<arr.length;i++) {
			sum^=arr[i]; //0...i的异或和
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

package mid_Class_Problem;

import java.util.HashMap;
import java.util.Scanner;

public class PreAndInArrayToPostArray {
	
	public static String[] getPostArray(String[] pre,String[] in) {
		if(pre==null) {
			return null;
		}
		int N = pre.length;
		String[] post = new String[pre.length];
		HashMap<String,Integer> inmap = new HashMap<>();
		for(int i=0;i<in.length;i++) {
			inmap.put(in[i], i);
		}
		set(pre,in,post,0,N-1,0,N-1,0,N-1,inmap);
		return post;
	}
	//递归
	//利用pre[prei……prej] 结合 in[ini……inj]
    //填写好post[posti……postj]
	private static void set(String[] pre, String[] in, String[] post, 
			int prei, int prej,
			int ini, int inj, 
			int posti, int postj,HashMap<String,Integer> inmap) {
		if(prei>prej) {
			return ;
		}
		post[postj]=pre[prei];//先序遍历的第一个一定是后续遍历的最后一个
		int find = ini; //在in数组中找到pre[prei]在哪个位置
		find=inmap.get(pre[prei]);
		/*
		for(;find<=inj;find++) {//遍历行为可以省略，建一个HashMap将in数组中每个值的位置记录下来
			if(in[find]==pre[prei]) {
				break;
			}
		}*/
		//找到了位置开始求填下一块post的数
		set(pre,in,post,prei+1,prei+find-ini,ini,find-1,posti,posti+find-ini-1,inmap);
		set(pre,in,post,prei+find-ini+1,prej,find+1,inj,posti+find-ini,postj-1,inmap);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] pre = {"a","b","d","e","c","f","j"};
		String[] in = {"d","b","e","a","f","c","j"};
		String[] post=getPostArray(pre,in);
		for(int i=0;i<post.length;i++) {
			System.out.print(post[i]+" ");
		}
	}

}

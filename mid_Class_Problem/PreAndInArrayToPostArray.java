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
	//�ݹ�
	//����pre[prei����prej] ��� in[ini����inj]
    //��д��post[posti����postj]
	private static void set(String[] pre, String[] in, String[] post, 
			int prei, int prej,
			int ini, int inj, 
			int posti, int postj,HashMap<String,Integer> inmap) {
		if(prei>prej) {
			return ;
		}
		post[postj]=pre[prei];//��������ĵ�һ��һ���Ǻ������������һ��
		int find = ini; //��in�������ҵ�pre[prei]���ĸ�λ��
		find=inmap.get(pre[prei]);
		/*
		for(;find<=inj;find++) {//������Ϊ����ʡ�ԣ���һ��HashMap��in������ÿ��ֵ��λ�ü�¼����
			if(in[find]==pre[prei]) {
				break;
			}
		}*/
		//�ҵ���λ�ÿ�ʼ������һ��post����
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

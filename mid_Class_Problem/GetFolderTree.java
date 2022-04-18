package mid_Class_Problem;

import java.util.TreeMap;

//��˳���ӡĿ¼
public class GetFolderTree {
	
	//����Ŀ¼���
	public static class Node{
		//��ǰ�����Ŀ¼��Ϊ·��
		public String name;
		//��ǰ���ָ�����һ���
		public TreeMap<String,Node> nextMap;//ӦΪҪ����ĸ˳���ӡ����Ҫ�������TreeMap
		
		public Node(String name) {
			this.name = name;
			this.nextMap = new TreeMap<>();
		}
	}
	
	public static void print(String[] folderPaths) {
		if(folderPaths==null || folderPaths.equals("")) {
			return ;
		}
		//�����ַ�����ǰ׺�������ã�ͷ�ڵ�Ϊhead
		Node head = generateFolderTree(folderPaths);
		//��ӡ
		printProcess(head,0);
	}

	
	public static void printProcess(Node node, int level) {
		if(level != 0) {
			//��ӡ2*level-2���ո�
			System.out.println(get2nSpace(level) + node.name);
		}
		for(Node next : node.nextMap.values()) {
			printProcess(next,level+1);
		}
	}


	public static String get2nSpace(int level) {
		String res = "";
		for(int i=1;i<level;i++) {
			res+="  ";
		}
		return res;
	}
	//�����ļ����ڲ���ǰ׺����
	static Node cur1 = new Node("");
	public static Node findNode(Node head,String str) {
		for(Node next : head.nextMap.values()) {
			if(next.name.equals(str)) {
				cur1 = next;
			}else {
				findNode(next,str);
			}
		}
		return cur1;
	}


	public static Node generateFolderTree(String[] folderPaths) {
		Node head = new Node("");//����ͷ���
		for(String folderPath : folderPaths) {//�ó�ÿһ������·��
			String[] paths = folderPath.split("\\\\"); //java���ԣ���"/"���ָ�
			Node cur = head;
			for(int i=0;i<paths.length;i++) { //��·���е��ļ����ŵ�ǰ׺����ȥ
				if(i==0) {//���i==0,����·����ͷ�ļ��ڲ���ǰ׺����
					Node find = findNode(head,paths[i]);
					if(find.name.equals(paths[i])) {//���������curָ����
						cur = find;
						//System.out.println("�ұ�ִ����");
						continue ;
					}
				}
				if(!cur.nextMap.containsKey(paths[i])) {
					cur.nextMap.put(paths[i], new Node(paths[i]));
				}
				cur = cur.nextMap.get(paths[i]);
			}
		}
		return head;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//         ��ʾ   "a\b\c"
		String test = "a\\b\\c";
		//"a\b\c" -> "a,b,v"
		// \\\\ -> \\
		// \\ -> \
		
		String[] arr = test.split("\\\\");//��ʾ�õ�б���и�
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		System.out.println("--------------------------");
		
		String[] folderPaths = {"a\\b\\c\\k","a\\d\\e\\kdjf","d\\f\\g"};
		Node head = generateFolderTree(folderPaths);
		System.out.println(findNode(head,"d").name);
		printProcess(head, 0);
	}

}

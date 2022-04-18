package mid_Class_Problem;

import java.util.TreeMap;

//按顺序打印目录
public class GetFolderTree {
	
	//建立目录结点
	public static class Node{
		//当前结点以目录名为路径
		public String name;
		//当前结点指向的下一结点
		public TreeMap<String,Node> nextMap;//应为要按字母顺序打印所以要用有序表TreeMap
		
		public Node(String name) {
			this.name = name;
			this.nextMap = new TreeMap<>();
		}
	}
	
	public static void print(String[] folderPaths) {
		if(folderPaths==null || folderPaths.equals("")) {
			return ;
		}
		//根据字符串把前缀树建立好，头节点为head
		Node head = generateFolderTree(folderPaths);
		//打印
		printProcess(head,0);
	}

	
	public static void printProcess(Node node, int level) {
		if(level != 0) {
			//打印2*level-2个空格
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
	//查找文件名在不在前缀树中
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
		Node head = new Node("");//创建头结点
		for(String folderPath : folderPaths) {//拿出每一个绝对路径
			String[] paths = folderPath.split("\\\\"); //java特性，用"/"做分割
			Node cur = head;
			for(int i=0;i<paths.length;i++) { //把路径中的文件名放到前缀树中去
				if(i==0) {//如果i==0,找新路径的头文件在不在前缀树中
					Node find = findNode(head,paths[i]);
					if(find.name.equals(paths[i])) {//如果在则令cur指向它
						cur = find;
						//System.out.println("我被执行了");
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
		//         表示   "a\b\c"
		String test = "a\\b\\c";
		//"a\b\c" -> "a,b,v"
		// \\\\ -> \\
		// \\ -> \
		
		String[] arr = test.split("\\\\");//表示用单斜杠切割
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

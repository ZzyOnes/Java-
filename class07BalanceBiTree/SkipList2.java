package class07BalanceBiTree;

import java.util.Random;

public class SkipList2 {
	
	public static final int MAX_LEVEL = 16; //跳表所允许的最大层级
    private SkipListNode head = new SkipListNode(null, MAX_LEVEL);
    private Random random = new Random();
    private int usedLevel = 1; //当前跳表使用中的最大层级
	
	 private class SkipListNode{
	        private Integer data;
	        private SkipListNode[] next; //后置节点
	        //private SkipListNode[] pre; //前置节点，方便删除使用
	 
	        public SkipListNode(Integer data, int maxLevel) {
	            this.data = data;
	            next = new SkipListNode[maxLevel];
	           // pre = new SkipListNode[maxLevel];
	        }
	        
	        int getMaxlevel() {
	            return next.length;
	        }
	    }
	 
	 //随机生成函数，即对于任意一个要插入跳表的节点，它的层级为多少
	    private int randomLevel() {
	        int level = 1;
	        for(int i = 1; i < MAX_LEVEL; i++) {
	            if(random.nextInt() % 2 == 1) {
	                level++;
	            }
	        }
	        return level;
	    }

	    public void insert(int value) {
	        int level = randomLevel(); //找出当前插入值随机最大层数
	        //遍历节点并插入
	        insert(value, level);
	 
	    }
	    
	    private void insert(int value, int level) {
	    	 SkipListNode targe = search(value);
		        if(targe!=null) {
		        	if(level>targe.getMaxlevel()) {
		        		delete(value);
		        	}else {
		        		return ;
		        	}
		        }
	        SkipListNode node = new SkipListNode(value, level); //构造当前节点
	        int currentLevel = level;
	        while(currentLevel-- >0) {
	        	if(head.next[currentLevel]==null) {
	        		head.next[currentLevel]=node;
	        	}else {
	        		SkipListNode cur=head.next[currentLevel];
	        		while(cur.next[currentLevel]!=null&&cur.next[currentLevel].data<node.data) {
	        			cur = cur.next[currentLevel];
	        		}
	        		node.next[currentLevel]=cur.next[currentLevel];
	        		cur.next[currentLevel]=node;
	        	}
	        }
	        usedLevel = usedLevel>level ? usedLevel:level;
	    }
	    
	    private SkipListNode search(int value) {
	    	int currentLevel=MAX_LEVEL;
	    	SkipListNode cur = head;
	    	while(currentLevel-->0) {
	    		while(cur.next[currentLevel]!=null && cur.next[currentLevel].data<value) {
		    		cur=cur.next[currentLevel];
		    	}
		    	if(cur.next[currentLevel]!=null &&cur.next[currentLevel].data==value) {
		    		return cur.next[currentLevel];
		    	}
	    	}
	    	return null;
	    }
	    
	    private void delete(int value) {
	    	int currentLevel=MAX_LEVEL;
	    	SkipListNode cur = head;
	    	while(currentLevel-->0) {
	    		while(cur.next[currentLevel]!=null && cur.next[currentLevel].data<value) {
		    		cur=cur.next[currentLevel];
		    	}
		    	if(cur.next[currentLevel]!=null&&cur.next[currentLevel].data==value) {
		    		SkipListNode target = cur.next[currentLevel];
		    		cur.next[currentLevel] = target.next[currentLevel];
		    		target.next[currentLevel]=null;
		    	}
	    	}
	    }
	  //逐层打印
	    public void print() {
	       int level = usedLevel;
	       while(level >= 1) {
	           level--;
	           printLevel(level);
	       }
	    }
	 
	    private void printLevel(int level) {
	        SkipListNode current = head;
	        String result = new String();
	        while (current.next[level] != null) {
	            result = result + current.next[level].data + " -> ";
	            current = current.next[level];
	        }
	        System.out.println("第 " + level + " 层的数据为 : " + result);
	    }
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SkipList2 skipList = new SkipList2();
        skipList.insert(10);
        skipList.insert(25);
        skipList.insert(83);
        skipList.insert(20);
        skipList.print();
        System.out.println("------------------");
        skipList.insert(20);
        skipList.print();
        System.out.println("------------------");
		 skipList.delete(83);
		 skipList.delete(20);
        skipList.print();
	}

}

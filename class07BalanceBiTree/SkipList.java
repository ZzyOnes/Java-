package class07BalanceBiTree;

import java.util.Random;
public class SkipList {

	public static final int MAX_LEVEL = 16; //��������������㼶
    private SkipListNode head = new SkipListNode(null, MAX_LEVEL);
    private Random random = new Random();
    private int usedLevel = 1; //��ǰ����ʹ���е����㼶
 
    public void insert(int value) {
        int level = randomLevel(); //�ҳ���ǰ����ֵ���������
        //�����ڵ㲢����
        insert(value, level);
 
    }
 
    public void delete(int value) {
        //����߲㿪ʼ��Ѱ�ҽڵ�
        int level = usedLevel;
        while(level >= 1) {
            level--;
            SkipListNode searchResult = search(value, level);
            if(searchResult != null) {
                deleteNode(searchResult);
                break;
            }
        }
 
    }
 
    //����ӡ
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
        System.out.println("�� " + level + " �������Ϊ : " + result);
    }
 
    private void deleteNode(SkipListNode searchResult) {
        //����ǰ�ڵ��ǰ�ýڵ�ͺ��ýڵ������������
        int currentLevel = searchResult.getMaxlevel();
        while(currentLevel >= 1) {
            currentLevel--;
            //��ǰ�ڵ��ǰ�ýڵ�ĺ��ýڵ� = ��ǰ�ڵ�ĺ��ýڵ�
            searchResult.pre[currentLevel].next[currentLevel] = searchResult.next[currentLevel];
            if(searchResult.next[currentLevel] != null) {
                //�����ǰ�ڵ�ĺ��ýڵ㲻Ϊnull
                //��ǰ�ڵ�Ķ���ýڵ��ǰ�ýڵ� = ��ǰ�ڵ��ǰ�ýڵ�
                searchResult.next[currentLevel].pre[currentLevel] = searchResult.pre[currentLevel];
            }
        }
    }
 
    private SkipListNode search(int value, int level) {
        //��ͷ��ʼ����
        SkipListNode current = head;
        while(current.next[level] != null && current.next[level].data < value) {
            current = current.next[level];
        }
        if(current.next[level] == null || current.next[level].data != value) {
            //�����������󣬻����Ѿ���������Ѱ��ֵ��Ľڵ���
            return null;
        }
        return current.next[level];
 
    }
 
    private void insert(int value, int level) {
        SkipListNode node = new SkipListNode(value, level); //���쵱ǰ�ڵ�
        int currentLevel = level;
        while(currentLevel-- > 0) {
            //����߲㿪ʼ������ÿһ��
            if(head.next[currentLevel] == null) {
                //�����ǰû�в����κ�Ԫ�أ�ֱ�Ӳ��뼴��
                head.next[currentLevel] = node;
                node.pre[currentLevel] = head;
            } else {
                SkipListNode current = head.next[currentLevel], pre = head;
                //�ӵ�һ��Ԫ�ؿ�ʼ����
                while(current != null && current.data < value) {
                    pre = current;
                    current = current.next[currentLevel];
                }
                //����ǰ�ýڵ�ĺ��ýڵ�Ϊ��ǰ�ڵ�
                pre.next[currentLevel] = node;
                node.pre[currentLevel] = pre;
                if(current != null) {
                    //���û�б�������β������Ҫ���õ�ǰ�ڵ��ǰ�ýڵ�
                    current.pre[currentLevel] = node;
                    node.next[currentLevel] = current;
                }
            }
 
        }
        usedLevel = usedLevel > level ? usedLevel : level;
    }
 
    //������ɺ���������������һ��Ҫ��������Ľڵ㣬���Ĳ㼶Ϊ����
    private int randomLevel() {
        int level = 1;
        for(int i = 1; i < MAX_LEVEL; i++) {
            if(random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }
 
 
    private class SkipListNode{
        private Integer data;
        private SkipListNode[] next; //���ýڵ�
        private SkipListNode[] pre; //ǰ�ýڵ㣬����ɾ��ʹ��
 
        public SkipListNode(Integer data, int maxLevel) {
            this.data = data;
            next = new SkipListNode[maxLevel];
            pre = new SkipListNode[maxLevel];
        }
 
        int getMaxlevel() {
            return next.length;
        }
 
    }
 
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(10);
        skipList.insert(25);
        skipList.insert(83);
        skipList.insert(20);
        skipList.print();
        System.out.println("------------------");
        skipList.delete(83);
        skipList.delete(20);
        skipList.print();
 
    }

}

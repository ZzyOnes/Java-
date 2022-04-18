package class07BalanceBiTree;

import class07BalanceBiTree.AvlTree.AvlTreeNode;

public class SizeBalanceTree<K,V> {

	private SBTNode<K,V> mRoot;    // 根结点
	
	class SBTNode<K,V> {
        K key;                // 关键字(键值)
        V value;
        int size;         // 大小
        SBTNode<K,V> l;    // 左孩子
        SBTNode<K,V> r;    // 右孩子

        public SBTNode(K key, SBTNode<K,V> l,SBTNode<K,V> r, V value) {
            this.key = key;
            this.l = l;
            this.r = r;
            this.size = 0;
            this.value = value;
        }
    }
	
	// 构造函数
    public SizeBalanceTree() {
        mRoot = null;
    }
    
    //调整SBTree
    @SuppressWarnings("unused")
	private SBTNode<K,V> matain(SBTNode<K,V> cur){
    	if(cur==null) {
    		return cur;
    	}
    	if(cur.l!=null && cur.l.l!=null && cur.r!=null && cur.l.l.size>cur.r.size) {
    		cur = rightRotate(cur);
    		cur.r = matain(cur.r);
    		cur = matain(cur);
    	}else if(cur.l!=null && cur.l.r!=null && cur.r!=null && cur.l.r.size>cur.r.size) {
    		cur = leftRightRotate(cur);
    		cur.l = matain(cur.l);
    		cur.r = matain(cur.r);
    		cur = matain(cur);
    	}else if(cur.r!=null && cur.r.r!=null && cur.l!=null && cur.r.r.size>cur.l.size) {
    		cur = leftRotate(cur);
    		cur.l = matain(cur.l);
    		cur = matain(cur);
    	}else if(cur.r!=null && cur.r.l!=null && cur.l!=null && cur.r.l.size>cur.l.size) {
    		cur = rightLeftRotate(cur);
    		cur.r = matain(cur.r);
    		cur.l = matain(cur.l);
    		cur = matain(cur);
    	}
    	return cur;
    }
	
	 /*
     * LL：左左对应的情况(左单旋转)。
     *
     * 返回值：旋转后的根节点
     */
    @SuppressWarnings("unused")
	private SBTNode<K,V> leftRotate(SBTNode<K,V> k2) {
    	SBTNode<K,V> k1;

        k1 = k2.l;
        k2.l = k1.r;
        k1.r = k2;

        k2.size = k2.l.size+k2.r.size + 1;
        k1.size = k1.l.size+k1.r.size + 1;

        return k1;
    }

    /*
     * RR：右右对应的情况(右单旋转)。
     *
     * 返回值：旋转后的根节点
     */
    @SuppressWarnings("unused")
	private SBTNode<K,V> rightRotate(SBTNode<K,V> k1) {
    	SBTNode<K,V> k2;

        k2 = k1.r;
        k1.r = k2.l;
        k2.l= k1;

        k1.size = k1.l.size+k1.r.size + 1;
        k2.size = k2.l.size+k2.r.size + 1;

        return k2;
    }

    /*
     * LR：左右对应的情况(左双旋转)。
     *
     * 返回值：旋转后的根节点
     */
    @SuppressWarnings("unused")
	private SBTNode<K,V> leftRightRotate(SBTNode<K,V> k3) {
        k3.l = rightRotate(k3.l);

        return leftRotate(k3);
    }

    /*
     * RL：右左对应的情况(右双旋转)。
     *
     * 返回值：旋转后的根节点
     */
    @SuppressWarnings("unused")
	private SBTNode<K,V> rightLeftRotate(SBTNode<K,V> k1) {
        k1.r = leftRotate(k1.r);

        return rightRotate(k1);
    }

}

package class03;

import java.util.HashMap;

public class RandomPool<k> {
	
	public static class Pool<K>{
		private HashMap<K,Integer> keyIndexMap;
		private HashMap<Integer,K> indexKeyMap;
		private int size;
		
		public Pool() {
			this.indexKeyMap = new HashMap<Integer,K>();
			this.keyIndexMap = new HashMap<K,Integer>();
			this.size = 0;
		}
		
		public void insert(K key) {
			if(!keyIndexMap.containsKey(key)) {
				this.indexKeyMap.put(this.size,key);
				this.keyIndexMap.put(key,this.size++);
			}
		}
		
		public void delete(K key) {
			if(this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);
				int lastIndex = --this.size;
				K lastKey = this.indexKeyMap.get(lastIndex);
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.put(key, lastIndex);
				this.indexKeyMap.remove(lastIndex);
				this.keyIndexMap.remove(lastKey);
			}
		}
		
		public K getRandom() {
			if(this.size == 0) {
				return null;
			}
			int randomIndex = (int)(Math.random()*this.size);
			return this.indexKeyMap.get(randomIndex);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

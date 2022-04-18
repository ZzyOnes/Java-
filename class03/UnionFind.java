package class03;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
	
	//样本进来会包一层叫做元素
	public static class Element<V>{
		public V value;
		
		public Element(V value) {
			this.value = value;
		}
	}

	public static class UnionFindSet<V> {
		public HashMap<V,Element<V>> elementMap;
		//key 某个元素该元素的父
		public HashMap<Element<V>,Element<V>> fatherMap;
		//key 某个集合的代表元素  value该集合的大小
		public HashMap<Element<V>,Integer> sizeMap;
		
		public UnionFindSet(List<V> list) {
			elementMap = new HashMap<>();
			fatherMap = new HashMap<>();
			sizeMap = new HashMap<>();
			for(V value: list) {
				Element<V> element = new Element<V>(value);
				elementMap.put(value, element);
				fatherMap.put(element, element);
				sizeMap.put(element,1);
			}
		}
		//返回代表元素
		private Element<V> FindHead(Element<V> element){
			Stack<Element<V>> path = new Stack<>();
			while(element != fatherMap.get(element)) {//找到父元素，并记录路径上的元素
				path.push(element);
				element = fatherMap.get(element);
			}
			while(!path.isEmpty()) {
				fatherMap.put(path.pop(), element);//将路径上的元素全部挂到父元素上
			}
			return element;
		}
		
		public  boolean isSameSet(V a,V b) {
			if(elementMap.containsKey(a)&&elementMap.containsKey(b)) {
				return FindHead(elementMap.get(a))==FindHead(elementMap.get(b));
			}
			return false;
		}
		
		public void Union(V a,V b) {
			if(elementMap.containsKey(a)&&elementMap.containsKey(b)) {
				Element<V> aFather = fatherMap.get(elementMap.get(a));
				Element<V> bFather = fatherMap.get(elementMap.get(b));
				if(aFather!=bFather) {
					Element<V> big = sizeMap.get(aFather)>sizeMap.get(bFather)?aFather:bFather;
					Element<V> small = big==aFather?bFather:aFather;
					fatherMap.put(small, big);
					sizeMap.put(big, sizeMap.get(big)+sizeMap.get(small));
					sizeMap.remove(small);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

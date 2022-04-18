package class03;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {
	
	//�����������һ�����Ԫ��
	public static class Element<V>{
		public V value;
		
		public Element(V value) {
			this.value = value;
		}
	}

	public static class UnionFindSet<V> {
		public HashMap<V,Element<V>> elementMap;
		//key ĳ��Ԫ�ظ�Ԫ�صĸ�
		public HashMap<Element<V>,Element<V>> fatherMap;
		//key ĳ�����ϵĴ���Ԫ��  value�ü��ϵĴ�С
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
		//���ش���Ԫ��
		private Element<V> FindHead(Element<V> element){
			Stack<Element<V>> path = new Stack<>();
			while(element != fatherMap.get(element)) {//�ҵ���Ԫ�أ�����¼·���ϵ�Ԫ��
				path.push(element);
				element = fatherMap.get(element);
			}
			while(!path.isEmpty()) {
				fatherMap.put(path.pop(), element);//��·���ϵ�Ԫ��ȫ���ҵ���Ԫ����
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

package ���ݽṹ;

	import java.util.Arrays;

	/**
	 * Created by chengxiao on 2016/12/17.
	 * ������demo
	 */
	public class HeapSort {
		
	    public static void heapSort(int []arr){
	        //1.�����󶥶�
	        for(int i=arr.length/2-1;i>=0;i--){
	            //�ӵ�һ����Ҷ�ӽ��������ϣ�������������ṹ
	            adjustHeap1(arr,i,arr.length);
	        }
	        //2.�����ѽṹ+�����Ѷ�Ԫ����ĩβԪ��
	        for(int j=arr.length-1;j>0;j--){
	            swap(arr,0,j);//���Ѷ�Ԫ����ĩβԪ�ؽ��н���
	            adjustHeap1(arr,0,j);//���¶Զѽ��е���
	        }
	    }
    
	    /**
	     * �����󶥶ѣ����ǵ������̣������ڴ󶥶��ѹ����Ļ����ϣ�
	     * @param arr
	     * @param i ��iλ�ÿ�ʼ���µ�
	     * @param length
	     */
	    public static void adjustHeap(int []arr,int i,int length){
	        int temp = arr[i];//��ȡ����ǰԪ��i
	        for(int k=i*2+1;k<length;k=k*2+1){//��i�������ӽ�㿪ʼ��Ҳ����2i+1����ʼ
	            if(k+1<length && arr[k]<arr[k+1]){//������ӽ��С�����ӽ�㣬kָ�����ӽ��
	                k++;
	            }
	            if(arr[k] >temp){//����ӽڵ���ڸ��ڵ㣬���ӽڵ�ֵ�������ڵ㣨���ý��н�����
	                arr[i] = arr[k];
	                i = k;
	            }else{
	                break;
	            }
	        }
	        arr[i] = temp;//��tempֵ�ŵ����յ�λ��
	    }
	    
	    //�ݹ�汾,����ѵ���
	    public static void adjustHeap1(int[] arr,int i,int length) {
	    	int l = 2*i+1;
	    	int r = l+1;
	    	int largest = i;
	    	if(l<length&&arr[i]<arr[l]) {
	    		largest = l;
	    	}
	    	if(r<length&&arr[largest]<arr[r]) {
	    		largest = r;
	    	}
	    	if(largest!=i) {
	    		swap(arr,i,largest);
	    		adjustHeap1(arr,largest,length);
	    	}
	    }

	    /**
	     * ����Ԫ��
	     * @param arr
	     * @param a
	     * @param b
	     */
	    public static void swap(int []arr,int a ,int b){
	        int temp=arr[a];
	        arr[a] = arr[b];
	        arr[b] = temp;
	    }
	    
	    public static void main(String []args){
	        int []arr = {9,8,7,6,5,4,3,2,1,52,16,3,6,7};
	        heapSort(arr);
	        System.out.println(Arrays.toString(arr));
	    }
}

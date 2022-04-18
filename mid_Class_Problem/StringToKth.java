package mid_Class_Problem;

public class StringToKth {
	
	//������i���ַ���ͷ���ܳ���Ϊlen���������ж��ٸ�
	public static int g(int i, int len) {
		if(len==1) {
			return 1;
		}
		int sum = 0;
		for(int j=i+1;j<=26;j++) {
			sum+=g(j,len-1);
		}
		return sum;
	}
	//����Ϊlen���������ж��ٸ�
	public static int f(int len) {
		int sum=0;
		for(int i=1;i<=26;i++) {
			sum+=g(i,len);
		}
		return sum;
	}

	//�����������ĸ�λ��
	public static int kth(String s) {
		char[] str = s.toCharArray();
		int sum = 0;
		int len = str.length;
		for(int i=1;i<len;i++) {//�����г���Ϊlen-1�����и�������
			sum+=f(i);
		}
		int first = str[0]-'a'+1;//��һ���ַ�
		for(int i=1;i<first;i++) {//����С�ڸ����е�һ���ַ�Ϊ��ͷ������Ϊlen�����и�������
			sum+=g(i,len);
		}
		//��ʣ�µļ���
		int pre=first;
		for(int i=1;i<len;i++) {
			int cur = str[i]-'a'+1;//��ǰ�ַ�
			for(int j=pre+1;j<cur;j++) {//��С�ڵ�ǰ�ַ���ͷ������Ϊlen-i�����и�������
				sum+=g(j,len-i);
			}
			pre=cur;
		}
		//�Դ˸�����֮ǰ���������и������ۼ���ϣ����ظ����е�λ��
		return sum+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(kth("ac"));

	}

}

package mid_Class_Problem;
/*
 * ���ַ���str1�༭��str2��Ҫ���Ѷ��ٳɱ������ж�һ���ַ�������ɾ�Ĳ����ĳɱ��ֱ�Ϊ ,ic��dc��rc
 */
public class EditCost {

	//���ж�̬�滮
	//dp[i][j]��ʾ��str1�е�ǰi���ַ��༭��str2�е�ǰj���ַ������ѵ���С�ɱ�
	public static int minCost(String str1,String str2,int ic,int dc,int rc) {
		if(str1==null&&str2==null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int row = chs1.length + 1;
		int col = chs2.length + 1;
		int[][] dp = new int[row][col];
		//dp[0][0]=0; java��Ĭ�Ͼ���0
		for(int i=1;i<row;i++) {
			dp[i][0] = i*dc;
		}
		for(int j=1;j<col;j++) {
			dp[0][j] = j*ic;
		}
		for(int i=1;i<row;i++) {
			for(int j=1;j<col;j++) {
				if(chs1[i-1]==chs2[j-1]) {
					dp[i][j] = dp[i-1][j-1];
				}else {
					dp[i][j] = dp[i-1][j-1] + rc;
				}
				dp[i][j] = Math.min(dp[i][j],dp[i-1][j]+dc);
				dp[i][j] = Math.min(dp[i][j],dp[i][j-1]+ic);
			}
		}
		return dp[row-1][col-1];
	}
	
	// ��str�У�ÿ���ַ�����һ�������ɾ���������Ľ�����ֵ�����С
	public static String remove(String str) {
		if(str==null||str.length()<2) {
			return str;
		}
		int[] map = new int[256]; //��¼ÿ���ַ��Ĵ�Ƶ��
		for(int i=0;i<str.length();i++) {
			map[str.charAt(i)]++;
		}
		int minACSIndex = 0;
		for(int i=0;i<str.length();i++) {
			if(--map[str.charAt(i)]==0) {
				break;
			}
			minACSIndex = str.charAt(minACSIndex)>str.charAt(i)?i:minACSIndex;
		}
		return String.valueOf(str.charAt(minACSIndex))+
				remove(str.substring(minACSIndex+1)
						.replaceAll(String.valueOf(str.charAt(minACSIndex)),""));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExam02 {

	public static void main(String[] args) {
		//��,��,�並 �A ��ü ���ڿ��� ����
		//[[�����İ��]
		//��� �ʹ� ���� ��� ���� �׷��� ���� ����
		//������ �ݵ�� �����ؼ� �۾�
		String myline ="����� �ʹ� ������ ����� ���� �׷��� ������ ��ö";
		String regex ="(��|��|��)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(myline);
		
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			m.appendReplacement(sb, "" );
		}
		
		m.appendTail(sb);
		System.out.println(sb);
	}

}

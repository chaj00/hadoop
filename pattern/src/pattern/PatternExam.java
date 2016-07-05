package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExam {
	public static boolean isIp(String data){
		String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		
		return Pattern.matches(regex, data);
		
		
	}
	
	
	public static void main(String[] args) {
		//1. �ڵ��� ��ȣ�� ���İ˻�
		//010,011���̸� ����, ���κп� ���� �ڸ��� ����
		
		String num1 = "011-347-5466";
		String num2 = "010-4347-5466";
		String num3 = "02-347-5466";
		String num4 = "054-3473-5466";
				
				
		String regex1= "[0][1][01]-[0-9]{3,4}-[0-9]{4}";
		String regex2= "[0][23456][12345]?-[0-9]{3,4}-[0-9]{4}";

		Pattern pattern1 = null;
		Pattern pattern2 = null;
		Matcher matcher=null;
		pattern1 = Pattern.compile(regex1);
		pattern2 = Pattern.compile(regex2);
		//����ǥ������ � ���ڿ��� ������ ������ ����
		matcher = pattern1.matcher(num1);
		System.out.println(matcher.find());
		
		matcher = pattern1.matcher(num2);
		System.out.println(matcher.find());
		
		matcher = pattern2.matcher(num3);
		System.out.println(matcher.find());
		
		matcher = pattern2.matcher(num4);
		System.out.println(matcher.find());
	}

}

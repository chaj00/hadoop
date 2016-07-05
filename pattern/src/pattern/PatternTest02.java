package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest02 {
	public static void main(String[] args){
		
		String email1 = "heaves1@hanmail.net";
		String email2 = "�׽�Ʈtest@hanmail.net";
		String email3 = "sw.kim@hanmail.net";
				
				
		String emailReg= "^[a-zA-Z]+[a-zA-Z0-9]*\\.?[a-zA-Z0-9]+@\\w+(\\.[a-zA-Z]+){1,2}";

		Pattern pattern = null;
		Matcher matcher=null;
		pattern = Pattern.compile(emailReg);
		//����ǥ������ � ���ڿ��� ������ ������ ����
		matcher = pattern.matcher(email1);
		System.out.println(matcher.find());
		
		matcher = pattern.matcher(email2);
		System.out.println(matcher.find());
		
		matcher = pattern.matcher(email3);
		System.out.println(matcher.find());
	}
	
}

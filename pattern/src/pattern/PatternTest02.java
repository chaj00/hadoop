package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest02 {
	public static void main(String[] args){
		
		String email1 = "heaves1@hanmail.net";
		String email2 = "테스트test@hanmail.net";
		String email3 = "sw.kim@hanmail.net";
				
				
		String emailReg= "^[a-zA-Z]+[a-zA-Z0-9]*\\.?[a-zA-Z0-9]+@\\w+(\\.[a-zA-Z]+){1,2}";

		Pattern pattern = null;
		Matcher matcher=null;
		pattern = Pattern.compile(emailReg);
		//정규표현식을 어떤 문자열에 정욕할 것인지 지정
		matcher = pattern.matcher(email1);
		System.out.println(matcher.find());
		
		matcher = pattern.matcher(email2);
		System.out.println(matcher.find());
		
		matcher = pattern.matcher(email3);
		System.out.println(matcher.find());
	}
	
}

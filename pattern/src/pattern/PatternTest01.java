package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest01 {

	public static void main(String[] args) {
		//String클래스 매소드를 이용 -matches(일치하면 true를 리턴)
		String str ="java로 만든 프로그램";
		String str1 ="j그램그램";
		String str2 ="j그램";
		//j로 시작하고 램으로 끝나는 문자열-j다음에 문자가 하나만 있는
		String regex01 = "^j.램$";
		
		System.out.println(str.matches(regex01));
		System.out.println(str1.matches(regex01));
		System.out.println(str2.matches(regex01));
	
		
		//이메일 - 이메일에 한글을 쓸수 없다. 
		//\p{Alnum}+는 숫자나 영문자가 올 수 있으며 +가 있기 때문에 한문자는 문드시 와야한다.
		// \.은 정규표현식에서도 .문자열을 사용하믄로 .이 정규표현식의 기로호 인식되지 않고 특수문자로 인식되록하기 위해서
		// \를 하나 더 붙여주는 이유? 특수 문자라서 인식이 안됨
		
		//String emailReg= "\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alnum}+";
		String emailReg= "\\w+\\.?\\w+@\\w+(\\.\\w+){1,2}";
		String[] emaillist ={"heaves@hanmail.net",
							"heasa43ves@hanmail.net",
							"heaves@hanmail,net",
							"@hanmail.net",
							"sw.kim@hanmail.net",
							"test@kitri.re.kr"};
		for (int i = 0; i < emaillist.length; i++) {
			System.out.println(emaillist[i]+":"+emaillist[i].matches(emailReg));
		}
		
		
		
		
		
	}

}

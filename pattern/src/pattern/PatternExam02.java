package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternExam02 {

	public static void main(String[] args) {
		//이,은,요를 뺸 전체 문자열을 리턴
		//[[적용후결과]
		//배송 너무 느려 배송 좋아 그런데 물건 엉망
		//패턴은 반드시 포함해서 작업
		String myline ="배송이 너무 느려요 배송은 좋아 그래데 물얼이 앙철";
		String regex ="(은|요|이)";
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

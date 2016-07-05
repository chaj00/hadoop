package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAPITest {

	public static void main(String[] args) {
		String data ="javase, javaee,javame,javaspring,javaio";
		Pattern p= Pattern.compile("(java)(se|ee|me)",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(data);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			System.out.println(m.group()+":"+m.group(0)+":"+m.group(1)+","+m.group(2));
			System.out.println(m.group(1));
			System.out.println(m.start()+":"+m.end());
			m.appendReplacement(sb, "********" );
		}
		
		m.appendTail(sb);
		System.out.println(sb);
	}

}

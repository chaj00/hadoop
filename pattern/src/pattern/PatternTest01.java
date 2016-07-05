package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest01 {

	public static void main(String[] args) {
		//StringŬ���� �żҵ带 �̿� -matches(��ġ�ϸ� true�� ����)
		String str ="java�� ���� ���α׷�";
		String str1 ="j�׷��׷�";
		String str2 ="j�׷�";
		//j�� �����ϰ� ������ ������ ���ڿ�-j������ ���ڰ� �ϳ��� �ִ�
		String regex01 = "^j.��$";
		
		System.out.println(str.matches(regex01));
		System.out.println(str1.matches(regex01));
		System.out.println(str2.matches(regex01));
	
		
		//�̸��� - �̸��Ͽ� �ѱ��� ���� ����. 
		//\p{Alnum}+�� ���ڳ� �����ڰ� �� �� ������ +�� �ֱ� ������ �ѹ��ڴ� ����� �;��Ѵ�.
		// \.�� ����ǥ���Ŀ����� .���ڿ��� ����Ϲȷ� .�� ����ǥ������ ���ȣ �νĵ��� �ʰ� Ư�����ڷ� �νĵǷ��ϱ� ���ؼ�
		// \�� �ϳ� �� �ٿ��ִ� ����? Ư�� ���ڶ� �ν��� �ȵ�
		
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

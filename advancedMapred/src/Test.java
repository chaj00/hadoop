import hadoop.sort.mapred.CustomKey;


public class Test {

	public static void main(String[] args) {
		String data ="rraaa";
		
		
		CustomKey key1 = new CustomKey();
		key1.setYear("1987");
		CustomKey key2 = new CustomKey();
		key2.setYear("1988");
		
		int val1 = key1.getYear().hashCode();
		int val2 = key2.getYear().hashCode();
		System.out.println(val1+":"+val2);
		
		
	}

}

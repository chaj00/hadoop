package sort.multiout;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomComparator extends WritableComparator{

	protected CustomComparator() {
		super(CustomKey.class, true);
	}

	@Override
	public int compare(WritableComparable keyObj1, WritableComparable keyObj2) {
		CustomKey key1 =(CustomKey)keyObj1;
		CustomKey key2 =(CustomKey)keyObj2;
		//연도비교
		int result = key1.getJobId().compareTo(key2.getJobId());
		if(result !=0 ){
			return result;
		}
		
		int result2 = 0; 
		if(key1.getGrade()==key2.getGrade()){
			result2 =0;
		}else if(key1.getGrade()<key2.getGrade()){
			result2 = 1;
		}else{
			result2=-1;
		}
		return result2;
	}
	
	

}

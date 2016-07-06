package hadoop.sort.mapred;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator {
	protected GroupKeyComparator(){
		super(CustomKey.class,true);
	}

	@Override
	public int compare(WritableComparable keyObj1, WritableComparable keyObj2) {
		CustomKey key1 =(CustomKey)keyObj1;
		CustomKey key2 =(CustomKey)keyObj2;
		
		return key1.getYear().compareTo(key2.getYear());
	}
	
	
}

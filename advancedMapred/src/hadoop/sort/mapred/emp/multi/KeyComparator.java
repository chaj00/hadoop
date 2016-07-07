package hadoop.sort.mapred.emp.multi;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyComparator extends WritableComparator {
    protected KeyComparator() {
      super(CustomKey.class, true);
    }
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
      CustomKey key1 = (CustomKey) w1;
      CustomKey key2 = (CustomKey) w2;
      int cmp = key1.getDeptno().compareTo(key2.getDeptno());
      if (cmp != 0) {
        return cmp;
      }
  		
		int result2 = 0;
		if(key1.getSal()==key2.getSal()){
			result2 = 0;
		}else if(key1.getSal()<key2.getSal()){
			result2 = -1;
		}else{
			result2 = 1;
		}
		return -1*result2;
   
    }
  }


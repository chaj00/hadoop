package hadoop.sort.mapred.job.multi.dto;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
    protected GroupComparator() {
      super(CustomKey.class, true);
    }
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
      CustomKey ip1 = (CustomKey) w1;
      CustomKey ip2 = (CustomKey) w2;
      return ip1.getJobid().compareTo(ip2.getJobid());
    }
  }

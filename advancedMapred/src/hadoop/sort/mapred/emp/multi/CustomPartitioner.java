package hadoop.sort.mapred.emp.multi;

import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner
extends Partitioner<CustomKey, EmpDTO> {

	@Override
	public int getPartition(CustomKey key,
			EmpDTO value, int numPartition) {
		int hash = key.getDeptno().hashCode();
		int partitionValue = hash%numPartition;
		return partitionValue;
	}
}

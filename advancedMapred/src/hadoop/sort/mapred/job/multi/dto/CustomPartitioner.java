package hadoop.sort.mapred.job.multi.dto;

import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner
extends Partitioner<CustomKey, JobDataDTO> {

	@Override
	public int getPartition(CustomKey key,
			JobDataDTO value, int numPartition) {
		int hash = key.getJobid().hashCode();
		int partitionValue = hash%numPartition;
		return partitionValue;
	}
}

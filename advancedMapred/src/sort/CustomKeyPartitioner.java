
package sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//같은 키를 갖는 레코드를 같은 리듀스 태스크에 보내지도록 한다.
//맵의 처리 결과를 파티션으로 나눠서 저장한다. 파티션을 나누는 기준이 키고 기본적으로 키의 해쉬코드값을 적용
//맵에서 출력되는 레코드가 어떤 리듀스 태스크로 보내져야 하는지 알고 있어야 하므로 파티셔너 객체를 통해 파티션 번호를 부여받는다.
//리듀서가 자신의 번호와 일치하는 번호를 갖고 있는 데이터들을 읽어서 처리한다.
public class CustomKeyPartitioner extends Partitioner<CustomKey, IntWritable> {

	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartition) {
		int hash = key.getJobId().hashCode();
		int partitionValue = hash%numPartition;
		return partitionValue;
	}

}

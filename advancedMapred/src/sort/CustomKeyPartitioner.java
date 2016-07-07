
package sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

//���� Ű�� ���� ���ڵ带 ���� ���ེ �½�ũ�� ���������� �Ѵ�.
//���� ó�� ����� ��Ƽ������ ������ �����Ѵ�. ��Ƽ���� ������ ������ Ű�� �⺻������ Ű�� �ؽ��ڵ尪�� ����
//�ʿ��� ��µǴ� ���ڵ尡 � ���ེ �½�ũ�� �������� �ϴ��� �˰� �־�� �ϹǷ� ��Ƽ�ų� ��ü�� ���� ��Ƽ�� ��ȣ�� �ο��޴´�.
//���༭�� �ڽ��� ��ȣ�� ��ġ�ϴ� ��ȣ�� ���� �ִ� �����͵��� �о ó���Ѵ�.
public class CustomKeyPartitioner extends Partitioner<CustomKey, IntWritable> {

	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartition) {
		int hash = key.getJobId().hashCode();
		int partitionValue = hash%numPartition;
		return partitionValue;
	}

}

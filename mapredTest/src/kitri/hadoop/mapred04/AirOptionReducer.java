package kitri.hadoop.mapred04;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirOptionReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//������� ������ ����
	private IntWritable resultValue = new IntWritable();
	
	
	/*
	 * reduce�żҵ��� �Ķ���ʹ� �Է�ŰŸ��, �Է°��ε� IterableŸ�Դϴ�.
	 * �Է°��� Iterator�� �������� ���´�.
	 * book[1,1,1]�� ���·� ������� �� �����Ƿ� Iterator�� �̿��ؼ� ���� Ž���� �� �ֵ��� ����
	 * 
	 */
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//��µ����ͷ� ���� �ۼ��� Ű�� �� ���ο��� ������ �и��� �ܾ��̰� ��µ������� value��
		//������ Ű�� ���� Ƚ���� �ջ��ؾ� �ϹǷ� �ݺ��۾�
		int sum =0;
		for(IntWritable value : values){ //reducer�� �Է°��� �ϳ��� ������ �ջ�
			sum=sum+value.get(); //IntWritableŸ���� �����͸� int������ ��ȯ
		}
		resultValue.set(sum);
		//reduce�� �������� Context�� ����
		context.write(key, resultValue);
	}

}

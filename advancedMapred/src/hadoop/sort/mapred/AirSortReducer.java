package hadoop.sort.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirSortReducer extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable> {
	//������� ������ ����
	private IntWritable resultValue = new IntWritable();
	//reduce�� ���Ű
	private CustomKey resultKey = new CustomKey();
	
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {

		int sum =0;
		Integer beforeMonth = key.getMonth();
		
		for(IntWritable value : values){ //reducer�� �Է°��� �ϳ��� ������ �ջ�
			if(beforeMonth!=key.getMonth()){
				resultValue.set(sum);
				resultKey.setYear(key.getYear());
				resultKey.setMonth(beforeMonth);
				context.write(resultKey, resultValue);
				sum=0;
			}
			sum=sum+value.get(); //IntWritableŸ���� �����͸� int������ ��ȯ
			beforeMonth = key.getMonth();
		}
		
		if(key.getMonth()==beforeMonth){
			resultKey.setYear(key.getYear());
			resultKey.setMonth(key.getMonth());
			resultValue.set(sum);
		}
		
		resultValue.set(sum);
		//reduce�� �������� Context�� ����
		context.write(key, resultValue);
	}

}

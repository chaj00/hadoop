package hadoop.multioutput.mapred;

/*
 * ���༭������ mapper�� ���� �Ѿ�� Ű�����͸� �������� ������ �����͸� �и��ؼ�
 * ��� ������ ���� �� �ֵ��� �ؾ��Ѵ�.
 * ���ǿ� �°� �и�
 * MultipleOutputs�� �̿��ؼ� �۾�
 * 		- ���༭�� ������ ��� �����;ȿ��� up, down, sam�� �����ؼ� ó���� �� �� �����͸� �ջ��ؼ�
 * 			���� ��������� ����
 * 		- setup�� ���༭�� ó�� ����ɋ� �ѹ� ����Ǵ� �޼ҵ�� MultipleOutputs��ü�� �����ؾ� �Ѵ�.
 * 		- cleanup�� ���༭ �۾��� ����� �� ����Ǵ� �޼ҵ�  MultipleOutputs�� �̿��Ҷ� �ν��Ͻ��� �����ؾ� �Ѵ�.
 */

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultiStockReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//������� ������ ����
	private IntWritable resultValue = new IntWritable();
	private Text resultKey = new Text();
	private MultipleOutputs<Text, IntWritable> multiOut;
	
	@Override
	protected void setup(
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<Text, IntWritable>(context);
	}
	
	
	@Override
	protected void cleanup(
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//�ݵ�� ����
		multiOut.close();
		
	}

	

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//1. key�и�
		String[] keyarr = key.toString().split(",");
		resultKey.set(keyarr[1]);
		
		if(keyarr[0].equals("up")){
			int sum =0;
			for(IntWritable value : values){ //reducer�� �Է°��� �ϳ��� ������ �ջ�
				sum=sum+value.get(); //IntWritableŸ���� �����͸� int������ ��ȯ
			}
			resultValue.set(sum);
			multiOut.write("up", resultKey, resultValue);
		}else if(keyarr[0].equals("down")){
			int sum =0;
			for(IntWritable value : values){ //reducer�� �Է°��� �ϳ��� ������ �ջ�
				sum=sum+value.get(); //IntWritableŸ���� �����͸� int������ ��ȯ
			}
			resultValue.set(sum);
			multiOut.write("down", resultKey, resultValue);
		}else if(keyarr[0].equals("same")){
			int sum =0;
			for(IntWritable value : values){ //reducer�� �Է°��� �ϳ��� ������ �ջ�
				sum=sum+value.get(); //IntWritableŸ���� �����͸� int������ ��ȯ
			}
			resultValue.set(sum);
			multiOut.write("same", resultKey, resultValue);
		}
		//MultipleOutputs�� �̿��ؼ� write�ϴ� �۾��� ó���ϹǷ� Context���� �۾����� �ʴ´�.
		//context.write(key, resultValue);
	}

}

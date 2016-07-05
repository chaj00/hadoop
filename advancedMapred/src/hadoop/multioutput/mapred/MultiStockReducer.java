package hadoop.multioutput.mapred;

/*
 * 리듀서에서는 mapper로 부터 넘어온 키데이터를 기준으로 각각의 데이터를 분리해서
 * 결과 파일을 만들 수 있도록 해야한다.
 * 조건에 맞게 분리
 * MultipleOutputs를 이용해서 작업
 * 		- 리듀서가 매퍼의 출력 데이터안에서 up, down, sam을 구분해서 처리한 후 각 데이터를 합산해서
 * 			개별 출력파일을 생성
 * 		- setup은 리듀서가 처음 실행될떄 한번 수행되는 메소드로 MultipleOutputs객체를 생성해야 한다.
 * 		- cleanup은 리듀서 작업이 종료될 떄 수행되는 메소드  MultipleOutputs을 이용할때 인스턴스를 종료해야 한다.
 */

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class MultiStockReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//결과값을 저장할 변수
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
		//반드시 종료
		multiOut.close();
		
	}

	

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//1. key분리
		String[] keyarr = key.toString().split(",");
		resultKey.set(keyarr[1]);
		
		if(keyarr[0].equals("up")){
			int sum =0;
			for(IntWritable value : values){ //reducer의 입력값을 하나씩 꺼내서 합산
				sum=sum+value.get(); //IntWritable타입의 데이터를 int형으로 변환
			}
			resultValue.set(sum);
			multiOut.write("up", resultKey, resultValue);
		}else if(keyarr[0].equals("down")){
			int sum =0;
			for(IntWritable value : values){ //reducer의 입력값을 하나씩 꺼내서 합산
				sum=sum+value.get(); //IntWritable타입의 데이터를 int형으로 변환
			}
			resultValue.set(sum);
			multiOut.write("down", resultKey, resultValue);
		}else if(keyarr[0].equals("same")){
			int sum =0;
			for(IntWritable value : values){ //reducer의 입력값을 하나씩 꺼내서 합산
				sum=sum+value.get(); //IntWritable타입의 데이터를 int형으로 변환
			}
			resultValue.set(sum);
			multiOut.write("same", resultKey, resultValue);
		}
		//MultipleOutputs을 이용해서 write하는 작업을 처리하므로 Context에서 작업하지 않는다.
		//context.write(key, resultValue);
	}

}

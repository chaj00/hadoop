package hadoop.sort.mapred;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirSortReducer extends Reducer<CustomKey, IntWritable, CustomKey, IntWritable> {
	//결과값을 저장할 변수
	private IntWritable resultValue = new IntWritable();
	//reduce의 출력키
	private CustomKey resultKey = new CustomKey();
	
	@Override
	protected void reduce(CustomKey key, Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context)
			throws IOException, InterruptedException {

		int sum =0;
		Integer beforeMonth = key.getMonth();
		
		for(IntWritable value : values){ //reducer의 입력값을 하나씩 꺼내서 합산
			if(beforeMonth!=key.getMonth()){
				resultValue.set(sum);
				resultKey.setYear(key.getYear());
				resultKey.setMonth(beforeMonth);
				context.write(resultKey, resultValue);
				sum=0;
			}
			sum=sum+value.get(); //IntWritable타입의 데이터를 int형으로 변환
			beforeMonth = key.getMonth();
		}
		
		if(key.getMonth()==beforeMonth){
			resultKey.setYear(key.getYear());
			resultKey.setMonth(key.getMonth());
			resultValue.set(sum);
		}
		
		resultValue.set(sum);
		//reduce의 실행결과를 Context에 설정
		context.write(key, resultValue);
	}

}

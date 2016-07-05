package kitri.hadoop.mapred03;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	//결과값을 저장할 변수
	private IntWritable resultValue = new IntWritable();
	
	
	/*
	 * reduce매소드의 파라미터는 입력키타입, 입력값인데 Iterable타입니다.
	 * 입력값은 Iterator로 감싸져서 들어온다.
	 * book[1,1,1]의 형태로 만들어질 수 있으므로 Iterator를 이용해서 값을 탐색할 수 있도록 적용
	 * 
	 */
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//출력데이터로 최종 작성할 키는 한 라인에서 공백을 분리된 단어이고 출력데이터의 value는
		//동일한 키에 대한 횟수를 합산해야 하므로 반복작업
		int sum =0;
		for(IntWritable value : values){ //reducer의 입력값을 하나씩 꺼내서 합산
			sum=sum+value.get(); //IntWritable타입의 데이터를 int형으로 변환
		}
		resultValue.set(sum);
		//reduce의 실행결과를 Context에 설정
		context.write(key, resultValue);
	}

}

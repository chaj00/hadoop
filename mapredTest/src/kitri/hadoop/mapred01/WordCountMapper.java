package kitri.hadoop.mapred01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1); //map매소드 실행결과중 value를 저장할 변수(무조건1이므로 상수로 정의)
	private Text outputKey = new Text();//map매소드의 실행 결과 중 키를 저장할 변수
	
	//입력데이러틑 분석하기 위한 매소드 - 조건반복
	//파라미터 - 입력키, 입력값, Context객체(맵리듀스가 통신하면서 출력데이터를 기록하고 맵의 출력데이터를 shuffle하기 위해 내보내는 작업을 수행,메시지 갱신...수행)
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//value에는 입력데이터의 한 라인에 해당하는 문장이 전달된다. ex) read a book
		//공백을 기준으로 잘라내기
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreElements()){
			String token = st.nextToken();
			//mongodb에서 처럼 emit시키는 작업 - map실행 결과
			//outputKey에 값을 셋팅
			outputKey.set(token);
			//Context객체에 output키와 value를 저장한다. - shuffle단계에서 Context에 저장된 값을 가지고 작업
			context.write(outputKey, one);
			
		}
		
		
	}
	
}

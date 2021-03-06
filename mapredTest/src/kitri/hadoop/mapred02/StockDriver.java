package kitri.hadoop.mapred02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//hdfs상에 저장된 파일을 읽어와서 맵리듀스를 실행하고 실행 결과를 hdfs에 저장하는 역할을 하는 클래스
public class StockDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		//1. 맵리듀스 실행하기 위한 job생성 - 하둡의 설정정보 필요
		Job job = new Job(conf, "stockjob");
		
		//2. 실제 잡을 구성하는 클래스를 정의
		job.setMapperClass(StockMapper.class); //Mapper 클래스 지정
		job.setReducerClass(StockReducer.class); //Reducer클래스 지정
		job.setJarByClass(StockDriver.class); //드라이버클래스(job을 실행)
		
		//3. input데이터와 output데이터의 포멧을 지정
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//4. 리듀서의 출력데이터에 대한 키와 value를 설정
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//5. hdfs에 저장된 파일을 읽어오고 처리결과를 저장하기
		FileInputFormat.addInputPath(job, new Path("/inputdata/NASDAQ_daily_prices_J.csv"));
		FileOutputFormat.setOutputPath(job, new Path("stock_output"));
		
		
		//6. 여러가지 셋팅된 정보를 기반으로 실제 job이 실행될수 있도록 처리
		job.waitForCompletion(true);
	}

}

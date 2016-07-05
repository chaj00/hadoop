package kitri.hadoop.mapred04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * 환경설정 정보를 쉽게 제어하기 위해서 Configured클래스를 상속받고
 * 사용자 정의옵션을 사용하기 위해서 Tool인터페이스를 구현
 * Tool인터페이스를 구현하는 경우 run매소드를 오버라이딩 한다.
 */
public class AirOptionDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] optionlist) throws Exception {
		//GenericOptionParser에 대한 정보를 설정
		//GenericOtionParser가 제공하는 옵션을 제외한 나머지 옵션을 배열로 변환해서 리턴
		String[] otherArgs = new GenericOptionsParser(getConf(), optionlist).getRemainingArgs();
		
		//나머지 Job을 실행할떄 필요한 코드를 구현
		Job job = new Job(getConf(), "airoption");
		
		job.setMapperClass(AirOptionMapper.class); 
		job.setReducerClass(AirOptionReducer.class); 
		job.setJarByClass(AirOptionDriver.class); 
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
				
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
				
		FileInputFormat.addInputPath(job, new Path(otherArgs[0])); //args[0] 콘솔에서 입력받는 첫번쨰
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1])); //args[1]
				
		job.waitForCompletion(true);
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		//run을 호출 -> ToolRunner의 run을 호출해서 우리가 구현한 run(Tool의 Run)이 호출되도록
		int result = ToolRunner.run(new Configuration(),new AirOptionDriver(), args);
		System.out.println("결과=>"+result);
	}

	

}

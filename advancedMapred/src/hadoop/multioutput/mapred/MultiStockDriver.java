package hadoop.multioutput.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * Driver클래스에서 MultipleOutputs로 출력될 경로만 지정해주면 된다.
 * 모든 path에는 prefix로 up,down,same이 연결될수 있도록 작성
 */
public class MultiStockDriver extends Configured implements Tool {
	@Override
	public int run(String[] optionlist) throws Exception {
		String[] otherArgs = new GenericOptionsParser(getConf(), optionlist).getRemainingArgs();
		
		Job job = new Job(getConf(), "multioutput");
		
		job.setMapperClass(MultiStockMapper.class); 
		job.setReducerClass(MultiStockReducer.class); 
		job.setJarByClass(MultiStockDriver.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		//output파일을 세개 생성할 것이므로 MultipleOutputs설정
		MultipleOutputs.addNamedOutput(job, "up", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "down", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "same", TextOutputFormat.class, Text.class, IntWritable.class);
		
		job.waitForCompletion(true);
		
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int result = ToolRunner.run(new Configuration(),new MultiStockDriver(), args);
		System.out.println("결과=>"+result);
	}

	

}

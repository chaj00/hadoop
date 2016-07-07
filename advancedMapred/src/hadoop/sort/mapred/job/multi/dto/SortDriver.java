package hadoop.sort.mapred.job.multi.dto;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

 
public class SortDriver extends Configured implements Tool{
	@Override
	public int run(String[] optionlist) throws Exception {
		String[] otherArgs = 
				new GenericOptionsParser(getConf(), optionlist)
				.getRemainingArgs();
		

		Job job = new Job(getConf(), "wordcount");

		job.setJarByClass(SortDriver.class);
		job.setMapperClass(SortMapper.class);
		job.setReducerClass(SortReducer.class); 
	
	
		job.setPartitionerClass(CustomPartitioner.class);//파티셔너
		job.setSortComparatorClass(KeyComparator.class);//정렬기준비교객체
		job.setGroupingComparatorClass(GroupComparator.class);//그룹키
		job.setMapOutputKeyClass(CustomKey.class);
		job.setMapOutputValueClass(JobDataDTO.class);
	
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//리듀서의 output
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(JobDataDTO.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		//output파일을 세 개 생성할 것이므로 MultipleOutputs설정
		/*MultipleOutputs.addNamedOutput(job, "BAD",
				TextOutputFormat.class,
				CustomKey.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "RE4",
				TextOutputFormat.class,
				CustomKey.class, IntWritable.class);*/
		job.waitForCompletion(true);	
		return 0;
	}
	public static void main(String[] args)	throws Exception {
 
		//run을 호출 -> ToolRunner의 run을 호출해서 우리가 구현한 run(Tool의 Run)
		//이 호출되도록
		int result = ToolRunner.run(new Configuration(), 
						new SortDriver(), args);
		System.out.println("결과=>"+result);		
	}

}

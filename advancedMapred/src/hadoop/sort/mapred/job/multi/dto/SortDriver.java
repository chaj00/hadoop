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
	
	
		job.setPartitionerClass(CustomPartitioner.class);//��Ƽ�ų�
		job.setSortComparatorClass(KeyComparator.class);//���ı��غ񱳰�ü
		job.setGroupingComparatorClass(GroupComparator.class);//�׷�Ű
		job.setMapOutputKeyClass(CustomKey.class);
		job.setMapOutputValueClass(JobDataDTO.class);
	
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//���༭�� output
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(JobDataDTO.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		//output������ �� �� ������ ���̹Ƿ� MultipleOutputs����
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
 
		//run�� ȣ�� -> ToolRunner�� run�� ȣ���ؼ� �츮�� ������ run(Tool�� Run)
		//�� ȣ��ǵ���
		int result = ToolRunner.run(new Configuration(), 
						new SortDriver(), args);
		System.out.println("���=>"+result);		
	}

}

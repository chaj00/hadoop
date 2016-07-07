package sort.multiout;



import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

 
public class MultiSortDriver {
	public static void main(String[] args) 
				throws IOException, InterruptedException, ClassNotFoundException {
 
		Configuration conf = new Configuration();

		Job job = new Job(conf, "sort");

		job.setJarByClass(MultiSortDriver.class);
		job.setMapperClass(MultiSortMapper.class);
		job.setReducerClass(MultiSortReducer.class); 
		job.setPartitionerClass(CustomKeyPartitioner.class); 
		job.setSortComparatorClass(CustomComparator.class); 
		job.setGroupingComparatorClass(GroupKeyComparator.class); 

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(CustomKey.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		MultipleOutputs.addNamedOutput(job, "BAD", TextOutputFormat.class, CustomKey.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "RE4", TextOutputFormat.class, CustomKey.class, IntWritable.class);
		
		job.waitForCompletion(true);	

	}

}

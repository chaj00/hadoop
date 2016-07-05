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
 * ȯ�漳�� ������ ���� �����ϱ� ���ؼ� ConfiguredŬ������ ��ӹް�
 * ����� ���ǿɼ��� ����ϱ� ���ؼ� Tool�������̽��� ����
 * Tool�������̽��� �����ϴ� ��� run�żҵ带 �������̵� �Ѵ�.
 */
public class AirOptionDriver extends Configured implements Tool{
	
	@Override
	public int run(String[] optionlist) throws Exception {
		//GenericOptionParser�� ���� ������ ����
		//GenericOtionParser�� �����ϴ� �ɼ��� ������ ������ �ɼ��� �迭�� ��ȯ�ؼ� ����
		String[] otherArgs = new GenericOptionsParser(getConf(), optionlist).getRemainingArgs();
		
		//������ Job�� �����ҋ� �ʿ��� �ڵ带 ����
		Job job = new Job(getConf(), "airoption");
		
		job.setMapperClass(AirOptionMapper.class); 
		job.setReducerClass(AirOptionReducer.class); 
		job.setJarByClass(AirOptionDriver.class); 
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
				
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
				
		FileInputFormat.addInputPath(job, new Path(otherArgs[0])); //args[0] �ֿܼ��� �Է¹޴� ù����
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1])); //args[1]
				
		job.waitForCompletion(true);
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		//run�� ȣ�� -> ToolRunner�� run�� ȣ���ؼ� �츮�� ������ run(Tool�� Run)�� ȣ��ǵ���
		int result = ToolRunner.run(new Configuration(),new AirOptionDriver(), args);
		System.out.println("���=>"+result);
	}

	

}

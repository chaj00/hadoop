package kitri.hadoop.mapred03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//hdfs�� ����� ������ �о�ͼ� �ʸ��ེ�� �����ϰ� ���� ����� hdfs�� �����ϴ� ������ �ϴ� Ŭ����
public class AirDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		//1. �ʸ��ེ �����ϱ� ���� job���� - �ϵ��� �������� �ʿ�
		Job job = new Job(conf, "airjob");
		
		//2. ���� ���� �����ϴ� Ŭ������ ����
		job.setMapperClass(AirMapper.class); //Mapper Ŭ���� ����
		job.setReducerClass(AirReducer.class); //ReducerŬ���� ����
		job.setJarByClass(AirDriver.class); //����̹�Ŭ����(job�� ����)
		
		//3. input�����Ϳ� output�������� ������ ����
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//4. ���༭�� ��µ����Ϳ� ���� Ű�� value�� ����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//5. hdfs�� ����� ������ �о���� ó������� �����ϱ�
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		//6. �������� ���õ� ������ ������� ���� job�� ����ɼ� �ֵ��� ó��
		job.waitForCompletion(true);
	}

}
package hadoop.sort.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//hdfs�� ����� ������ �о�ͼ� �ʸ��ེ�� �����ϰ� ���� ����� hdfs�� �����ϴ� ������ �ϴ� Ŭ����
public class AirSortDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		//1. �ʸ��ེ �����ϱ� ���� job���� - �ϵ��� �������� �ʿ�
		Job job = new Job(conf, "airjob");
		
		//2. ���� ���� �����ϴ� Ŭ������ ����
		job.setMapperClass(AirSortMapper.class); //Mapper Ŭ���� ����
		job.setReducerClass(AirSortReducer.class); //ReducerŬ���� ����
		job.setJarByClass(AirSortDriver.class); //����̹�Ŭ����(job�� ����)
		job.setPartitionerClass(CustomKeyPartitioner.class); //��Ƽ�ų�
		job.setSortComparatorClass(CustomComparator.class); //���ı��غ񱳰�ü
		job.setGroupingComparatorClass(GroupKeyComparator.class); //�׷�Ű
		
		//3. input�����Ϳ� output�������� ������ ����
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//4. ���༭�� ��µ����Ϳ� ���� Ű�� value�� ����
		job.setOutputKeyClass(CustomKey.class);
		job.setOutputValueClass(IntWritable.class);
		
		//5. hdfs�� ����� ������ �о���� ó������� �����ϱ�
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		//6. �������� ���õ� ������ ������� ���� job�� ����ɼ� �ֵ��� ó��
		job.waitForCompletion(true);
	}

}

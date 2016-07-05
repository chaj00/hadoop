package kitri.hadoop.mapred01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1); //map�żҵ� �������� value�� ������ ����(������1�̹Ƿ� ����� ����)
	private Text outputKey = new Text();//map�żҵ��� ���� ��� �� Ű�� ������ ����
	
	//�Էµ��̷��z �м��ϱ� ���� �żҵ� - ���ǹݺ�
	//�Ķ���� - �Է�Ű, �Է°�, Context��ü(�ʸ��ེ�� ����ϸ鼭 ��µ����͸� ����ϰ� ���� ��µ����͸� shuffle�ϱ� ���� �������� �۾��� ����,�޽��� ����...����)
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//value���� �Էµ������� �� ���ο� �ش��ϴ� ������ ���޵ȴ�. ex) read a book
		//������ �������� �߶󳻱�
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreElements()){
			String token = st.nextToken();
			//mongodb���� ó�� emit��Ű�� �۾� - map���� ���
			//outputKey�� ���� ����
			outputKey.set(token);
			//Context��ü�� outputŰ�� value�� �����Ѵ�. - shuffle�ܰ迡�� Context�� ����� ���� ������ �۾�
			context.write(outputKey, one);
			
		}
		
		
	}
	
}

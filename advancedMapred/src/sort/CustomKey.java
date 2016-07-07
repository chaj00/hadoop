package sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomKey implements WritableComparable<CustomKey> {
	private String jobId;
	private Integer grade;
	
	public CustomKey(){
		
	}
	
	public CustomKey(String jobId, Integer grade) {
		super();
		this.jobId = jobId;
		this.grade = grade;
	}

	//�����͸� �а� ���� �۾��� readField�� write�޼ҵ带 �̿��Ͽ� ó���ϴµ�
	//�����͸� �а� ���� �۾��� ����ȭ, ������ȭ �۾��̰� �� �۾��� �ϵӿ��� �����Ǵ� WritableUtilsŬ���� ���ο��� ����
	//�Է½�Ʈ������ jobId�� grade�� ��ȸ�ϱ�
	@Override
	public void readFields(DataInput in) throws IOException {
		jobId =WritableUtils.readString(in);
		grade = in.readInt();
	}
	//��½�Ʈ������ ������ ���� ���
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, jobId);
		out.writeInt(grade);
	}
	//Ŀ����Ű���� ���ؼ� ������ ���ϱ� ���ؼ� ���
	@Override
	public int compareTo(CustomKey key) {
		int result = jobId.compareTo(key.jobId);
		if(result==0){
			result = grade.compareTo(key.grade);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return (new StringBuffer()).append(jobId).append(",").append(grade).toString();
	}
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	
	

}

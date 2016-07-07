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

	//데이터를 읽고 쓰는 작업을 readField와 write메소드를 이용하여 처리하는데
	//데이터를 읽고 쓰는 작업은 직렬화, 역직렬화 작업이고 이 작업은 하둡에서 제공되는 WritableUtils클래스 내부에서 제공
	//입력스트림에서 jobId와 grade를 조회하기
	@Override
	public void readFields(DataInput in) throws IOException {
		jobId =WritableUtils.readString(in);
		grade = in.readInt();
	}
	//출력스트림에서 연도와 월을 출력
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, jobId);
		out.writeInt(grade);
	}
	//커스텀키들을 비교해서 순서를 정하기 위해서 사용
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

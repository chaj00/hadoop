package hadoop.sort.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CustomKey implements WritableComparable<CustomKey> {
	private String year;
	private Integer month;
	
	public CustomKey(){
		
	}
	public CustomKey(String year, Integer month) {
		super();
		this.year = year;
		this.month = month;
	}
	//데이터를 읽고 쓰는 작업을 readField와 write메소드를 이용하여 처리하는데
	//데이터를 읽고 쓰는 작업은 직렬화, 역직렬화 작업이고 이 작업은 하둡에서 제공되는 WritableUtils클래스 내부에서 제공
	//입력스트림에서 year와 month를 조회하기
	@Override
	public void readFields(DataInput in) throws IOException {
		year =WritableUtils.readString(in);
		month = in.readInt();
	}
	//출력스트림에서 연도와 월을 출력
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, year);
		out.writeInt(month);
	}
	//커스텀키들을 비교해서 순서를 정하기 위해서 사용
	@Override
	public int compareTo(CustomKey key) {
		int result = year.compareTo(key.year);
		if(result==0){
			result = month.compareTo(key.month);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return (new StringBuffer()).append(year).append(",").append(month).toString();
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
	

}

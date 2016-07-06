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
	//�����͸� �а� ���� �۾��� readField�� write�޼ҵ带 �̿��Ͽ� ó���ϴµ�
	//�����͸� �а� ���� �۾��� ����ȭ, ������ȭ �۾��̰� �� �۾��� �ϵӿ��� �����Ǵ� WritableUtilsŬ���� ���ο��� ����
	//�Է½�Ʈ������ year�� month�� ��ȸ�ϱ�
	@Override
	public void readFields(DataInput in) throws IOException {
		year =WritableUtils.readString(in);
		month = in.readInt();
	}
	//��½�Ʈ������ ������ ���� ���
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, year);
		out.writeInt(month);
	}
	//Ŀ����Ű���� ���ؼ� ������ ���ϱ� ���ؼ� ���
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

package hadoop.sort.mapred;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomComparator extends WritableComparator{

	protected CustomComparator() {
		super(CustomKey.class, true);
	}

	//����Ű ���ο� �̹� ���ϴ� ������ ������ �Ǿ� ������ �Է½�Ʈ������ ��ȸ�� ���� ����
	//��Ȯ�� ���� ������ �����ֱ� ���ؼ� ��������� ���ϴ� �ڵ带 ����
	@Override
	public int compare(WritableComparable keyObj1, WritableComparable keyObj2) {
		CustomKey key1 =(CustomKey)keyObj1;
		CustomKey key2 =(CustomKey)keyObj2;
		//������
		int result = key1.getYear().compareTo(key2.getYear());
		if(result !=0 ){ //key1�� ������ key2�� �������� ���� ���� �ʴ°��
			return result;
		}
		//������ ��ġ�� ��� ���� ��
		//�� ==0
		//key1�� ���� key2������ ������-1 ũ�� 1�� ����
		int result2 = 0; 
		if(key1.getMonth()==key2.getMonth()){
			result2 =0;
		}else if(key1.getMonth()<key2.getMonth()){
			result2 = -1;
		}else{
			result2=1;
		}
		return result2;
	}
	
	

}

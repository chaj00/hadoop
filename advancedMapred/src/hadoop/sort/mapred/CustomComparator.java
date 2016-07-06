package hadoop.sort.mapred;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomComparator extends WritableComparator{

	protected CustomComparator() {
		super(CustomKey.class, true);
	}

	//복합키 내부에 이미 비교하는 로직이 구현이 되어 있지만 입력스트림에서 조회한 값을 비교함
	//정확한 정렬 순서를 정해주기 위해서 멤버변수를 비교하는 코드를 구현
	@Override
	public int compare(WritableComparable keyObj1, WritableComparable keyObj2) {
		CustomKey key1 =(CustomKey)keyObj1;
		CustomKey key2 =(CustomKey)keyObj2;
		//연도비교
		int result = key1.getYear().compareTo(key2.getYear());
		if(result !=0 ){ //key1의 연도와 key2의 연도값이 동일 하지 않는경우
			return result;
		}
		//연도가 일치할 경우 월을 비교
		//월 ==0
		//key1의 월이 key2월보다 작으면-1 크면 1을 리턴
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

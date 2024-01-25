package university;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/* 교수 클래스 : 손나영 */
//교수 : 교번, 이름, 담당 강의, 연락처, 성별
/*
 * 연락처 : 01012345678 형태로 입력받기. 자릿수 검사, 11자리가 아닐 경우 잘못된 전화번호라고 알리기
 * 이름 : 동명이인일 수 있음
 * 담당강의 : 여러개일 수 있음(만약 강의 내용이 수정, 삭제가 된다면 같이 수정 및 삭제가 되어야함
 * 학과 : 하나(수정 시 함께 수정, 삭제 시 교수 정보 삭제)
 * 모든 것이 일치하는 교수가 있을 시 등록불가
 *  */
/*
	@NoArgsConstructor : 파라미터가 없는 디폴트 생성자를 생성
	@AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자를 생성
	@RequiredArgsConstructor : final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
 */

@Data
public class Professor implements Serializable {

	private static final long serialVersionUID = 8377957422955045309L;
	
	//교번, 성별(1:남성, 2:여성)
	int num, gender;
	
	//이름, 연락처(8자 혹은 11자로 받아서 000-0000-0000 형태로출력)
	String name, phoneNum;
		 
	//학과(전공)
	 Department department;
		 
	 //담당 강의(강의 번호, 강의명, 담당교수, 인원, 강의 시간, 강의실)
	 //입력받지 않음
	 List<Lecture> lList;
	 
	//출력 재정의
	@Override
	public String toString() {
		//조건연산자로 성별 출력
		String type = gender==1 ? " 남성" : "여성";
		String printNum = String.format("%5d", num);
		String printdpName = String.format("%6s",  department.dpName );
		String printName = String.format("%4s",  name );
		String printType = String.format(" %s ",  type );
		String printPhoneNum = String.format(" %13s ",  phoneFormatter(phoneNum) );
		
		
		return  " [" + printNum + "] "+ printdpName + " " + printName + "  " + printType + "  " + printPhoneNum;
	}

	//교번, 성별, 이름, 연락처만 동일해도 같은 교수로 판별
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return gender == other.gender && Objects.equals(name, other.name) && num == other.num
				&& Objects.equals(phoneNum, other.phoneNum);
	}


	@Override
	public int hashCode() {
		return Objects.hash(gender, name, num, phoneNum);
	}

	public Professor(int num, int gender, String name, String phoneNum, Department department, List<Lecture> lList) {
		super();
		this.num = num;
		this.gender = gender;
		this.name = name;
		this.phoneNum = phoneNum;
		this.department = department;
		//만약 강의 리스트가 없다면
		if(lList == null || lList.size() ==0) {
			this.lList = new ArrayList<Lecture>();
		}
	}
	
	//전화번호 메소드
	public String phoneFormatter(String phoneNum) {
		 String formatNum = "";
		formatNum = phoneNum.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
		return formatNum;
	}
}


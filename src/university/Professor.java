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
 * 연락처 : 000-0000-0000 형태로 입력받기. 자릿수 검사, 13자리가 아닐 경우 잘못된 전화번호라고 알리기
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
	
	//교번, 성별(1:여성, 2:남성)
	int num, gender;
	
	//이름, 연락처(000-0000-0000)
	String name, phoneNum;
		 
	//학과(전공)
	 Department department;
		 
	 //담당 강의(강의 번호, 강의명, 담당교수, 인원, 강의 시간, 강의실)
	 //입력받지 않음
	 List<Lecture> lectureList;
	 
	//출력 재정의
	@Override
	public String toString() {
		//조건연산자로 성별 출력
		String type = gender==1 ? "여성" : "남성";
		
		return  "[" + num + "]  "+lectureList + "  " + name + "  " + type + "  " + phoneNum;
	}
	
	//강의 추가하는 메소드

	
	//강의 수정하는 메소드
	public boolean setMean(int pos, Lecture lecture) {
		//뜻을 저장하는 리스트가 생성되어 있지 않으면
		if(lectureList == null) {
			return false;
		}

		//pos가 잘못된 경우
		if(pos < 0 || pos >= lectureList.size()) {
			return false;
		}
		
		//이미 등록된 뜻이면
//		if(lectureList.contains(new Lecture(lecture))) {
//			return false;
//		}
//		
//		//수정
//		lectureList.set(pos, new Lecture(lecture));
		return true;
	}
	
	//강의를 삭제하는 메소드
	
	
	
	
	


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

	public Professor(int num, int gender, String name, String phoneNum, Department department,
			List<Lecture> lectureList) {
		super();
		this.num = num;
		this.gender = gender;
		this.name = name;
		this.phoneNum = phoneNum;
		this.department = department;
		//만약 강의 리스트가 없다면
		if(lectureList == null) {
			lectureList = new ArrayList<Lecture>();
		}
		this.lectureList = lectureList;
	}


	


}







/* <전화번호 받아오기 응용?>
 * - 날짜 변환을 위해 SimpleDateFormat을 멤버로 하기보단 패턴을 멤버로 한 후, 
 *   지역변수로 SimpleDateFormat을 이용하는 방법도 있음
 * public final static String datePattern = "yyyy-MM-dd";
 * - 위와 같이 선언하면 Item.datePattern을 통해 다른 클래스에서도 Item 클래스의 날짜 패턴을 알 수 있음.
 * 
 * public class FormatUtil {
 
  public static String phone(String src) {
    if (src == null) {
      return "";
    }
    if (src.length() == 8) {
      return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
    } else if (src.length() == 12) {
      return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
    }
    return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
  }
  */


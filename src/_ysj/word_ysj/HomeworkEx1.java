package _ysj.word_ysj;
 
public class HomeworkEx1 {
	/* 영어 단어장을 관리하는 프로그램을 작성하세요. (day 10 word)
	 * 기한은 1/5일(금) 까지 github에 업로드 후 강사에게 공유(단톡방)
	 * - 한 단어에 뜻이 여러개 있을 수 있음(arrayList)
	 * - 단어, 품사, 뜻을 관리 / 품사 : 동사 명사 형용사 등
	 * - 단어 추가
	 * - 단어 수정
	 * - 단어 삭제
	 * - 뜻 추가
	 * - 뜻 수정
	 * - 뜻 삭제
	 * - 단어 조회
	 * ---------------------------
	 * - 기타 추가 기능을 구현해도 됨
	 * - 등록된 단어장에서 랜덤으로 문제가 나오고 맞추는 기능
	 * - 오답 노트를 관리하는 기능 
	 * - 많이 조회한 단어를 확인하는 기능 */

	public static void main(String[] args) {
		Program p = new WordProgram();
		p.run();
	}
	
}

/* 구현안된 것
 * 1. 한 단어에 여러 뜻이 있는 것
 * 2. 품사 관리
 * 3. 단어 조회시, 등록된 단어가 없을때 단어가 없다는 메세지 출력
 * 4. 기타 추가 기능 */
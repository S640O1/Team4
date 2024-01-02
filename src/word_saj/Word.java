package word_saj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Word {
	
	@NonNull
	private String word;				// 세 개를 한 줄에 쓰면 Duplicate method in type 에러 발생
	private String mean, speechOfPart;
}

package word_saj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Word implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4250255972094339729L;
	@NonNull
	private String word;				// 세 개를 한 줄에 쓰면 Duplicate method in type 에러 발생
	private String speechOfPart;
	private List<String> mean = new ArrayList<String>();
}

package word_saj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private static final long serialVersionUID = -8877489027076810049L;
	@NonNull
	private String word;				// 세 개를 한 줄에 쓰면 Duplicate method in type 에러 발생
	private String speechOfPart;
	private List<String> mean = new ArrayList<String>();
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(word, other.word);
	}
	@Override
	public int hashCode() {
		return Objects.hash(word);
	}
	
	
}

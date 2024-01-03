package word_ysj;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
 * word : 단어
 * mean : 뜻 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {
	@NonNull
	private String word, mean;
	
	//단어 : 뜻 출력
	public void print() {
		System.out.println(word + " : " + mean);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		return Objects.equals(mean, other.mean) && Objects.equals(word, other.word);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mean, word);
	}
	
}

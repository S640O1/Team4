package word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Word implements Serializable{
	
	private static final long serialVersionUID = -2016745280033829563L;
	private String word;		//단어
	private String speechOfPart;//품사
	private List<String> mean = new ArrayList<String>();//뜻
	private int count;	//단어 조회시 카운트 1 증가
}

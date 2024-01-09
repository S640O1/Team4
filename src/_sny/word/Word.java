package _sny.word;

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
	
	/* 피드백
	 * - 품사는 뜻마다 다름
	 * - 단어에 품사가 고정이 아님
	 * */
	private static final long serialVersionUID = -2016745280033829563L;
	private String word;		//단어
	private String speechOfPart;//품사
	private List<String> mean = new ArrayList<String>();//뜻
	private int count;	//단어 조회시 카운트 1 증가
}

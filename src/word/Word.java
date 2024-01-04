package word;

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
	private static final long serialVersionUID = -2016745280033829563L;
	private String word;
	private String speechOfPart;
	private List<String> mean = new ArrayList<String>();
	private int count;
//	int count;
}

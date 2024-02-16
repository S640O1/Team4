package cafe.pagination;

import lombok.Data;

@Data
public class Criteria {

	private int page; //현재 페이지
	private int perPageNum; //한 페이지에 최대 컨텐츠 개수
	private int pageStart;
	
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}

	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
		this.pageStart = getPageStart();
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
	
}

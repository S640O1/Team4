package cafe.pagination;

public class Criteria {

	private int page; //현재 페이지
	private int perPageNum; //한 페이지에 최대 컨텐츠 개수
	
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}

	public Criteria(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
	
}

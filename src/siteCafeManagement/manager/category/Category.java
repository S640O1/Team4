package siteCafeManagement.manager.category;

import lombok.Data;

	@Data
	public class Category {
	 
	private String categoryName;
	
	@Override
	public String toString() {
	return "Category{" + "categoryName='" + categoryName + '\'' + '}';
		}
	}
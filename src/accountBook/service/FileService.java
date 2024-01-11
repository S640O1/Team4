package accountBook.service;

import java.util.List;

import accountBook.Item;



public interface FileService {
	
	List<Item> load(String fileName);

	boolean save(String fileName, List<Item> list);
}

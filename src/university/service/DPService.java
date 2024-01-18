package university.service;

import java.util.List;
import university.Department;

public interface DPService {

    List<Department> getAllDepartments();

    Department addDepartment(List<Department> departmentList);

    boolean editDepartment(List<Department> departmentList);

    boolean deleteDepartment(List<Department> departmentList);

	List<Department> getAllDepartments(List<Department> departmentList);

	void printDepartments(List<Department> dList);
}
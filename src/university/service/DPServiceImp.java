package university.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import university.Department;
import university.UniversityProgram;

public class DPServiceImp implements DPService {

    @Override
    public List<Department> getAllDepartments(List<Department> departmentList) {
        // 학과 목록 조회
    		
        return departmentList;
    }
    /** 학과를 등록하는 메소드*/
    @SuppressWarnings("unchecked")
	@Override
    public List<Department> addDepartment(List<Department> departmentList) {
        System.out.println("새로운 학과를 등록합니다.");

        // 사용자로부터 학과 정보 입력 받기
        System.out.print("학과 분류 번호: ");
        int dpNum = UniversityProgram.scan.nextInt();
        UniversityProgram.scan.nextLine(); // 버퍼 비우기
        System.out.print("학과명: ");
        String dpName = UniversityProgram.scan.nextLine();

        // 새로운 학과 객체 생성
        Department newDepartment = new Department(dpNum, dpName, null, null);

        // 중복된 학과 시 

  		for(int i=0; i<departmentList.size(); i++) {
  			if(departmentList.get(i).equals(dpNum)) {
  				System.out.println("이미 등록된 강의입니다.");
  				return departmentList;
  			}
  		}
      		
        // 리스트에 추가
        departmentList.add(newDepartment);
        System.out.println("학과가 성공적으로 등록되었습니다.");

        return departmentList;
    }
    /** 학과를 수정하는 메소드*/
    @Override
    public boolean editDepartment(List<Department> departmentList) {
        System.out.println("학과를 수정합니다.");
        
        //학과리스트 출력
        
        //사용자로부터 수정할 학과 선택 받기
        
        int index = UniversityProgram.scan.nextInt() - 1;
        
        //인덱스값이 맞는지 확인
        if(index <= 0 || index >departmentList.size()) {
        	//잘못된 값이라고 알려주고
        	return false;
        }
        
        //수정할 내용 입력 받기
        
        //입력받은 내용으로 수정
        departmentList.get(index).setDpNum(0);
        departmentList.get(index).setDpName(null);
        
        System.out.println("학과가 성공적으로 등록되었습니다.");
        
        //
//        Department targetDepartment = new Department(0, null, null, null);
//        departmentList.get(index).setDpName(targetDepartment.getDpName());
        

        
        
        
        // 사용자로부터 수정할 학과명 입력 받기
        System.out.print("수정할 학과명: ");
        UniversityProgram.scan.nextLine();
        String targetDepartmentName = UniversityProgram.scan.nextLine();

        // 해당 학과를 찾아서 수정
        List<Department> targetDepartments = departmentList.stream()
                .filter(department -> department.getDpName().equals(targetDepartmentName))
                .collect(Collectors.toList());

        if (targetDepartments.isEmpty()) {
            System.out.println("해당 학과를 찾을 수 없습니다.");
            return false;
        }

        // 수정할 내용 입력 받기
        Department targetDepartment = targetDepartments.get(0);
        System.out.print("수정할 학과 분류 번호: ");
        int newDpNum = UniversityProgram.scan.nextInt();
        UniversityProgram.scan.nextLine(); // 버퍼 비우기
        System.out.print("수정할 학과명: ");
        String newDpName = UniversityProgram.scan.nextLine();

        // 수정된 정보로 업데이트
        targetDepartment.setDpNum(newDpNum);
        targetDepartment.setDpName(newDpName);

        System.out.println("학과가 성공적으로 수정되었습니다.");
        return true;
    }

    @Override
    public boolean deleteDepartment(List<Department> departmentList) {
        System.out.println("학과를 삭제합니다.");

        // 사용자로부터 삭제할 학과명 입력 받기
        System.out.print("삭제할 학과명: ");
        UniversityProgram.scan.nextLine();
        String targetDepartmentName = UniversityProgram.scan.nextLine();
        
        //학과 리스트가 존재하지 않을 때
        	//return;

        // 해당 학과를 찾아서 삭제
        List<Department> targetDepartments = departmentList.stream()
                .filter(department -> department.getDpName().equals(targetDepartmentName))
                .collect(Collectors.toList());

        if (targetDepartments.isEmpty()) {
            System.out.println("해당 학과를 찾을 수 없습니다.");
            return false;
        }

        // 리스트에서 해당 학과 삭제
        departmentList.remove(targetDepartments.get(0));
        System.out.println("학과가 성공적으로 삭제되었습니다.");
        return true;
    }

 
	@Override
	public boolean printDepartments(List<Department> dList) {
		if(dList.isEmpty()) {
			System.out.println("등록된 학과가 없습니다.");
			return false;
		}
		System.out.println(" 순번\t코드\t학과명");
		System.out.println("---------------------------------------");
		for(int i = 0; i<dList.size(); i++) {
			System.out.println("[" +(i+1) + "] "+ dList.get(i).toString());
    	}
		return true;
	}

}
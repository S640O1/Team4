# 강사 피드백

## 공통(이상적인, 나중에 DB와 연결했을 때를 고려한다면)

- Department, Lecture, Professor, Student, Score 클래스에서 서로를 멤버로 참조하는 문제가 있어요.
- 예로 Department 클래스에 학생 리스트가 있고, Student 클래스에는 과를 참고있어요.
- 그래서 해결하기 위해서 관련 있는 클래스를 참조하는 새로운 클래스를 추가해야 해요.
    - 학생이 소속된 과를 처리하기 위해 다음 클래스를 만들면 서로 참조하는 상황을 막을 수 있어요.
    - 학생 클래스에 학과 멤버를 지우고, 과 클래스에 학생 리스트를 지운 후 UniversityProgram에서 DepartmentAffiliation 클래스를 리스트로 관리하면 어떤 학생이 어느 과에 소속되어 있는지 알 수 있고, 특정과에 소속된 학생들을 알 수 있어요.
    
    ```java
    class DepartmentAffiliation{
    	DepartMent dm;
    	Student std;
    }
    class Score{
    	Lecture lecture;
    	Student student;
    	double score;
    }
    //지도클래스에서 교수와 학생을 멤버로
    //강의 클래스에서 교수와 Lecture 클래스를 멤버로
    //수강 클래스에서 학생과 Lecture 클래스를 멤버로
    //교수 소속 클래스에서 교수와 과를 멤버로
    //학생 소속 클래스에서 학생과 과를 멤버로
    ```
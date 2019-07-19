package report;

public class StudentInfo {
    private int id;
    private String name;

    public StudentInfo(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int studentId(){
        return id;
    }

    @Override
    public String toString(){
        return "Student Id: " + this.id + ", " + "name: " + this.name + "\n";
    }


}

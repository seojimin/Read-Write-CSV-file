package report;

public class MarkInfo {
    private int id;
    private int studentId;
    private double mark;

    public MarkInfo(int id, int s_id, double m_id){
        this.id = id;
        studentId = s_id;
        mark = m_id;
    }

    public int getMarkTestId(){
        return id;
    }

    public int getMarkStudentId(){
        return studentId;
    }

    public double getMark(){
        return mark;
    }

    @Override
    public String toString(){
        return "test_id: " + this.id + ", " + "student id: " + this.studentId + ", " + "mark: " + this.mark + "\n";
    }
}

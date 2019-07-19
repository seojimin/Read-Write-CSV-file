package report;

public class TestInfo {
    private int id;
    private int courseId;
    private double weight;

    public TestInfo(int id, int c_id, double weight){
        this.id = id;
        courseId = c_id;
        this.weight = weight;
    }

    public int getTestId(){
        return id;
    }

    public int getCourseId(){
        return courseId;
    }

    public double getWeight(){
        return weight;
    }

    @Override
    public String toString(){
        return "Test id: " + this.id + ", " + "course id: " + this.courseId + ", " + "weight" + this.weight + "\n";
    }
}

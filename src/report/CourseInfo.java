package report;

public class CourseInfo {
    private int id;
    private String name;
    private String teacher;

    public CourseInfo(int id, String courseName, String courseTeacher){
        this.id = id;
        name = courseName;
        teacher = courseTeacher;
    }

    public int getCourseId(){
        return id;
    }

    public String getCourseName(){
        return name;
    }

    public String getCourseTeacher(){
        return teacher;
    }

    @Override
    public String toString(){
        return "Course: " + this.name + ", " + "Teacher: " + this.teacher + "\n";
    }
}

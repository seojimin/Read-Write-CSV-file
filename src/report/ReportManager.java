package report;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportManager {
    //ArrayList
    ArrayList<CourseInfo> courseInfos = new ArrayList<>();
    ArrayList<MarkInfo> markInfos = new ArrayList<>();
    ArrayList<TestInfo> testInfos = new ArrayList<>();
    ArrayList<StudentInfo> studentInfos = new ArrayList<>();

    //singleton pattern
    static ReportManager inst = null;

    public static ReportManager createReportManager() {
        if (inst == null)
            inst = new ReportManager();
        return inst;
    }

    private ReportManager() {
    }

    public void readFile(String csvFile) throws FileNotFoundException {
        BufferedReader dataFile = new BufferedReader(new FileReader("src" + File.separator + "csv_input" + File.separator + csvFile));

        try {
            //skip the first line
            String re = dataFile.readLine(); //id, name

            while ((re = dataFile.readLine()) != null) {

                String[] data = re.split(",");

                switch (csvFile) {
                    case "courses.csv":
                        courseInfos.add(new CourseInfo(Integer.parseInt(data[0]), data[1], data[2]));
                        break;
                    case "marks.csv":
                        markInfos.add(new MarkInfo(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Double.parseDouble(data[2])));
                        break;
                    case "students.csv":
                        studentInfos.add(new StudentInfo(Integer.parseInt(data[0]), data[1]));
                        break;
                    case "tests.csv":
                        testInfos.add(new TestInfo(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Double.parseDouble(data[2])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //calculate weight
    private ArrayList<MarkInfo> getWeight(){
        ArrayList<MarkInfo> list = new ArrayList<>();
        int t_id = 0;
        int s_id = 0;
        double weight = 0;
        for (MarkInfo m : markInfos) {
            for (TestInfo t : testInfos) {
                if (t.getTestId() == m.getMarkTestId()) {
                    t_id = m.getMarkTestId();
                    s_id = m.getMarkStudentId();
                    weight = m.getMark() * (t.getWeight() / 100);
                    list.add(new MarkInfo(t_id, s_id, weight));
                }
            }
        }

        return list;

    }

    public void writeFile() throws IOException {
        //create text file
        File file = new File("src" + File.separator + "txt_output" + File.separator + "reportCard.txt");

        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //MarkInfo list (weighted mark)
        ArrayList<MarkInfo> weightedMarks = getWeight();

        for (int i = 0; i < studentInfos.size(); i++) {
            output.write(studentInfos.get(i).toString()); // student Id: name:

            //Total Average
            double total = 0;

            HashMap<Integer, Double> map = new HashMap<>();

            //weighted mark
            for (MarkInfo m : weightedMarks) {
                //mark student_Id == student_Id
                if (m.getMarkStudentId() == studentInfos.get(i).studentId()) {
                    //Test
                    for (TestInfo t : testInfos) {
                        //Test_id == Mark test_id
                        if (t.getTestId() == m.getMarkTestId()) {
                            total += m.getMark(); //adding weighted mark
                            if (!map.containsKey(t.getCourseId()))map.put(t.getCourseId(), m.getMark());
                            else map.put(t.getCourseId(), map.get(t.getCourseId()) + m.getMark());
                        }
                    }
                }
            }
            output.write("Total Average: " + "\t" + "\t" + String.format("%.2f", total / map.size()) + "%" + "\n" + "\n");

            for (CourseInfo c : courseInfos) {
                output.write("\t" + "\t" + c.toString());
                output.write("\t" + "\t" + "Final Grade: " + "\t" + String.format("%.2f", map.get(c.getCourseId())) + "%" + "\n" + "\n");
            }
            output.write("\n");
        }

        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}




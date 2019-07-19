package report;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ReportManager reportManager = ReportManager.createReportManager();

        //assign the CSV file into readFile method
        reportManager.readFile("courses.csv");
        reportManager.readFile("marks.csv");
        reportManager.readFile("students.csv");
        reportManager.readFile("tests.csv");

        //write txt file.
        try {
            reportManager.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

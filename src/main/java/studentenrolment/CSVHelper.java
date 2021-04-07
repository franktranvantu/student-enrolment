package studentenrolment;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CSVHelper {

    private static final StudentEnrolmentManager STUDENT_ENROLMENT_MANAGER = new ConsoleStudentEnrolment();

    public void exportAllCoursesForSpecificStudentInSpecificSemester(List<Course> courses) {
        try {
            String fileName = "all-courses-for-1-student-in-1-semester.csv";
            String[] headers = {"Course Id", "Course Name", "Credit Number"};
            FileWriter out = new FileWriter(fileName);
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                for (Course course : courses) {
                    printer.printRecord(course.getId(), course.getName(), course.getCreditNumber());
                }
            }
            System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportAllStudentsOfSpecificCourseInSpecificSemester(List<Student> students) {
        try {
            String fileName = "all-students-for-1-course-in-1-semester.csv";
            String[] headers = {"StudentId Id", "Student Name", "Birthday"};
            FileWriter out = new FileWriter(fileName);
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                for (Student student : students) {
                    printer.printRecord(student.getId(), student.getName(), student.getBirthday());
                }
            }
            System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportAllCoursesOfferedInSpecificSemester(List<Course> courses) {
        try {
            String fileName = "all-courses-offered-in-1-semester.csv";
            String[] headers = {"Course Id", "Course Name", "Credit Number"};
            FileWriter out = new FileWriter(fileName);
            try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                for (Course course : courses) {
                    printer.printRecord(course.getId(), course.getName(), course.getCreditNumber());
                }
            }
            System.out.printf("Exported report. File name is %s%n", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEnrolments(String fileName) {
        String[] headers = {"Student Id","Student Name","Birthday","Course Id","Course Name","Credit Number","Semester"};
        Reader in;
        try {
            in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                int studentId = Integer.parseInt(record.get(headers[0]));
                String studentName = record.get(headers[1]);
                String birthday = record.get(headers[2]);
                int courseId = Integer.parseInt(record.get(headers[3]));
                String courseName = record.get(headers[4]);
                String creditNumber = record.get(headers[5]);
                String semester = record.get(headers[6]);

                Student student = new Student(studentId, studentName, LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                Course course = new Course(courseId, courseName, creditNumber);
                StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, semester);
                STUDENT_ENROLMENT_MANAGER.add(studentEnrolment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

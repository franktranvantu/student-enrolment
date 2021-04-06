package studentenrolment;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StudentEnrolmentManager ENROLMENT_MANAGER = new ConsoleStudentEnrolment();
    private static final StudentDao STUDENT_DAO = new StudentDao();
    private static final CourseDao COURSE_DAO = new CourseDao();

    CourseDao courseDao = new CourseDao();

    public static void main(String[] args) {
        int choose;
        do {
            System.out.println("============================== Student Enrollment Program =========================");
            STUDENT_DAO.printAll();
            COURSE_DAO.print(COURSE_DAO.getAll());
            System.out.println("1. Enroll a student for 1 semester");
            System.out.println("2. Update");
            System.out.println("0. Exit");
            System.out.print("Enter your choose: ");
            choose = Integer.parseInt(SCANNER.nextLine());
            switch (choose) {
                case 1:
                    System.out.println("=========== Enroll a student for 1 semester ==========");
                    System.out.print("\tEnter student id: ");
                    int studentId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter course id: ");
                    int courseId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    String semester = SCANNER.nextLine();

                    Student student = STUDENT_DAO.getOne(studentId);
                    Course course = COURSE_DAO.getOne(courseId);
                    ENROLMENT_MANAGER.add(new StudentEnrolment(student, course, semester));

                    ENROLMENT_MANAGER.printAllCoursesForStudentInSemester();
            }

        } while (choose != 0);
    }
}

package studentenrolment;

import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final StudentEnrolmentManager ENROLMENT_MANAGER = new ConsoleStudentEnrolment();
    private static final StudentDao STUDENT_DAO = new StudentDao();
    private static final CourseDao COURSE_DAO = new CourseDao();
    private static final CSVHelper CSV_HELPER = new CSVHelper();

    public static void main(String[] args) {
        System.out.println("Do you want to load enrolments from any file or not? Yes(Y) / No(N)");
        String c = SCANNER.nextLine();
        String fileName;
        if ("Y".equalsIgnoreCase(c)) {
            System.out.print("Enter file name: ");
            fileName = SCANNER.nextLine();
        } else {
            fileName = "default.csv";
        }
        CSV_HELPER.loadEnrolments(fileName);
        int choose;
        do {
            System.out.println("============================== Student Enrollment Program =========================");
            STUDENT_DAO.print(STUDENT_DAO.getAll());
            COURSE_DAO.print(COURSE_DAO.getAll());
            System.out.println("1. Print all courses for 1 student in 1 semester");
            System.out.println("2. Print all students of 1 course in 1 semester");
            System.out.println("3. Print all courses offered in 1 semester");
            System.out.println("4. Enroll a student for 1 semester");
            System.out.println("5. Update an enrolment of a student for 1 semester");
            System.out.println("6. Delete an enrolment of a student for 1 semester");
            System.out.println("0. Exit");
            System.out.print("Enter your choose: ");
            choose = Integer.parseInt(SCANNER.nextLine());
            int studentId;
            int courseId;
            String semester;
            switch (choose) {
                case 1:
                    System.out.println("=========== Print all courses for 1 student in 1 semester ==========");
                    System.out.print("\tEnter student id: ");
                    studentId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();
                    ENROLMENT_MANAGER.printAllCoursesForSpecificStudentInSpecificSemester(studentId, semester);
                    break;
                case 2:
                    System.out.println("=========== Print all students of 1 course in 1 semester ==========");
                    System.out.print("\tEnter course id: ");
                    courseId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();
                    ENROLMENT_MANAGER.printAllStudentsOfSpecificCourseInSpecificSemester(courseId, semester);
                    break;
                case 3:
                    System.out.println("=========== Print all courses offered in 1 semester ==========");
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();
                    ENROLMENT_MANAGER.printAllCoursesOfferedInSpecificSemester(semester);
                    break;
                case 4:
                    System.out.println("=========== Enroll a student for 1 semester ==========");
                    System.out.print("\tEnter student id: ");
                    studentId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter course id: ");
                    courseId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();

                    Student student = STUDENT_DAO.getOne(studentId);
                    Course course = COURSE_DAO.getOne(courseId);
                    ENROLMENT_MANAGER.add(new StudentEnrolment(student, course, semester));
                    break;
                case 5:
                    System.out.println("=========== Update an enrolment of a student for 1 semester ==========");
                    System.out.print("\tEnter student id: ");
                    studentId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();
                    ENROLMENT_MANAGER.printAllCoursesForSpecificStudentInSpecificSemester(studentId, semester);
                    System.out.print("\tEnter old course id: ");
                    int oldCourseId = Integer.parseInt(SCANNER.nextLine());
                    StudentEnrolment studentEnrolment = ENROLMENT_MANAGER.getOneByStudentAndCourseAndSemester(studentId, oldCourseId, semester);
                    System.out.print("\tEnter new course id: ");
                    int newCourseId = Integer.parseInt(SCANNER.nextLine());
                    int enrolmentId = studentEnrolment.getId();
                    Course newCourse = COURSE_DAO.getOne(newCourseId);
                    studentEnrolment.setCourse(newCourse);
                    ENROLMENT_MANAGER.update(enrolmentId, studentEnrolment);
                    break;
                case 6:
                    System.out.println("=========== Delete an enrolment of a student for 1 semester ==========");
                    System.out.print("\tEnter student id: ");
                    studentId = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("\tEnter semester: ");
                    semester = SCANNER.nextLine();
                    ENROLMENT_MANAGER.printAllCoursesForSpecificStudentInSpecificSemester(studentId, semester);
                    System.out.print("\tEnter course id: ");
                    courseId = Integer.parseInt(SCANNER.nextLine());
                    ENROLMENT_MANAGER.deleteByCourseId(courseId);
            }
        } while (choose != 0);
    }
}

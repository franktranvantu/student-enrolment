package studentenrolment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private static StudentEnrolmentManager studentEnrolmentManager;
    private static StudentDao studentDao;
    private static CourseDao courseDao;

    @BeforeEach
    public void setUp() {
        studentEnrolmentManager = new ConsoleStudentEnrolment();
        studentDao = new StudentDao();
        courseDao = new CourseDao();
    }

    @Test
    public void testEnrolmentIdWhenAddNewEnrolment() {
        Student student = studentDao.getOne(1);
        Course course = courseDao.getOne(1);
        String semester = "2021A";
        studentEnrolmentManager.add(new StudentEnrolment(student, course, semester));
        assertEquals(1, studentEnrolmentManager.getOne(1).getId());
    }

    @Test
    public void testEnrolmentStudentNameWhenAddNewEnrolment() {
        Student student = studentDao.getOne(1);
        Course course = courseDao.getOne(1);
        String semester = "2021A";
        studentEnrolmentManager.add(new StudentEnrolment(student, course, semester));
        StudentEnrolment enrolment = studentEnrolmentManager.getOneByStudentAndCourseAndSemester(student.getId(), course.getId(), semester);
        assertEquals("Student 1", enrolment.getStudent().getName());
    }

    @Test
    public void testEnrolmentCourseNameWhenAddNewEnrolment() {
        Student student = studentDao.getOne(1);
        Course course = courseDao.getOne(2);
        String semester = "2021A";
        studentEnrolmentManager.add(new StudentEnrolment(student, course, semester));
        StudentEnrolment enrolment = studentEnrolmentManager.getOneByStudentAndCourseAndSemester(student.getId(), course.getId(), semester);
        assertEquals("Course 2", enrolment.getCourse().getName());
    }

    @Test
    public void testEnrolmentCourseNameWhenUpdateEnrolment() {
        Student student = studentDao.getOne(1);
        Course course = courseDao.getOne(1);
        String semester = "2021A";
        studentEnrolmentManager.add(new StudentEnrolment(student, course, semester));
        StudentEnrolment enrolment = studentEnrolmentManager.getOneByStudentAndCourseAndSemester(student.getId(), course.getId(), semester);
        enrolment.setCourse(courseDao.getOne(2));
        studentEnrolmentManager.update(enrolment.getId(), enrolment);
        StudentEnrolment newEnrolment = studentEnrolmentManager.getOne(enrolment.getId());
        assertEquals("Course 2", newEnrolment.getCourse().getName());
    }
}

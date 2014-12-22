package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;
import mu.lab.model.Student;
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guangchen on 12/19/14 20:10.
 */

@Service
@Transactional
public class CourseService {

    /**
     * default page size of course list
     */
    public static final int page_size = 20;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;

    /**
     * get total numbers of courses.
     * @return number of courses.
     */
    public long getNumberOfCourses() {
        return courseRepository.count();
    }

    public Course createCourse(String courseId, String courseName, Float stdev) {
        return courseRepository.save(new Course(courseId, courseName, stdev));
    }

    public Course getCourseByCourseId(String courseId) {
        return courseRepository.findBySchemaPropertyValue("courseId", courseId);
    }

    public void addRelationshipBetweenStudentAndCourse(String courseId, String studentId, Integer score) {
        Course course = this.getCourseByCourseId(courseId);
        assert course != null;
        Student student = studentRepository.findBySchemaPropertyValue("studentId", studentId);
        if(student == null) {
            student = studentRepository.save(new Student(studentId));
        }
        student.takeExamsIn(course, score);
        studentRepository.save(student);
        course.addStudent(student);
        courseRepository.save(course);
    }

    public Score addRelationshipBetweenStudentAndCourse(Long studentId, String courseId, Integer score) {
        Course course = this.getCourseByCourseId(courseId);
        assert course != null;
        Student student = studentRepository.findOne(studentId);
        assert student != null;
        Score s = student.takeExamsIn(course, score);
        course.addStudent(student);
        studentRepository.save(student);
        courseRepository.save(course);
        return s;
    }

    public void getStudentForCourse(Course course) {
        template.fetch(course.studentSet);
    }

    /**
     * Get course list
     * @param skip skip
     * @param limit limit
     * @return courses
     */
    public Iterable<Course> getCourses(int skip, int limit) {
        return courseRepository.getCourseList(skip, limit);
    }

    /**
     * Get course list, skip=0, limit={@link #page_size}
     * @return course list
     */
    public Iterable<Course> getCourses() {
        return this.getCourses(0, page_size);
    }

    /**
     * Get course list of given page, use default {@link @page_size}
     * @param page page
     * @return course list
     */
    public Iterable<Course> getCourses(int page) {
        return this.getCourses(page * page_size, page_size);
    }
}

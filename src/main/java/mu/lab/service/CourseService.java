package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guangchen on 12/19/14 20:10.
 */

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

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
}

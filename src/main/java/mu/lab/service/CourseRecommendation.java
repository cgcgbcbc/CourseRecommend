package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;
import mu.lab.model.Student;
import mu.lab.repo.CourseRepository;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guangchen on 12/18/14 22:04.
 */
@Service
public class CourseRecommendation implements ICourseRecommendation {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    IUserService userService;

    @Autowired
    CourseService courseService;

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId) {
        return this.getRecommendCourse(studentId, 5);
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, int n) {
        return courseRepository.recommendCourseBasedOnSimilarity(studentId, n);
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourseBasedOnMockScores(List<Score> scores) {
        Student student = userService.createFakeStudent();
        for (Score score : scores) {
            courseService.addRelationshipBetweenStudentAndCourse(student.getId(), score.getCourse().courseId, score.getScore());
        }
        studentRepository.addSimilarityRelation(student.getId());
        Iterable<Course> courses = getRecommendCourse(student.getId());
        studentRepository.delete(student);
        return courses;
    }


}

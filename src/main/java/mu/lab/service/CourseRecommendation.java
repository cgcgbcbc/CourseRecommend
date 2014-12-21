package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;
import mu.lab.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guangchen on 12/18/14 22:04.
 */
@Service
public class CourseRecommendation implements ICourseRecommendation {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Student getRandomStudent(int seed) {
        return null;
    }

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


}

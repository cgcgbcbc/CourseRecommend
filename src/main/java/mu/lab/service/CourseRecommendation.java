package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;
import org.springframework.stereotype.Service;

/**
 * Created by guangchen on 12/18/14 22:04.
 */
@Service
public class CourseRecommendation implements ICourseRecommendation {
    @Override
    public Student getRandomStudent(int seed) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, int n) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId) {
        return null;
    }


}

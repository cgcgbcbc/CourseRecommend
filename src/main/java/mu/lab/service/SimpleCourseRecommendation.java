package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;
import org.springframework.stereotype.Service;

/**
 * Created by guangchen on 12/18/14 22:18.
 */
@Service
public class SimpleCourseRecommendation implements ICourseRecommendation {
    @Override
    public Student getRandomStudent(int seed) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourse(Student student) {
        return null;
    }

    @Override
    public Iterable<Course> getRecommendCourse(Student student, Course course) {
        return null;
    }
}

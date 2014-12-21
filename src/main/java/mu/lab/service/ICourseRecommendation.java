package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;

/**
 * Created by guangchen on 12/18/14 22:01.
 */
public interface ICourseRecommendation {
    @Deprecated
    public Student getRandomStudent(int seed);
    public Iterable<Course> getRecommendCourse(Long studentId);
    public Iterable<Course> getRecommendCourse(Long studentId, int n);
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId);
}

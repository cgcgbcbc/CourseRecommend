package mu.lab.mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Student;

/**
 * Created by guangchen on 12/18/14 22:01.
 */
public interface ICourseRecommendation {
    public Student getRandomStudent(int seed);
    public Iterable<Course> getRecommendCourse(Student student);
    public Iterable<Course> getRecommendCourse(Student student, Course course);
}

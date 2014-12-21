package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;

/**
 * Created by guangchen on 12/18/14 22:01.
 */
public interface ICourseRecommendation {
    public Iterable<Course> getRecommendCourse(Long studentId);
    public Iterable<Course> getRecommendCourse(Long studentId, int n);
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId);
    public Iterable<Course> getRecommendCourseBasedOnMockScores(Score... scores);
}

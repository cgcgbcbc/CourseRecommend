package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;

import java.util.List;

/**
 * Created by guangchen on 12/18/14 22:01.
 */
public interface ICourseRecommendation {
    /**
     * Directly get 5 courses recommended for the given student.
     * @param studentId The graphId of the student node
     * @return Recommend course list
     */
    public Iterable<Course> getRecommendCourse(Long studentId);

    /**
     * get n courses recommended for the given student.
     * @param studentId The graphId of the student node
     * @param n n
     * @return Recommend course list
     */
    public Iterable<Course> getRecommendCourse(Long studentId, int n);

    /**
     * NOT IMPLEMENTED YET!!!
     * @param studentId The graphId of the student node
     * @param courseId The graphId of a course node
     * @return Recommend course list
     */
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId);

    /**
     *
     * @param scores Score list
     * @return recommend courses based on the given scores.
     */
    public Iterable<Course> getRecommendCourseBasedOnMockScores(List<Score> scores);
}

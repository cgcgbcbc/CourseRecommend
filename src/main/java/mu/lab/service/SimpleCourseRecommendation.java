package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guangchen on 12/18/14 22:18.
 */
@Service
public class SimpleCourseRecommendation implements ICourseRecommendation {

    /**
     * Default recommend course count
     */
    public static final int DEFAULT_RECOMMEND_COURSE_COUNT = 5;

    public static final int DEFAULT_COURSE_STUDENT_BOUND = 10;

    @Autowired
    CourseRepository courseRepository;

    /**
     *
     * @param studentId the student whom recommending courses to
     * @return Recommended {@link #DEFAULT_RECOMMEND_COURSE_COUNT} courses based on student's all learned course
     */
    @Override
    public Iterable<Course> getRecommendCourse(Long studentId) {
        return getRecommendCourse(studentId, DEFAULT_RECOMMEND_COURSE_COUNT);
    }

    /**
     * Algorithm:
     * @param studentId the student whom recommending courses to
     * @param n the maximum of courses to recommend
     * @return Recommended courses
     */
    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, int n) {
        return courseRepository.simpleRecommendCourse(studentId, DEFAULT_COURSE_STUDENT_BOUND, n);
    }

    @Override
    public Iterable<Course> getRecommendCourse(Long studentId, Long courseId) {
        return null;
    }
}

package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guangchen on 12/21/14 13:32.
 */
@Service
@Transactional
public class ScoreService {
    @Autowired CourseService courseService;

    /**
     * create a fake score
     * @param courseId courseId of the course
     * @param score score
     * @return a fake score which used in {@link ICourseRecommendation#getRecommendCourseBasedOnMockScores}
     */
    public Score createFakeScore(String courseId, Integer score) {
        Course course = courseService.getCourseByCourseId(courseId);
        return new Score(course, score);
    }
}

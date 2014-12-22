package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class SimpleCourseRecommendationTest {

    @Autowired@Qualifier("simpleCourseRecommendation")
    ICourseRecommendation courseRecommendation;

    @Autowired
    ScoreService scoreService;

    @Test
    public void testGetRecommendCourseBasedOnMockScores() throws Exception {
        List<Score> scores = new ArrayList<Score>();
        scores.add(scoreService.createFakeScore("10610183", 99));
        Iterable<Course> courses = courseRecommendation.getRecommendCourseBasedOnMockScores(scores);
        assertNotNull(courses);
        assertTrue(courses.iterator().hasNext());
    }
}
package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.model.Score;
import mu.lab.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-context.xml")
public class UserServiceTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;

    @Autowired IUserService userService;
    @Autowired CourseService courseService;

    String courseId = "courseId";
    String courseName = "courseName";
    float courseStd = (float) 0.9;
    String studentId = "studentId";
    int score = 90;


    @Before
    public void Setup() {
        Neo4jHelper.cleanDb(template);
        courseService.createCourse(courseId, courseName, courseStd);
        courseService.addRelationshipBetweenStudentAndCourse(courseId, studentId, score);
    }

    @After
    public void tearDown() {
        Neo4jHelper.cleanDb(template);
    }

    @Test
    public void testGetRandomStudent() throws Exception {
        Student student = userService.getRandomStudent(0);
        assertNotNull(student);
        assertNotNull(student.scoreSet);
        Score s = student.scoreSet.iterator().next();
        assertNotNull(s.getScore());
        Set<Course> course = student.getCourses();
        assertEquals(1, course.size());
        assertEquals(courseId, course.iterator().next().courseId);
    }

    @Test
    public void testGetDistanceDistribution() throws Exception {
        String studentId2 = "test";
        courseService.addRelationshipBetweenStudentAndCourse(courseId, studentId2, 100);
        Student student = userService.getRandomStudent(0);
        Map<Integer, Integer> map = userService.getDistanceDistribution(student.getId());
        assertEquals((Integer)1, map.get(1));
    }
}
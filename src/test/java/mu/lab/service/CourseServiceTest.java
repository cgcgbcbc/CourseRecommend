package mu.lab.service;

import mu.lab.model.Course;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-context.xml")
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;

    @Before
    public void Setup() {
        Neo4jHelper.cleanDb(template);
    }

    @After
    public void tearDown() {
        Neo4jHelper.cleanDb(template);
    }

    @Test
    public void testGetNumberOfCourses() throws Exception {
        assertEquals(0, courseService.getNumberOfCourses());
    }

    @Test
    public void testCreateCourse() throws Exception {
        String courseId = "00690632";
        Course course = courseService.createCourse(courseId, "大学语文", new Float(1.69967));
        assertNotNull(course);
        assertNotNull(course.getId());
        assertEquals(1, courseService.getNumberOfCourses());
        course = courseService.getCourseByCourseId(courseId);
        assertNotNull(course);
        assertEquals(course.courseName, course.courseName);

        String studentId = "abcde";
        courseService.addRelationshipBetweenStudentAndCourse(courseId, studentId, 99);
        course = courseService.getCourseByCourseId(courseId);
        courseService.getStudentForCourse(course);
        assertNotEquals(course.studentSet.size(), 0);
        assertEquals(studentId, course.studentSet.iterator().next().studentId);
        assertNotEquals(course.studentSet.iterator().next().scoreSet.size(), 0);
    }
}
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        Course _course = courseService.getCourseByCourseId(courseId);
        assertNotNull(_course);
        assertEquals(course.courseName, _course.courseName);
    }
}
package mu.lab.repo;

import mu.lab.model.Course;
import mu.lab.model.Student;
import mu.lab.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class CourseRepositoryTest {

    @Autowired CourseRepository courseRepository;

    @Autowired
    IUserService userService;

    @Test
    public void testGetTopCourses() throws Exception {
        Student student = userService.getRandomStudent(0);
        Iterable<Course> courses = courseRepository.simpleRecommendCourse(student.getId(), 10, 3);
        assertNotNull(courses);
        assertTrue(courses.iterator().hasNext());
    }

    @Test
    public void testGetRecommend() throws Exception {
        Student student = userService.getRandomStudent(0);
        Iterable<Course> courses = courseRepository.recommendCourseBasedOnSimilarity(student.getId(), 5);
        assertNotNull(courses);
        assertTrue(courses.iterator().hasNext());
    }
}
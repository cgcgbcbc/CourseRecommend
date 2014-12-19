package mu.lab.util;

import mu.lab.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by guangchen on 12/19/14 22:53.
 */
@Component
public class DropCourse {
    @Autowired
    CourseRepository courseRepository;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
        DropCourse instance = context.getBean(DropCourse.class);
        System.out.printf("course count: %d\n", instance.courseRepository.count());
        instance.courseRepository.deleteAll();
        System.out.printf("after deleteAll: %d\n", instance.courseRepository.count());
    }
}

package mu.lab.util;

import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by guangchen on 12/20/14 17:11.
 */
@Component
public class DropScores {
    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
        DropScores instance = context.getBean(DropScores.class);
        System.out.printf("student count: %d\n", instance.studentRepository.count());
        instance.studentRepository.deleteAll();
        System.out.printf("after deleteAll: %d\n", instance.studentRepository.count());
    }
}

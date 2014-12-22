package mu.lab.util;

import mu.lab.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by guangchen on 12/20/14 16:16.
 */
@Component
public class ImportScores {
    @Autowired
    CourseService courseService;
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml");
        ImportScores instance = context.getBean(ImportScores.class);
        System.out.println("Reading files..." + args[0]);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            instance.importScore(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Importing finished, without errors.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importScore(BufferedReader bufferedReader) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] scores = line.split(",");
            courseService.addRelationshipBetweenStudentAndCourse(scores[2], scores[1], Integer.valueOf(scores[4]));
        }
    }
}

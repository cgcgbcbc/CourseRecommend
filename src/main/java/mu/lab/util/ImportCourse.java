package mu.lab.util;

import mu.lab.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by guangchen on 12/19/14 20:48.
 */

@Component
public class ImportCourse {
    @Autowired
    private CourseService courseService;

    public static void main(String[] args) {
        System.out.println("Reading files..." + args[1]);
        ImportCourse instance = new ImportCourse();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[1]));
            instance.importCourse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void importCourse(@NotNull BufferedReader bufferedReader ) throws IOException {
        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            String[] course = line.split(",");
            courseService.createCourse(course[0], course[1], Float.valueOf(course[2]));
        }

    }
}

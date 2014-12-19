package mu.lab.service;

import mu.lab.model.Course;
import mu.lab.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guangchen on 12/19/14 20:10.
 */

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public long getNumberOfCourses() {
        return courseRepository.count();
    }

    public Course createCourse(String courseId, String courseName, Float stdev) {
        return courseRepository.save(new Course(courseId, courseName, stdev));
    }

    public Course getCourseByCourseId(String courseId) {
        return courseRepository.findBySchemaPropertyValue("courseId", courseId);
    }
}

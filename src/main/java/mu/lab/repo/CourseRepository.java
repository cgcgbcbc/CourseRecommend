package mu.lab.repo;

import mu.lab.model.Course;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by guangchen on 12/18/14 18:26.
 */
public interface CourseRepository extends GraphRepository<Course> {
//    Iterable<Course> suggestSimilarCourses(Course course);
}

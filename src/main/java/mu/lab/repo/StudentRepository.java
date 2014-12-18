package mu.lab.repo;

import mu.lab.model.Student;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by guangchen on 12/18/14 18:26.
 */

public interface StudentRepository extends GraphRepository<Student> {
//    @Query("start student")
//    Iterable<Course> suggestCoursesByScores(Student me);
}

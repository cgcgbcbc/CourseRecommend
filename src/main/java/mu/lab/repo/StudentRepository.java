package mu.lab.repo;

import mu.lab.model.Student;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by guangchen on 12/18/14 18:26.
 */

public interface StudentRepository extends GraphRepository<Student> {
//    @Query("start student")
//    Iterable<Course> suggestCoursesByScores(Student me);
    @Query("match (a:Student)-[x:SCORE]->(c:Course)<-[y:SCORE]-(b:Student)\n" +
            "with a, b, case when abs(x.score-y.score) > 0 then 1-abs(x.score-y.score)/(2*c.standardDeviation) else 0.0 end as result\n" +
            "with a, b, sum(result) as sim\n" +
            "merge (a)-[s:Similarity]-(b) set s.similarity=sim;")
    void addSimilarityRelation();
}

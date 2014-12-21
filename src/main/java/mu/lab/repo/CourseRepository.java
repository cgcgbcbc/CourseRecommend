package mu.lab.repo;

import mu.lab.model.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by guangchen on 12/18/14 18:26.
 */
public interface CourseRepository extends GraphRepository<Course> {
    @Query("start n = node({0})\n" +
            "match (n)-[s:SCORE]->(course)-[:LEARN_BY]-(student) with s.score as score, course, count(*) as num, n\n" +
            "where num >= {1}\n" +
            "with n, course, score\n" +
            "order by score desc\n" +
            "limit {2}\n" +
            "match n, (course)-[:LEARN_BY]->(student)-[s:SCORE]->(newc)\n" +
            "with n, sum(s.score)/count(*) as a, newc, count(*) as num\n" +
            "where num >= {1}\n" +
            "with n, a , newc\n" +
            "order by a desc\n" +
            "with n, newc\n" +
            "where not(newc-[:LEARN_BY]->n)\n" +
            "return newc\n" +
            "limit {2};")
    Iterable<Course> simpleRecommendCourse(Long id, Integer courseStudentsBound, Integer limit);

        @Query("start a = node({0})\n" +
                "match (a)-[s:Similarity]-(b:Student)\n" +
                "with a, s.similarity as sim, b\n" +
                "order by sim desc\n" +
                "limit {1}\n" +
                "match a, (b)-[s:SCORE]->(c:Course)\n" +
                "where not( (a)-[:SCORE]->(c) )\n" +
                "with s.score as score, c\n" +
                "order by score desc\n" +
                "limit {1}\n" +
                "return c;")
        Iterable<Course> recommendCourseBasedOnSimilarity(Long studentId, Integer limit);

        @Query("match (c:Course) skip {0} limit {1};")
        Iterable<Course> getCourseList(int skip, int limit);
}

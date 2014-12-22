package mu.lab.service;

import mu.lab.model.Score;
import mu.lab.model.Student;
import mu.lab.repo.StudentRepository;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guangchen on 12/19/14 19:57.
 */
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private CourseService courseService;

    enum MyRelationshipTypes implements RelationshipType {
        SCORE, LEARN_BY
    }

    public static final int MAX_STEP = 5;
    @Autowired
    StudentRepository studentRepository;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;
    @Override
    public Student getRandomStudent(int seed) {
        long count = studentRepository.count();
        if (count == 0) return null;
        Student student = studentRepository.getStudentByIndex(seed % count);
        template.fetch(student.scoreSet);
        for (Score score : student.scoreSet) {
            template.fetch(score.getCourse());
        }
        return student;
    }

    @Override
    public Student createFakeStudent() {
        return studentRepository.save(new Student(""));
    }

    @Override
    public Map<Integer, Integer> getDistanceDistribution(int max_step, Long userId) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (int i = 0; i < max_step; i++) {
            TraversalDescription description = this.createTraversalDescriptionByDepth(2*(i+1));
            int count = 0;
            for (Student node : studentRepository.findAllByTraversal(studentRepository.findOne(userId), description)) {
                count ++;
            }
            result.put(i+1, count);
        }
        return result;
    }

    private TraversalDescription createTraversalDescriptionByDepth(int depth) {
        return template.getGraphDatabase().traversalDescription()
                .breadthFirst()
                .uniqueness(Uniqueness.NODE_GLOBAL)
                .relationships(MyRelationshipTypes.SCORE)
                .relationships(MyRelationshipTypes.LEARN_BY)
                .evaluator(Evaluators.atDepth(depth));
    }

    @Override
    public Map<Integer, Integer> getDistanceDistribution(Long userId) {
        return this.getDistanceDistribution(MAX_STEP, userId);
    }

    @Override
    public Map<Integer, Integer> getDistanceDistribution(List<Score> scores) {
        return this.getDistanceDistribution(scores, MAX_STEP);
    }

    @Override
    public Map<Integer, Integer> getDistanceDistribution(List<Score> scores, int max_step) {
        Student student = this.createFakeStudent();
        for (Score score : scores) {
            courseService.addRelationshipBetweenStudentAndCourse(student.getId(), score.getCourse().courseId, score.getScore());
        }
        Map<Integer, Integer> result = this.getDistanceDistribution(max_step, student.getId());
        studentRepository.delete(student);
        return result;
    }
}

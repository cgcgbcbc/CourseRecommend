package mu.lab.repo;

import junit.framework.TestCase;
import mu.lab.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-context.xml")
public class StudentRepositoryTest extends TestCase {

    @Autowired StudentRepository studentRepository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;

    @Before
    public void Setup() {
        Neo4jHelper.cleanDb(template);
    }

    @After
    public void tearDown() {
        Neo4jHelper.cleanDb(template);
    }

    @Test
    @Transactional
    public void testCreateStudent() throws Exception {
        String studentId = "abcde";
        studentRepository.save(new Student(studentId));
        assertEquals(1, studentRepository.count());
        Student student = studentRepository.findAll().single();
        assertEquals(studentId, student.studentId);
    }
}
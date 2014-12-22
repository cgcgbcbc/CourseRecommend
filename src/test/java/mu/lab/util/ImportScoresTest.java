package mu.lab.util;

import mu.lab.repo.StudentRepository;
import mu.lab.service.CourseService;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

@ContextConfiguration("file:src/main/webapp/WEB-INF/test-context.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ImportScoresTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;

    @Autowired
    ImportScores importScores;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseService courseService;

    @Before
    public void Setup() throws Exception {
        Neo4jHelper.cleanDb(template);
        courseService.createCourse("80805012", "交互艺术设计研究", (float) 0.0);
        courseService.createCourse("80801052", "信息技术前沿与设计应用", (float) 0.0);
        courseService.createCourse("80440252", "分析化学前沿导论", (float) 0.0);
        courseService.createCourse("60680021", "自然辩证法概论", (float) 0.0);

    }

    @After
    public void tearDown() {
        Neo4jHelper.cleanDb(template);
    }

    @Test
    public void testImportScore() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/test_output.csv")));
        importScores.importScore(bufferedReader);
        assertEquals(2, studentRepository.count());
    }
}
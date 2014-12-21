package mu.lab.service;

import mu.lab.model.Student;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by guangchen on 12/19/14 19:57.
 */
@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    StudentRepository studentRepository;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private Neo4jTemplate template;
    @Override
    public Student getRandomStudent(int seed) {
        long count = studentRepository.count();
        if (count == 0) return null;
        Student student = studentRepository.findAll().slice(0,1).getContent().get(0);
        template.fetch(student.scoreSet);
        return student;
    }

    @Override
    public Student createFakeStudent() {
        return studentRepository.save(new Student(""));
    }
}

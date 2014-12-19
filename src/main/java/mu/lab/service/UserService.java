package mu.lab.service;

import mu.lab.model.Student;
import mu.lab.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by guangchen on 12/19/14 19:57.
 */
@Service
public class UserService implements IUserService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student getRandomStudent(int seed) {
        long count = studentRepository.count();
        if (count == 0) return null;
        return studentRepository.findAll().single();
    }
}

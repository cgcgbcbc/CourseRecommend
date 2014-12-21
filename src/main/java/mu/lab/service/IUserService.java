package mu.lab.service;

import mu.lab.model.Student;

/**
 * Created by guangchen on 12/19/14 19:57.
 */
public interface IUserService {
    public Student getRandomStudent(int seed);
    public Student createFakeStudent();
}

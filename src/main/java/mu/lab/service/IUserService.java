package mu.lab.service;

import mu.lab.model.Student;

import java.util.Map;

/**
 * Created by guangchen on 12/19/14 19:57.
 */
public interface IUserService {
    /**
     * Note now it won't use seed, and no randomness.
     * @param seed the seed
     * @return a random student
     */
    public Student getRandomStudent(int seed);
    public Student createFakeStudent();

    public Map<Integer, Integer> getDistanceDistribution(int max_step, Long userId);

    public Map<Integer, Integer> getDistanceDistribution(Long userId);
}

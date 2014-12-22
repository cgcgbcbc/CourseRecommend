package mu.lab.service;

import mu.lab.model.Score;
import mu.lab.model.Student;

import java.util.List;
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

    public Map<Integer, Integer> getDistanceDistribution(List<Score> scores);

    public Map<Integer, Integer> getDistanceDistribution(List<Score> scores, int max_step);
}

package mu.lab.repo;

import mu.lab.model.Score;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by guangchen on 12/20/14 17:37.
 */
public interface ScoreRepository extends GraphRepository<Score> {
}

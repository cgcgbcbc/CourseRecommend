package mu.lab.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.Set;

/**
 * Created by guangchen on 12/18/14 16:03.
 */

@NodeEntity
public class Student {
    @GraphId Long id;
    public String studentId;

    @RelatedToVia(type = "SCORE")
    public Set<Score> scoreSet;

}

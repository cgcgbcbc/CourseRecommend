package mu.lab.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import javax.validation.constraints.NotNull;

/**
 * Created by guangchen on 12/18/14 16:44.
 */
@RelationshipEntity(type = "SCORE")
public class Score {
    @GraphId Long id;
    @StartNode Student student;
    @EndNode Course course;
    Integer score;
    public Score(@NotNull Student student,@NotNull Course course,@NotNull Integer score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }
}

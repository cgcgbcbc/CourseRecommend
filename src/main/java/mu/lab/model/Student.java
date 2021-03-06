package mu.lab.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by guangchen on 12/18/14 16:03.
 */

@NodeEntity
public class Student {
    @GraphId Long id;

    public Long getId() {
        return id;
    }

    @Indexed public String studentId;

    @RelatedToVia(type = "SCORE")
    public Set<Score> scoreSet;

    public Score takeExamsIn(Course course, Integer score) {
        Score s = new Score(this, course, score);
        this.scoreSet.add(s);
        return s;
    }

    public Student() {}

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Set<Course> getCourses() {
        Set<Course> courses = new HashSet<Course>();
        for (Score score : this.scoreSet) {
            courses.add(score.getCourse());
        }
        return courses;
    }

}

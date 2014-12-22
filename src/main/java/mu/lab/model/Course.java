package mu.lab.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.Set;

/**
 * Created by guangchen on 12/18/14 16:32.
 */
@NodeEntity
public class Course {

    @GraphId Long id;
    public Long getId() {return this.id;};
    @Indexed public String courseId;

    public String courseName;

    public Float standardDeviation;

    @RelatedTo(type = "LEARN_BY")
    public Set<Student> studentSet;

    public Course() {};

    public Course(String courseId, String courseName, Float standardDeviation) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.standardDeviation = standardDeviation;
    }

    public void addStudent(Student student) {
        this.studentSet.add(student);
    }
}

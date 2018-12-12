package io.leres.courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.leres.entities.Resource;
import io.leres.students.Student;
import io.leres.teachers.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"posts", "students", "teachers"})
@ToString(callSuper = true, exclude = {"posts", "students", "teachers"})
public class Course extends Resource {

    public String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", orphanRemoval = true)
    private Set<CoursePost> posts = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "course_teachers",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.getCourses().remove(this);
    }

    public boolean hasStudent(Student student) {
        return students.contains(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.getCourses().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        teacher.getCourses().remove(this);
    }

    public boolean hasTeacher(Teacher teacher) {
        return teachers.contains(teacher);
    }

    public void addPost(CoursePost coursePost) {
        posts.add(coursePost);
        coursePost.setCourse(this);
    }

    public void removePost(CoursePost coursePost) {
        posts.remove(coursePost);
        coursePost.setCourse(null);
    }
}

package io.leres.courses.services;

import io.leres.courses.Course;
import io.leres.courses.CoursePost;
import io.leres.courses.exceptions.AlreadyAssignedToCourse;
import io.leres.courses.exceptions.NotAssignedToCourse;
import io.leres.courses.repo.CoursePostRepository;
import io.leres.courses.repo.CourseRepository;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import io.leres.students.Student;
import io.leres.teachers.Teacher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CoursePostRepository coursePostRepository;

    CourseServiceImpl(CourseRepository courseRepository, CoursePostRepository coursePostRepository) {
        this.courseRepository = courseRepository;
        this.coursePostRepository = coursePostRepository;
    }

    @Override
    public Course createCourse(Course example) throws ResourceAlreadyExists {
        int count = courseRepository.countByName(example.getName());

        if (count > 0) {
            throw new ResourceAlreadyExists(Course.class, example.getName());
        }

        Course course = new Course();
        course.setName(example.getName());

        return courseRepository.save(course);
    }

    @Override
    public void removeCourse(long courseId) throws ResourceNotFound {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFound(Course.class, courseId);
        }

        courseRepository.deleteById(courseId);
    }

    @Override
    public CoursePost createCoursePost(Course course, CoursePost example) {
        CoursePost coursePost = new CoursePost(example.getContent());
        course.addPost(coursePost);

        return coursePostRepository.save(coursePost);
    }

    @Override
    public void removeCoursePost(CoursePost coursePost) {
        coursePost.getCourse().removePost(coursePost);
        coursePostRepository.delete(coursePost);
    }

    @Override
    public Course getCourseById(long courseId) throws ResourceNotFound {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFound(Course.class, courseId));
    }

    @Override
    public CoursePost getCoursePostById(long coursePostId) throws ResourceNotFound {
        Optional<CoursePost> coursePost = coursePostRepository.findById(coursePostId);

        if (!coursePost.isPresent()) {
            throw new ResourceNotFound(CoursePost.class, coursePostId);
        }

        return coursePost.get();
    }

    @Override
    public void assignStudentToCourse(Course course, Student student) throws AlreadyAssignedToCourse {
        if (course.hasStudent(student)) {
            throw new AlreadyAssignedToCourse(Student.class, student.getId());
        }

        course.addStudent(student);
        courseRepository.save(course);
    }

    @Override
    public void removeStudentFromCourse(Course course, Student student) throws NotAssignedToCourse {
        if (!course.hasStudent(student)) {
            throw new NotAssignedToCourse(Student.class, student.getId());
        }

        course.removeStudent(student);
        courseRepository.save(course);
    }

    @Override
    public void assignTeacherToCourse(Course course, Teacher teacher) throws AlreadyAssignedToCourse {
        if (course.hasTeacher(teacher)) {
            throw new AlreadyAssignedToCourse(Teacher.class, teacher.getId());
        }

        course.addTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void removeTeacherFromCourse(Course course, Teacher teacher) throws NotAssignedToCourse {
        if (!course.hasTeacher(teacher)) {
            throw new NotAssignedToCourse(Teacher.class, teacher.getId());
        }

        course.removeTeacher(teacher);
        courseRepository.save(course);
    }
}

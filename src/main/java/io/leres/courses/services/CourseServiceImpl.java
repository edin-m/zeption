package io.leres.courses.services;

import io.leres.courses.Course;
import io.leres.courses.CoursePost;
import io.leres.courses.exceptions.StudentAlreadyAssignedToCourse;
import io.leres.courses.exceptions.StudentNotAssignedToCourse;
import io.leres.courses.repo.CoursePostRepository;
import io.leres.courses.repo.CourseRepository;
import io.leres.students.Student;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

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
        coursePost.setCourse(course);

        return coursePostRepository.save(coursePost);
    }

    @Override
    public void removeCoursePost(long coursePostId) throws ResourceNotFound {
        if (coursePostRepository.existsById(coursePostId)) {
            throw new ResourceNotFound(CoursePost.class, coursePostId);
        }

        coursePostRepository.deleteById(coursePostId);
    }

    @Override
    public Course getCourseById(long courseId) throws ResourceNotFound {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFound(Course.class, courseId));
    }

    @Override
    public void assignStudentToCourse(Course course, Student student) throws StudentAlreadyAssignedToCourse {
        if (course.getStudents().contains(student)) {
            throw new StudentAlreadyAssignedToCourse(student.getId());
        }

        course.getStudents().add(student);
        courseRepository.save(course);
    }

    @Override
    public void removeStudentFromCourse(Course course, Student student) throws StudentNotAssignedToCourse {
        if (!course.getStudents().contains(student)) {
            throw new StudentNotAssignedToCourse(student.getId());
        }

        course.getStudents().remove(student);
        courseRepository.save(course);
    }
}

package io.leres.courses.services;

import io.leres.courses.Course;
import io.leres.courses.repo.CourseRepository;
import io.leres.exceptions.ResourceAlreadyExists;
import io.leres.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

@Service
class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
    public Course getById(long courseId) throws ResourceNotFound {
        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFound(Course.class, courseId));
    }
}

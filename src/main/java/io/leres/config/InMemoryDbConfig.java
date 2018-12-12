package io.leres.config;

import io.leres.courses.Course;
import io.leres.courses.repo.CourseRepository;
import io.leres.example.posts.repo.CommentRepository;
import io.leres.example.posts.repo.PostRepository;
import io.leres.example.posts.repo.TagRepository;
import io.leres.students.Student;
import io.leres.students.repo.StudentRepository;
import io.leres.teachers.Teacher;
import io.leres.teachers.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = {
        "io.leres.entities",
        "io.leres.example"
}, basePackageClasses = {
        Course.class,
        Student.class,
        Teacher.class
})
@EnableJpaRepositories(basePackageClasses = {
        StudentRepository.class,
        TeacherRepository.class,
        PostRepository.class,
        CommentRepository.class,
        TagRepository.class,
        CourseRepository.class
})
public class InMemoryDbConfig {

    private static final String InMemoryDb = "H2";

    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier(InMemoryDb) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "H2")
    public DataSource dataSourceH2() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .setName("wicked")
                .build();

        return db;
    }

}

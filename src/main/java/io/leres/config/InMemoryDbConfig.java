package io.leres.config;

import io.leres.classes.repo.UniClassRepository;
import io.leres.curriculums.repo.CurriculumEntryRepository;
import io.leres.example.posts.repo.CommentRepository;
import io.leres.example.posts.repo.PostRepository;
import io.leres.example.posts.repo.TagRepository;
import io.leres.exams.repo.ExamRepository;
import io.leres.exams.repo.ExamResultRepository;
import io.leres.students.repo.StudentRepository;
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
})
@EnableJpaRepositories(basePackageClasses = {
        StudentRepository.class,
        UniClassRepository.class,
        CurriculumEntryRepository.class,
        TeacherRepository.class,
        ExamRepository.class,
        ExamResultRepository.class,
        PostRepository.class,
        CommentRepository.class,
        TagRepository.class
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

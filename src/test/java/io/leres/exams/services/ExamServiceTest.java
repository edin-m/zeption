package io.leres.exams.services;

import io.leres.curriculums.CurriculumPoster;
import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Student;
import io.leres.exams.ExamFixtures;
import io.leres.exams.exceptions.ExamNotFound;
import io.leres.exams.repo.ExamRepository;
import io.leres.exams.repo.ExamResultRepository;
import io.leres.students.StudentFixture;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExamServiceTest {

    private ExamRepository examRepositoryMock;
    private ExamResultRepository examResultRepositoryMock;
    private CurriculumPoster curriculumPoster;

    private ExamService examService;

    private Exam exampleExam;

    @Before
    public void setUp() {
        examRepositoryMock = mock(ExamRepository.class);
        examResultRepositoryMock = mock(ExamResultRepository.class);
        curriculumPoster = mock(CurriculumPoster.class);

        examService = new ExamServiceImpl(examRepositoryMock, examResultRepositoryMock, curriculumPoster);

        exampleExam = ExamFixtures.getDefaultExam();
        setUpMocks();
    }

    private void setUpMocks() {
        when(examRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleExam));
    }

    @Test(expected = ExamNotFound.class)
    public void testGettingNonExistingExamByIdThrows() throws ExamNotFound {
        when(examRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        Exam exam = examService.getExamById(exampleExam.getId());

        assertThat(exam).isNotNull();
        verify(examRepositoryMock).findById(1L);
    }

    @Test
    @Ignore
    public void testGettingRecentExamsForStudent() {
        throw new NotImplementedException();
    }

    @Test
    public void testStoringExamResults() {
        Exam exam = ExamFixtures.getDefaultExam();
        Student student = StudentFixture.getDefaultStudent();

        ExamResult examResult = examService.storeExamResult(exam, student, 80);

        assertThat(examResult.getExam()).isEqualTo(exam);
        assertThat(examResult.getStudent()).isEqualTo(student);
        verify(examResultRepositoryMock).save(any(ExamResult.class));
    }
}

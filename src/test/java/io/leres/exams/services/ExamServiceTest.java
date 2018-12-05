package io.leres.exams.services;

import io.leres.classes.ClassFinder;
import io.leres.curriculums.CurriculumPoster;
import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Student;
import io.leres.exams.ExamFixtures;
import io.leres.exams.exceptions.ExamNotFound;
import io.leres.exams.exceptions.ExamResultNotFound;
import io.leres.exams.repo.ExamRepository;
import io.leres.exams.repo.ExamResultRepository;
import io.leres.students.StudentFixture;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExamServiceTest {

    private ExamRepository examRepositoryMock;
    private ExamResultRepository examResultRepositoryMock;
    private CurriculumPoster curriculumPoster;
    private ClassFinder classFinder;

    private ExamService examService;

    private Exam exampleExam;

    @Before
    public void setUp() {
        examRepositoryMock = mock(ExamRepository.class);
        examResultRepositoryMock = mock(ExamResultRepository.class);
        curriculumPoster = mock(CurriculumPoster.class);
        classFinder = mock(ClassFinder.class);

        examService = new ExamServiceImpl(examRepositoryMock, examResultRepositoryMock, curriculumPoster, classFinder);

        exampleExam = ExamFixtures.getDefaultExam();
        setUpMocks();
    }

    private void setUpMocks() {
        when(examRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(exampleExam));

        when(examResultRepositoryMock.save(
                any(ExamResult.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test(expected = ExamNotFound.class)
    public void testGettingNonExistingExamThrows() throws ExamNotFound {
        when(examRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        examService.getExamById(exampleExam.getId());
    }

    @Test
    public void testGettingExamById() throws ExamNotFound {
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

    @Test(expected = ExamResultNotFound.class)
    public void testGettingNonExistingExamResultThrows() throws ExamResultNotFound {
        when(examResultRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.empty());

        examService.getExamResultById(1L);
    }

    @Test
    public void testGettingExamResultById() throws ExamResultNotFound {
        when(examResultRepositoryMock.findById(
                anyLong()
        )).thenReturn(Optional.of(ExamFixtures.getDefaultExamResult()));

        ExamResult examResult = examService.getExamResultById(1L);

        assertThat(examResult).isNotNull();
        verify(examResultRepositoryMock).findById(1L);
    }
}

package io.leres.exams.services;

import io.leres.UnitTests;
import org.junit.experimental.categories.Category;

@Category(UnitTests.class)
public class ExamServiceTest {
//
//    private ExamRepository examRepositoryMock;
//    private ExamResultRepository examResultRepositoryMock;
//    private CurriculumPoster curriculumPoster;
//    private ClassFinder classFinder;
//
//    private ExamService examService;
//
//    private Exam exampleExam;
//
//    @Before
//    public void setUp() {
//        examRepositoryMock = mock(ExamRepository.class);
//        examResultRepositoryMock = mock(ExamResultRepository.class);
//        curriculumPoster = mock(CurriculumPoster.class);
//        classFinder = mock(ClassFinder.class);
//
//        examService = new ExamServiceImpl(examRepositoryMock, examResultRepositoryMock, curriculumPoster, classFinder);
//
//        exampleExam = ExamFixtures.getDefaultExam();
//        setUpMocks();
//    }
//
//    private void setUpMocks() {
//        when(examRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.of(exampleExam));
//
//        when(examResultRepositoryMock.save(
//                any(ExamResult.class)
//        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
//
//        doNothing().when(curriculumPoster).addTextMessageToCurriculum(
//                any(Teacher.class), any(UniClass.class), anyInt(), anyString()
//        );
//    }
//
//    @Test(expected = ExamNotFound.class)
//    public void testGettingNonExistingExamThrows() throws ExamNotFound {
//        when(examRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.empty());
//
//        examService.getExamById(exampleExam.getId());
//    }
//
//    @Test
//    public void testGettingExamById() throws ExamNotFound {
//        Exam exam = examService.getExamById(exampleExam.getId());
//
//        assertThat(exam).isNotNull();
//        verify(examRepositoryMock).findById(1L);
//    }
//
//    @Test
//    @Ignore
//    public void testGettingRecentExamsForStudent() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Test
//    public void testStoringExamResults() {
//        Exam exam = ExamFixtures.getDefaultExam();
//        Student student = StudentFixture.getDefaultStudent();
//
//        ExamResult examResult = examService.storeExamResult(exam, student, 80);
//
//        assertThat(examResult.getExam()).isEqualTo(exam);
//        assertThat(examResult.getStudent()).isEqualTo(student);
//        verify(examResultRepositoryMock).save(any(ExamResult.class));
//    }
//
//    @Test(expected = ExamResultNotFound.class)
//    public void testGettingNonExistingExamResultThrows() throws ExamResultNotFound {
//        when(examResultRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.empty());
//
//        examService.getExamResultById(1L);
//    }
//
//    @Test
//    public void testGettingExamResultById() throws ExamResultNotFound {
//        when(examResultRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.of(ExamFixtures.getDefaultExamResult()));
//
//        ExamResult examResult = examService.getExamResultById(1L);
//
//        assertThat(examResult).isNotNull();
//        verify(examResultRepositoryMock).findById(1L);
//    }
//
//    @Test
//    public void testSchedulingExam() {
//        Teacher teacher = TeacherFixture.getDefaultProfessor();
//        Instant instant = Instant.now();
//        UniClass uniClass = UniClassFixture.getDefaultClass();
//
//        examService.scheduleExam(teacher, instant, uniClass);
//
//        verify(examRepositoryMock).save(any(Exam.class));
//
//    }
}

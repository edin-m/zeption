package io.leres.curriculums.services;

import io.leres.UnitTests;
import io.leres.classes.UniClassCuder;
import io.leres.curriculums.repo.CurriculumEntryRepository;
import org.junit.experimental.categories.Category;

@Category(UnitTests.class)
public class CurriculumServiceTest {

    private CurriculumEntryRepository curriculumEntryRepositoryMock;
    private UniClassCuder classCruderMock;

    private CurriculumService curriculumService;

//    @Before
//    public void setUp() throws ResourceAlreadyExists {
//        curriculumEntryRepositoryMock = mock(CurriculumEntryRepository.class);
//        classCruderMock = mock(UniClassCuder.class);
//
//        curriculumService = new CurriculumServiceImpl(curriculumEntryRepositoryMock);
//
//        setUpMocks();
//    }
//
//    private void setUpMocks() throws ResourceAlreadyExists {
//        doNothing().when(classCruderMock).createUniClass(any(UniClass.class));
//
//        when(curriculumEntryRepositoryMock.save(
//                any(CurriculumEntry.class)
//        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
//    }
//
//    @Test
//    public void testAddingTextMessageToCurriculum() throws ResourceAlreadyExists {
////        Teacher teacher = TeacherFixture.getDefaultProfessor();
////        UniClass uniClass = UniClassFixture.getDefaultClass();
//
////        curriculumService.addTextMessageToCurriculum(teacher, uniClass, 1, "Intro Lecture");
//
////        verify(classCruderMock).createUniClass(any(UniClass.class));
////        verify(curriculumEntryRepositoryMock).save(any(CurriculumEntry.class));
//    }

}

package io.leres.curriculums.services;

import io.leres.UnitTests;
import io.leres.classes.ClassCruder;
import io.leres.classes.UniClassFixture;
import io.leres.curriculums.repo.CurriculumEntryRepository;
import io.leres.entities.CurriculumEntry;
import io.leres.entities.Teacher;
import io.leres.entities.UniClass;
import io.leres.teachers.TeacherFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.AdditionalAnswers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Category(UnitTests.class)
public class CurriculumServiceTest {

    private CurriculumEntryRepository curriculumEntryRepositoryMock;
    private ClassCruder classCruderMock;

    private CurriculumService curriculumService;

    @Before
    public void setUp() {
        curriculumEntryRepositoryMock = mock(CurriculumEntryRepository.class);
        classCruderMock = mock(ClassCruder.class);

        curriculumService = new CurriculumServiceImpl(curriculumEntryRepositoryMock, classCruderMock);

        setUpMocks();
    }

    private void setUpMocks() {
        doNothing().when(classCruderMock).saveExisting(any(UniClass.class));

        when(curriculumEntryRepositoryMock.save(
                any(CurriculumEntry.class)
        )).thenAnswer(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void testAddingTextMessageToCurriculum() {
        Teacher teacher = TeacherFixture.getDefaultProfessor();
        UniClass uniClass = UniClassFixture.getDefaultClass();

        curriculumService.addTextMessageToCurriculum(teacher, uniClass, 1, "Intro Lecture");

        verify(classCruderMock).saveExisting(any(UniClass.class));
        verify(curriculumEntryRepositoryMock).save(any(CurriculumEntry.class));
    }

}

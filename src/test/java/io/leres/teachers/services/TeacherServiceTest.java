package io.leres.teachers.services;

import io.leres.UnitTests;
import io.leres.teachers.Teacher;
import io.leres.teachers.repo.TeacherRepository;
import org.junit.experimental.categories.Category;

@Category(UnitTests.class)
public class TeacherServiceTest {

    private TeacherRepository teacherRepositoryMock;

    private TeacherService teacherService;

    private Teacher exampleTeacher;

//    @Before
//    public void setUp() {
//        teacherRepositoryMock = mock(TeacherRepository.class);
//
//        teacherService = new TeacherServiceImpl(teacherRepositoryMock);
//
////        exampleTeacher = TeacherFixture.getDefaultProfessor();
//    }
//
//    @Test(expected = TeacherNotFound.class)
//    public void testGettingNonExistingTeacher() throws TeacherNotFound {
//        when(teacherRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.empty());
//
//        teacherService.getTeacherById(1L);
//    }
//
//    @Test
//    public void testGettingTeacherById() throws TeacherNotFound {
//        when(teacherRepositoryMock.findById(
//                anyLong()
//        )).thenReturn(Optional.of(exampleTeacher));
//
//        Teacher teacher = teacherService.getTeacherById(1L);
//
//        assertThat(teacher).isEqualTo(exampleTeacher);
//        verify(teacherRepositoryMock).findById(1L);
//    }

}

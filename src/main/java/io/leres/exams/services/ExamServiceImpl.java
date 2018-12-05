package io.leres.exams.services;

import io.leres.curriculums.CurriculumPoster;
import io.leres.entities.Exam;
import io.leres.entities.ExamResult;
import io.leres.entities.Student;
import io.leres.entities.Teacher;
import io.leres.exams.exceptions.ExamNotFound;
import io.leres.exams.repo.ExamRepository;
import io.leres.exams.repo.ExamResultRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;
    private ExamResultRepository examResultRepository;
    private CurriculumPoster curriculumPoster;

    public ExamServiceImpl(ExamRepository examRepository, ExamResultRepository examResultRepository, CurriculumPoster curriculumPoster) {
        this.examRepository = examRepository;
        this.examResultRepository = examResultRepository;
        this.curriculumPoster = curriculumPoster;
    }

    @Override
    public Exam getExamById(long examId) throws ExamNotFound {
        Optional<Exam> exam = examRepository.findById(examId);

        if (!exam.isPresent()) {
            throw new ExamNotFound(examId);
        }

        return exam.get();
    }

    @Override
    public List<Exam> getRecentExamsForStudent(Student student) {
        throw new NotImplementedException();
    }

    @Override
    public ExamResult storeExamResult(Exam exam, Student student, long grade) {
        
    }

    @Override
    public ExamResult getExamResult(long examResultId) {
        return null;
    }

    @Override
    public List<ExamResult> getExamResultsByExam(Exam exam) {
        return null;
    }

    @Override
    public void scheduleExam(Teacher signOffTeacher, Instant timeAt, Set<Teacher> attendingTeachers) {
        // create exam
        // create curriculum entry
    }
}

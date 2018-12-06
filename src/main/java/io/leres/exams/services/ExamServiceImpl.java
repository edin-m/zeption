package io.leres.exams.services;

import io.leres.classes.ClassFinder;
import io.leres.curriculums.CurriculumPoster;
import io.leres.entities.*;
import io.leres.exams.exceptions.ExamNotFound;
import io.leres.exams.exceptions.ExamResultNotFound;
import io.leres.exams.repo.ExamRepository;
import io.leres.exams.repo.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;
    private ExamResultRepository examResultRepository;
    private CurriculumPoster curriculumPoster;
    private ClassFinder classFinder;

    public ExamServiceImpl(ExamRepository examRepository, ExamResultRepository examResultRepository, CurriculumPoster curriculumPoster, ClassFinder classFinder) {
        this.examRepository = examRepository;
        this.examResultRepository = examResultRepository;
        this.curriculumPoster = curriculumPoster;
        this.classFinder = classFinder;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public ExamResult storeExamResult(Exam exam, Student student, int grade) {
        ExamResult examResult = new ExamResult(exam, student, grade);
        return examResultRepository.save(examResult);
    }

    @Override
    public ExamResult getExamResultById(long examResultId) throws ExamResultNotFound {
        Optional<ExamResult> examResult = examResultRepository.findById(examResultId);

        if (!examResult.isPresent()) {
            throw new ExamResultNotFound(examResultId);
        }

        return examResult.get();
    }

    @Override
    public Exam scheduleExam(Teacher signOffTeacher, Instant timeAt, UniClass uniClass) {
        Exam exam = new Exam(signOffTeacher, timeAt);

        exam = examRepository.save(exam);

        int weekOfClass = classFinder.findWeekOfClass(uniClass, timeAt);
        String description = String.format("Exam scheduled for week %s", weekOfClass);
        curriculumPoster.addTextMessageToCurriculum(signOffTeacher, uniClass, weekOfClass, description);

        return exam;
    }
}

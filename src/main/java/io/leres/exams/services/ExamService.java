package io.leres.exams.services;

import io.leres.exams.ExamFinder;
import io.leres.exams.ExamResultCreator;
import io.leres.exams.ExamResultFinder;
import io.leres.exams.ExamScheduler;

interface ExamService extends ExamFinder, ExamResultCreator, ExamResultFinder, ExamScheduler {
}

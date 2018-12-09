package io.leres.exams.services;

import io.leres.exams.ExamFinder;
import io.leres.exams.ExamResultCruder;
import io.leres.exams.ExamScheduler;

interface ExamService extends ExamFinder, ExamResultCruder, ExamScheduler {
}

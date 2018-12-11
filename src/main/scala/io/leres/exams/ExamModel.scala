package io.leres.exams

import java.time.Instant

import io.leres.entities.UniClass
import io.leres.teachers.Teacher

case class ExamModel(signOffTeacher: Teacher, timeAt: Instant, uniClass: UniClass)

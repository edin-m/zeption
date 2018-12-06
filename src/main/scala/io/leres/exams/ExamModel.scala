package io.leres.exams

import java.time.Instant

import io.leres.entities.{Teacher, UniClass}

case class ExamModel(signOffTeacher: Teacher, timeAt: Instant, uniClass: UniClass)

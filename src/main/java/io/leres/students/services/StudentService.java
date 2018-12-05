package io.leres.students.services;

import io.leres.students.StudentCreator;
import io.leres.students.StudentRetriever;
import io.leres.students.StudentUpdater;

interface StudentService extends StudentCreator, StudentRetriever, StudentUpdater {
}

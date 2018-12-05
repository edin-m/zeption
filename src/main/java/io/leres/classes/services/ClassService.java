package io.leres.classes.services;

import io.leres.classes.ClassFinder;
import io.leres.classes.EnrollementService;
import io.leres.classes.ClassManager;

interface ClassService extends EnrollementService, ClassFinder, ClassManager {
}

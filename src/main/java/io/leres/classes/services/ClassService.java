package io.leres.classes.services;

import io.leres.classes.ClassFinder;
import io.leres.classes.ClassScheduler;
import io.leres.classes.EnrollementService;
import io.leres.classes.UniClassCuder;

interface ClassService extends EnrollementService, ClassFinder, UniClassCuder, ClassScheduler {
}

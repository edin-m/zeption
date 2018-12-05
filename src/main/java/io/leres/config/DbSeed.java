package io.leres.config;

import io.leres.classes.ClassManager;
import io.leres.classes.repo.UniClassRepository;
import io.leres.entities.PersonData;
import io.leres.entities.Student;
import io.leres.entities.UniClass;
import io.leres.students.StudentCreator;
import io.leres.students.exceptions.StudentAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Component
public class DbSeed {

    @Autowired
    private StudentCreator studentCreator;

    @Autowired
    private ClassManager classManager;

    @Autowired
    private UniClassRepository uniClassRepository;

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @PostConstruct
    public void runPostConstruct() {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    setUpDb();
                } catch (StudentAlreadyExists e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setUpDb() throws StudentAlreadyExists {
        Student studentA = createStudentA();
        Student studentB = createStudentB();
        Student studentC = createStudentC();

        studentCreator.createStudent(studentA);
        studentCreator.createStudent(studentB);
        studentCreator.createStudent(studentC);

        UniClass cs101 = classManager.createUniClass("CS-101", "Intro to Computer Science");
        UniClass ca101 = classManager.createUniClass("CA-101", "Intro to Computer Architecture");

        uniClassRepository.save(cs101);
        uniClassRepository.save(ca101);

        System.out.println(uniClassRepository.findAll());
    }

    private Student createStudentA() {
        Student student = new Student();

        student.setPersonData(new PersonData("Kenny", "McCormick", "111-111-111"));
        student.setLastUpdatedToNow();

        return student;
    }

    private Student createStudentB() {
        Student student = new Student();

        student.setPersonData(new PersonData("Stan", "Marshall", "222-222-222"));
        student.setLastUpdatedToNow();

        return student;
    }

    private Student createStudentC() {
        Student student = new Student();

        student.setPersonData(new PersonData("Eric", "Cartman", "333-333-333"));
        student.setLastUpdatedToNow();

        return student;
    }

}

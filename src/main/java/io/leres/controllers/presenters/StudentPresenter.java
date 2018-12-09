package io.leres.controllers.presenters;

import io.leres.controllers.Presenter;
import io.leres.controllers.view_models.StudentViewModel;
import io.leres.entities.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentPresenter implements Presenter<StudentViewModel, Student> {

    @Override
    public StudentViewModel convert(Student student) {
        return new StudentViewModel(student.getFullName(), "", student.getPersonData().getSocialId());
    }

}

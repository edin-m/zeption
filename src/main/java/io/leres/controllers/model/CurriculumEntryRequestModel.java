package io.leres.controllers.model;

import lombok.Data;

@Data
public class CurriculumEntryRequestModel {
    long teacherId;
    long uniClassId;
    int weekOfCurriculum = 0;
    String description;
}

package io.leres.controllers.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ExamRequestModel {
    private long teacherId;
    private long uniClassId;
    private Instant timeAt;
}

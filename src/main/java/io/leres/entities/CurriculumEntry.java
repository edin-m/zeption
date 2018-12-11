package io.leres.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CurriculumEntry extends Resource {

//    @JsonIgnore
//    @ManyToOne
//    private UniClass uniClass;
//
//    @JsonIgnore
//    @ManyToOne
//    private Teacher author;

    private int weekOfCurriculum;

    private String description;

    private String attachmentPath;

    public boolean hasAttachment() {
        return attachmentPath == null || attachmentPath.length() == 0;
    }

}

package io.leres.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CurriculumEntry extends Resource {

    @ManyToOne
    private UniClass uniClass;

    @ManyToOne
    private Teacher author;

    private int weekOfCurriculum;

    private String description;

    private String attachmentPath;

    public boolean hasAttachment() {
        return attachmentPath == null || attachmentPath.length() == 0;
    }

}

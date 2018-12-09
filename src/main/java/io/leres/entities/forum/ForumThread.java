package io.leres.entities.forum;

import io.leres.entities.Resource;
import io.leres.entities.UniClass;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForumThread extends Resource {

    @ManyToOne(fetch = FetchType.LAZY)
    private UniClass uniClass;

    private Instant createdAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ForumEntry> entries;

}

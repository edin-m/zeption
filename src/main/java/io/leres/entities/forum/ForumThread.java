package io.leres.entities.forum;

import io.leres.entities.Resource;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ForumThread extends Resource {

//    @ManyToOne(fetch = FetchType.LAZY)
//    private UniClass uniClass;
//
//    private Instant createdAt;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<ForumEntry> entries;

}

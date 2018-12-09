package io.leres.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "lastUpdated" })
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Instant lastUpdated = Instant.now();

    public void setLastUpdatedToNow() {
        lastUpdated = Instant.now();
    }

    @Version
    private Long version;

}

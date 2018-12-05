package io.leres.entities;

import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ResourceTest {

    @Test
    public void testResourceEquals() {
        Resource resourceA = new Resource();
        resourceA.setId(1L);
        resourceA.setLastUpdated(Instant.now());

        Resource resourceB = new Resource();
        resourceB.setId(1L);
        resourceB.setLastUpdated(Instant.now().plusSeconds(5));

        assertThat(resourceA).isEqualTo(resourceB);
    }
}

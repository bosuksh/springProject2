package kr.co.springExample2.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    public void creation() {
        Region region = Region.builder().name("Seoul").build();

        assertThat(region.getName(),is("Seoul"));
    }

}
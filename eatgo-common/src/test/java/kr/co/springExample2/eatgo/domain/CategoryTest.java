package kr.co.springExample2.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void create() {
        Category category = Category.builder().name("Korean Food").build();

        assertThat(category.getName(),is("Korean Food"));
    }

}
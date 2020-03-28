package com.rodrigovsilva.memorygame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(properties = "application")
public class MemoryGameApplicationTests {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void contextLoads() {
    }

}


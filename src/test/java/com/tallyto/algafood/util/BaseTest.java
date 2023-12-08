package com.tallyto.algafood.util;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseTest {
    @Autowired
    Flyway flyway;

    @BeforeEach
    void migrate() {
        flyway.migrate();
    }

    @AfterEach
    void clean() {
        flyway.clean();
    }
}

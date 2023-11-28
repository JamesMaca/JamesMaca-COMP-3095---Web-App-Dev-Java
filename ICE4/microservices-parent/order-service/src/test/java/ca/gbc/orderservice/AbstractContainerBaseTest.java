package ca.gbc.orderservice;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.function.Supplier;

public class AbstractContainerBaseTest {

    static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest").withDatabaseName("testdb").withUsername("admin").withPassword("password");

    @BeforeAll
    public static void initialize(){
        POSTGRE_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.data.postgres.url", POSTGRE_SQL_CONTAINER::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", () -> "admin");
        dynamicPropertyRegistry.add("spring.datasource.password", () -> "password");
    }
}

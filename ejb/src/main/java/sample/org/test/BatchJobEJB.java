package sample.org.test;


import jakarta.annotation.Resource;
import jakarta.batch.runtime.context.JobContext;
import jakarta.inject.Inject;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;

@Stateless
public class BatchJobEJB {

    @Resource(lookup = "java:jboss/datasources/PostgresDS")
    private DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private JobContext jobContext;

    public String process(String username) throws Exception {
        // Insert log data
        Login_Info entity = new Login_Info();
        entity.setUsername(username);

        entity.setTimestamp(Timestamp.from(Instant.now()));
        entityManager.persist(entity);
        return "Logging in user: " + username + " at " + Timestamp.from(Instant.now());
    }
}
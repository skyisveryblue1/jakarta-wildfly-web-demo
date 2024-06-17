package sample.org.test;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "login_info")
public class Login_Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp timestamp;

    // getters and setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


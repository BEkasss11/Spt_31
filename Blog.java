package Classes;

import java.sql.Timestamp;

public class Blog {

    private Long id;
    private Users users;
    private String title;
    private String content;
    private Timestamp timestamp;


    public Blog(Long id, Users users, String title,String content, Timestamp timestamp) {
        this.id = id;
        this.users = users;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Blog() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

package Classes;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private String comment;
    private Users users;
    private Timestamp post_date;
    private Blog blog;

    public Comment(Long id, String comment, Users users, Timestamp post_date, Blog blog) {
        this.id = id;
        this.comment = comment;
        this.users = users;
        this.post_date = post_date;
        this.blog = blog;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

}

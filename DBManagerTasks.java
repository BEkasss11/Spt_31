package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerTasks {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ADMIN");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private  static ArrayList<Tasks> tasks = new ArrayList<>();
    private  static  long id = 4L;
    static {
        tasks.add(new Tasks(1L,"Bekarys","important","2021-02-05",false));
        tasks.add(new Tasks(2L,"Arnur"," not important","2020-05-06",false));
        tasks.add(new Tasks(3L,"Yelzhas","important","2020-04-10",false));
    }

   public static void addTask(Tasks task){

       task.setId(id);
       task.setStatus(false);
       id++;
       tasks.add(task);
   }

    public  static  Tasks getTask(Long id){
       Tasks task = new Tasks();
        for (int i =0;i<tasks.size();i++){
            if(id == tasks.get(i).getId()){
                return  tasks.get(i);
            }
        }
        return  task;
    } // этот метод возвращает объект задачи по id

    public static   ArrayList getAllTasks(){
        return  tasks;
    } //этот метод возвращает список всех задач

    public  void deleteTask(Long id){
        for(Tasks task: tasks){
            if ( id == task.getId()){
                tasks.remove(task);
            }
        }
    }

    public  static  Users getUser(String email){
        Users users = null;
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT  * FROM  users WHERE email = ?");
            stmt.setString(1,email);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                users= new Users();
                users.setEmail(resultSet.getString("email"));
                users.setPassword(resultSet.getString("password"));
                users.setFullName(resultSet.getString("fullname"));
                users.setId(resultSet.getLong("id"));
            }

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
        return  users;
    }

    public static boolean addUser(Users users){
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO postgres.public.users(email, password, fullname) "+
                    "Values (?, ?, ?)");
            stmt.setString(1, users.getEmail());
            stmt.setString(2, users.getPassword());
            stmt.setString(3, users.getFullName());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static  boolean addBlog(Blog blog){
        int row =  0;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT  into blogs (user_id, title, content, post_date) values (?,?,?,Now());");
            stmt.setLong(1,blog.getUsers().getId());
            stmt.setString(2,blog.getTitle());
            stmt.setString(3,blog.getContent());
            row = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  row>0;
    }

    public static List<Blog> getBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("select b.id, b.content, b.title, b.post_date, u.id, u.fullname, u.email " +
                    "from blogs as b " +
                    "INNER JOIN postgres.public.users as u on u.id = b.user_id");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Blog blog = new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setContent(resultSet.getString("content"));
                blog.setTitle(resultSet.getString("title"));
                blog.setTimestamp(resultSet.getTimestamp("post_date"));
                Users users = new Users();
                users.setId(resultSet.getLong("id"));
                users.setFullName(resultSet.getString("fullname"));
                users.setEmail(resultSet.getString("email"));
                blog.setUsers(users);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

     public  static  Blog getBlogsbyID(long id){
        Blog blog = new Blog();
         try {
             PreparedStatement stmt = connection.prepareStatement("select b.id,b.title,b.content,b.post_date,u.id,u.email,u.fullname\n" +
                     "from  blogs as b\n" +
                     "inner join public.users u on u.id = b.user_id\n" +
                     "where b.id = ?");
             stmt.setLong(1,id);
             ResultSet resultSet = stmt.executeQuery();
             if(resultSet.next()){
                 blog.setId(resultSet.getLong("id"));
                 blog.setTitle(resultSet.getString("title"));
                 blog.setContent(resultSet.getString("content"));
                 blog.setTimestamp(resultSet.getTimestamp("post_date"));
                 Users users = new Users();
                 users.setId(resultSet.getLong("id"));
                 users.setEmail(resultSet.getString("email"));
                 users.setFullName(resultSet.getString("fullname"));
                 blog.setUsers(users);
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }

         return  blog;
     }

     public  static  boolean addComment(Comment comment){
        int rows = 0;
         try {
             PreparedStatement stmt = connection.prepareStatement("INSERT  into  comments(comment, post_date, user_id, blog_id) VALUES (?,Now(),?,?);");
             stmt.setString(1, comment.getComment());
             stmt.setLong(2,comment.getUsers().getId());
             stmt.setLong(3,comment.getBlog().getId());
             rows = stmt.executeUpdate();
             stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return  rows > 0;
     }

}

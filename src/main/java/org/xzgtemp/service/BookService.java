package org.xzgtemp.service;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.xzgtemp.entity.Book;

@Component
public class BookService {


	@Autowired
	JdbcTemplate jdbctemplate;

	RowMapper<Book> userRowMapper = new BeanPropertyRowMapper<>(Book.class);


    //----------AddBook------------------------------------------
    public void AddBook(Book book){
        //String title, String author, Date publishtime,String publisher,String buyer, Date buytime,
        //String whereis, Boolean isonshelf,Integer borrowtimes, Boolean orderd, String orderuser
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Book(b_id,b_title,b_author,b_buyer,b_buytime,b_whereis,b_isonshelf,b_borrowtimes,b_publishtime,b_publisher) VALUES(?,?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,holder.getKey().longValue());
                    ps.setObject(2,book.GetTitle());
                    ps.setObject(3,book.GetAuthor());
                    ps.setObject(4,book.GetPublishtime());
                    ps.setObject(5,book.GetPublisher());
                    ps.setObject(6,book.Getbuyer());
                    ps.setObject(7,book.Getbuytime());
                    ps.setObject(8,book.GetWhereis());
                    ps.setObject(9,book.GetIsonshelf());
                    ps.setObject(10,book.GetBorrowtimes());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
    }

    //---------GetBookByAttribute------------------------------
    public Book GetBookbyBID(int bid){
        String sql = "SELECT * FROM Book WHERE b_id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes")
                    );
                }
                ,bid
        );
    }

    public List<Book> GetBookbyTitleOrAuthor(String s){
        String sql = "SELECT * FROM Book WHERE b_title LIKE %?% OR b_author LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes")
                    );
                },s,s);
    }

    public List<Book> GetBookbyTitle(String Title){
        String sql = "SELECT * FROM Book WHERE b_title LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes")
                    );
                },Title);
    }

    public List<Book> GetBookbyAuthor(String Author){
        String sql = "SELECT * FROM Book WHERE b_author LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes")
                    );
                },Author);
    }

    public List<Book> GetALlBooks(){
        return jdbctemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    //----------ChangBookAttribute------------------------------
    //need to pass arguments
    public void ChangeBookTitle(Book book){
        String sql = "UPDATA Book SET b_title = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookAuthor(Book book){
        String sql = "UPDATA Book SET b_author = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuyer(Book book){
        String sql = "UPDATA Book SET b_buyer = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuytime(Book book){
        String sql = "UPDATA Book SET b_buytime = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookWhereis(Book book){
        String sql = "UPDATA Book SET b_whereis = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookIsonshelf(Book book){
        String sql = "UPDATA Book SET b_isonshelf = ? WHERE b_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Book no found by id");
        }
    }
}

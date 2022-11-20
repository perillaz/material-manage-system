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
        String sql = "INSERT INTO Book(b_title,b_author,b_buyer,b_buytime,b_whereis,b_isonshelf,b_borrowtimes,b_publishtime,b_publisher) VALUES(?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,book.GetTitle());
                    ps.setObject(2,book.GetAuthor());
                    ps.setObject(3,book.Getbuyer());
                    ps.setObject(4,book.Getbuytime());
                    ps.setObject(5,book.GetWhereis());
                    ps.setObject(6,book.GetIsonshelf());
                    ps.setObject(7,book.GetBorrowtimes());
                    ps.setObject(8,book.GetPublishtime());
                    ps.setObject(9,book.GetPublisher());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        book.SetID(holder.getKey().longValue());
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
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher")
                    );
                }
                ,bid
        );
    }

    public List<Book> GetBookbyTitleOrAuthor(String s){
        return jdbctemplate.query("SELECT * FROM Book WHERE b_title LIKE %?% OR b_author LIKE %?%",userRowMapper,s,s);
    }

/* 
    public List<Book> GetBookbyTitleOrAuthor(String s){
        String sql = "SELECT * FROM Book WHERE b_title LIKE %?% OR b_author LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher")
                    );
                },s,s);
    }
*/

    public List<Book> GetBookbyTitle(String Title){
        String sql = "SELECT * FROM Book WHERE b_title LIKE %?%";
        return jdbctemplate.query(sql,userRowMapper,Title);
    }
/* 
    public List<Book> GetBookbyTitle(String Title){
        String sql = "SELECT * FROM Book WHERE b_title LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher")
                    );
                },Title);
    }
*/
    public List<Book> GetBookbyAuthor(String Author){
        String sql = "SELECT * FROM Book WHERE b_author LIKE %?%";
        return jdbctemplate.query(sql,userRowMapper,Author);
    }
/*
    public List<Book> GetBookbyAuthor(String Author){
        String sql = "SELECT * FROM Book WHERE b_author LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                            rs.getLong("b_id"),
                            rs.getString("b_title"),
                            rs.getString("b_author"),
                            rs.getString("b_buyer"),
                            rs.getDate("b_buytime"),
                            rs.getString("b_whereis"),
                            rs.getBoolean("b_isonshelf"),
                            rs.getInt("borrowtimes"),
                            rs.getDate("b_publishtime"),
                            rs.getString("b_publisher")
                    );
                },Author);
    }
 */
    public List<Book> GetALlBooks(){
        return jdbctemplate.query("SELECT * FROM Book",userRowMapper);
    }

    //----------ChangBookAttribute------------------------------
    //need to pass arguments
    public void ChangeBookTitle(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_title = ? WHERE b_id = ? ",
            book.GetTitle(),
            book.GetID()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookAuthor(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_author = ? WHERE b_id = ? ",
            book.GetAuthor(),
            book.GetID()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuyer(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_buyer = ? WHERE b_id = ? ",
            book.Getbuyer(),
            book.GetID()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuytime(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_buytime = ? WHERE b_id = ? ",
            book.Getbuytime(),
            book.GetID()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookWhereis(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_whereis = ? WHERE b_id = ? ",
            book.GetWhereis(),
            book.GetID()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookIsonshelf(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET b_isonshelf = ? WHERE b_id = ? ",
            book.GetIsonshelf(),
            book.GetID()
        )){
            throw new RuntimeException("Book no found by id");
        }
    }
}

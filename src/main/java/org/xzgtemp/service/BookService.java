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

	RowMapper<Book> bookRowMapper = new BeanPropertyRowMapper<>(Book.class);


    //----------AddBook------------------------------------------
    public void AddBook(Book book){
        //String title, String author, Date publishtime,String publisher,String buyer, Date buytime,
        //String whereis, Boolean isonshelf,Integer borrowtimes, Boolean orderd, String orderuser
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Book(title,author,buyer,buytime,whereis,isonshelf,borrowtimes,publishtime,publisher) VALUES(?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,book.getTitle());
                    ps.setObject(2,book.getAuthor());
                    ps.setObject(3,book.getBuyer());
                    ps.setObject(4,book.getBuytime());
                    ps.setObject(5,book.getWhereis());
                    ps.setObject(6,book.getIsonshelf());
                    ps.setObject(7,book.getBorrowtimes());
                    ps.setObject(8,book.getPublishtime());
                    ps.setObject(9,book.getPublisher());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        book.setId(holder.getKey().longValue());
    }

    //---------GetBookByAttribute------------------------------
    public Book GetBookbyBID(Long bid){
        String sql = "SELECT * FROM Book WHERE id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("buyer"),
                        rs.getDate("buytime"),
                        rs.getString("whereis"),
                        rs.getBoolean("isonshelf"),
                        rs.getInt("borrowtimes"),
                        rs.getDate("publishtime"),
                        rs.getString("publisher")
                    );
                }
                ,bid
        );
    }

    public List<Book> GetBooksbyTitleOrAuthor(String s){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE title LIKE CONCAT(\'%\',?,\'%\') OR author LIKE CONCAT(\'%\',?,\'%\')",
            bookRowMapper,
            s,
            s
        );
    }

    public List<Book> GetBooksbyTitle(String Title){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE title LIKE CONCAT(\'%\',?,\'%\')",
            bookRowMapper,
            Title
        );
    }

    public List<Book> GetBooksbyAuthor(String Author){
        return jdbctemplate.query(
            "SELECT * FROM Book WHERE author LIKE CONCAT(\'%\',?,\'%\')",
            bookRowMapper,
            Author
        );
    }

    public List<Book> GetALlBooks(){
        return jdbctemplate.query("SELECT * FROM Book",bookRowMapper);
    }

    //----------ChangBookAttribute------------------------------
    //need to pass arguments
    public void ChangeBookTitle(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET title = ? WHERE id = ? ",
            book.getTitle(),
            book.getId()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookAuthor(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET author = ? WHERE id = ? ",
            book.getAuthor(),
            book.getId()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuyer(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET buyer = ? WHERE id = ? ",
            book.getBuyer(),
            book.getId()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookBuytime(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET buytime = ? WHERE id = ? ",
            book.getBuytime(),
            book.getId()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookWhereis(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET whereis = ? WHERE id = ? ",
            book.getWhereis(),
            book.getId()
            )
        ){
            throw new RuntimeException("Book no found by id");
        }
    }

    public void ChangeBookIsonshelf(Book book){
        if(1 != jdbctemplate.update(
            "UPDATA Book SET isonshelf = ? WHERE id = ? ",
            book.getIsonshelf(),
            book.getId()
        )){
            throw new RuntimeException("Book no found by id");
        }
    }
}

package org.xzgtemp.service;

import org.springframework.stereotype.Component;

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


    public Boolean AddBook(Book book){



        return false;
    }


    public Book GetBookbyBID(int bid){

        return null;
    }

    public List<Book> GetBookbyTitleAndAuthor(String s){

        return null;
    }
    public List<Book> GetBookbyTitle(String Title){

        return null;
    }
    public List<Book> GetBookbyAuthor(String Author){

        return null;
    }


    public List<Book> GetALlBooks(){
        return jdbctemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public void ChangeBookTitle(Book book){

    }
    //还有其他的"ChangeBook"+其他属性名 函数

}

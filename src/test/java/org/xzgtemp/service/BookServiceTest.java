package org.xzgtemp.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xzgtemp.AppConfig;
import org.xzgtemp.entity.Book;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=AppConfig.class)
public class BookServiceTest {


    @Autowired
	BookService bookservice;

   //@Autowired
    //JdbcTemplate jdbctemplate;

    @Test
    public void testaddbook() throws ParseException{
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        java.util.Date d = null; 
        d =  ft.parse("2021-03-03");
        Date d1 = new java.sql.Date(d.getTime());
        d =  ft.parse("2022-05-05");
        Date d2 = new java.sql.Date(d.getTime());
        Book book = new Book(
            "testtitle",
            "testauthor",
            "testuid",
            d1,
            "shelf1",
            true,
            0,
            d2,
            "pub1"
            );
        System.out.println(book.getTitle());
        bookservice.AddBook(book);
    }

    @Test
    public void testaddbooks1() throws ParseException{
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        java.util.Date d = null; 
        d =  ft.parse("2021-03-03");
        Date d1 = new java.sql.Date(d.getTime());
        d =  ft.parse("2022-05-05");
        Date d2 = new java.sql.Date(d.getTime());
        long startTime = System.currentTimeMillis();
        Book book = new Book(
            "test2title",
            "test2author",
            "test2uid",
            d1,
            "shelf2",
            false,
            0,
            d2,
            "pub1"
            );
        System.out.println(book.getTitle());
        for(int i = 1; i <= 10000; i ++){
            bookservice.AddBook(book);
        }
        bookservice.AddBook(book);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (double) (endTime - startTime) / 1000 + "s");
    
    }
    
}

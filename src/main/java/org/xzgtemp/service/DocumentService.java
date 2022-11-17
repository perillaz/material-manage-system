package org.xzgtemp.service;


import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.xzgtemp.entity.Document;

@Component
public class DocumentService {
    
	@Autowired
	JdbcTemplate jdbctemplate;

	RowMapper<Document> userRowMapper = new BeanPropertyRowMapper<>(Document.class);



    public Boolean AddDocument(Document document){


        
        return false;
    }

    public Document GetBookbyBID(int bid){

        return null;
    }

    public List<Document> GetBookbyTitle(String Title){

        return null;
    }


    public List<Document> GetBookbyAuthor(String Author){

        return null;
    }


    public List<Document> GetBooks(){
        return jdbctemplate.query("SELECT * FROM Document",new BeanPropertyRowMapper<>(Document.class));
    }

}

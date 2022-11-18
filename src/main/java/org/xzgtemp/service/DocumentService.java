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

    public Document GetDocumentsbyBID(int bid){

        return null;
    }

    public List<Document> GetDocumentsbyTitle(String Title){

        return null;
    }


    public List<Document> GetDocumentsbyAuthor(String Author){

        return null;
    }


    public List<Document> GetAllDocuments(){
        return jdbctemplate.query("SELECT * FROM Document",new BeanPropertyRowMapper<>(Document.class));
    }


    public void ChangeDocumentTitle(Document document){

    }
    //还有其他的"ChangeDocument"+其他属性名 函数

}

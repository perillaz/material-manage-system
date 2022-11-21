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

import org.xzgtemp.entity.Document;

@Component
public class DocumentService {
    
	@Autowired
	JdbcTemplate jdbctemplate;

	RowMapper<Document> documentRowMapper = new BeanPropertyRowMapper<>(Document.class);


    //---------------AddDocunment---------------------------------
    public void AddDocument(Document document){
        //String title, String author, String uploaduser, Date uploadtime, String filepath
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Document(title,author,uploader,uploadtime,filepath,downloadtimes) VALUES(?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,document.getTitle());
                    ps.setObject(2,document.getAuthor());
                    ps.setObject(3,document.getUploader());
                    ps.setObject(4,document.getUploadtime());
                    ps.setObject(5,document.getFilepath());
                    ps.setObject(6,document.getDownloadtimes());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        document.setId(holder.getKey().longValue());
    }

    //------------GetDocunmentByAttribute-----------------------------
    public Document GetDocumentsbyDID(int did){
        String sql = "SELECT * FROM Document WHERE id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Document(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("uploader"),
                            rs.getDate("uploadtime"),
                            rs.getString("filepath"),
                            rs.getInt("downloadtimes")
                    );
                },
                did
        );
    }

    public List<Document> GetDocumentsbyTitleOrAuthor(String s){
        return jdbctemplate.query(
            "SELECT * FROM Document WHERE title LIKE CONCAT(\'%\',?,\'%\') OR author LIKE CONCAT(\'%\',?,\'%\')",
            documentRowMapper,
            s,
            s
        );
    }

    public List<Document> GetDocumentsbyTitle(String Title){
        return jdbctemplate.query(
            "SELECT * FROM Document WHERE title LIKE CONCAT(\'%\',?,\'%\')",
            documentRowMapper,
            Title
        );
    }


    public List<Document> GetDocumentsbyAuthor(String Author){
        return jdbctemplate.query(
            "SELECT * FROM Document WHERE author LIKE CONCAT(\'%\',?,\'%\')",
            documentRowMapper,
            Author
        );
    }


    public List<Document> GetAllDocuments(){
        return jdbctemplate.query("SELECT * FROM Document",documentRowMapper);
    }

    //----------ChangDocumentAttribute------------------------------
    //need pass arguments
    public void ChangeDocumentTitle(Document document){
        if(1 != jdbctemplate.update(
            "UPDATA Document SET title = ? WHERE id = ? ",
            document.getTitle(),
            document.getId()
            )
        ){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentAuthor(Document document){
        if(1 != jdbctemplate.update(
            "UPDATA Document SET author = ? WHERE id = ? ",
            document.getAuthor(),
            document.getId()
            )
        ){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentuploader(Document document){
        if(1 != jdbctemplate.update(
                "UPDATA Document SET uploader = ? WHERE id = ? ",
                document.getUploader(),
                document.getId()
            )
        ){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentfilepath(Document document){
        if(1 != jdbctemplate.update(
            "UPDATA Document SET filepath = ? WHERE id = ? ",
            document.getFilepath(),
            document.getId()
            )
        ){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentdownloadtimes(Document document){
        if(1 != jdbctemplate.update(
            "UPDATA Document SET downloadtimes = ? WHERE id = ? ",
            document.getDownloadtimes(),
            document.getId()
            )
        ){
            throw new RuntimeException("Document no found by id.");
        }
    }
}

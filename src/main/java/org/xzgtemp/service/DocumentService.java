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

	RowMapper<Document> userRowMapper = new BeanPropertyRowMapper<>(Document.class);



    //---------------AddDocunment---------------------------------
    public void AddDocument(Document document){
        //String title, String author, String uploaduser, Date uploadtime, String filepath
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Document(d_id,d_title,d_author,d_uploader,d_uploadtime,d_filepath,d_downloadtimes) VALUES(?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,holder.getKey().longValue());
                    ps.setObject(2,document.GetTitle());
                    ps.setObject(3,document.GetAuthor());
                    ps.setObject(4,document.GetUploader());
                    ps.setObject(5,document.GetUploadtime());
                    ps.setObject(6,document.GetFilepath());
                    ps.setObject(7,document.GetDownloadtimes());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
    }

    //------------GetDocunmentByAttribute-----------------------------
    public Document GetDocumentsbyDID(int did){
        String sql = "SELECT * FROM Document WHERE d_id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Document(
                            rs.getLong("d_id"),
                            rs.getString("d_title"),
                            rs.getString("d_author"),
                            rs.getString("d_uploader"),
                            rs.getDate("d_uploadtime"),
                            rs.getString("d_filepath"),
                            rs.getInt("d_downloadtimes")
                    );
                },
                did
        );
    }

    public List<Document> GetDocumentsbyTitle(String Title){
        String sql = "SELECT * FROM Document WHERE d_title LIKE %?%";
        return jdbctemplate.query(sql,
                (ResultSet rs,int rowNum) -> {
                    return new Document(
                            rs.getLong("d_id"),
                            rs.getString("d_title"),
                            rs.getString("d_author"),
                            rs.getString("d_uploader"),
                            rs.getDate("d_uploadtime"),
                            rs.getString("d_filepath"),
                            rs.getInt("d_downloadtimes")
                    );
                },
                Title
        );
    }


    public List<Document> GetDocumentsbyAuthor(String Author){
        String sql = "SELECT * FROM Document WHERE d_author = ?";
        return jdbctemplate.query(sql,
                (ResultSet rs,int rowNum) -> {
                    return new Document(
                            rs.getLong("d_id"),
                            rs.getString("d_title"),
                            rs.getString("d_author"),
                            rs.getString("d_uploader"),
                            rs.getDate("d_uploadtime"),
                            rs.getString("d_filepath"),
                            rs.getInt("d_downloadtimes")
                    );
                },
                Author
        );
    }


    public List<Document> GetAllDocuments(){
        return jdbctemplate.query("SELECT * FROM Document",new BeanPropertyRowMapper<>(Document.class));
    }

    //----------ChangDocumentAttribute------------------------------
    //need pass arguments
    public void ChangeDocumentTitle(Document document){
        String sql = "UPDATA Document SET d_title = ? WHERE d_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentAuthor(Document document){
        String sql = "UPDATA Document SET d_author = ? WHERE d_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentuploader(Document document){
        String sql = "UPDATA Document SET d_uploader = ? WHERE d_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentfilepath(Document document){
        String sql = "UPDATA Document SET d_filepath = ? WHERE d_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Document no found by id.");
        }
    }

    public void ChangeDocumentdownloadtimes(Document document){
        String sql = "UPDATA Document SET d_downloadtimes = ? WHERE d_id = ? ";
        if(1 != jdbctemplate.update(sql)){
            throw new RuntimeException("Document no found by id.");
        }
    }
}

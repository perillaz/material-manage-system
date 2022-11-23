package org.xzgtemp.service;


import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.User;

@Component
public class DocumentService {
    
	@Autowired
	JdbcTemplate jdbctemplate;

	RowMapper<Document> documentRowMapper = new BeanPropertyRowMapper<>(Document.class);


    //---------------AddDocunment---------------------------------
    public void AddBookFromFIle(){
        //TODO:从文件读取记录批量导入数据库，使用预编译，参数为定
    }
    
    public Document UploadDocument(User user,MultipartFile file,
        String title,
        String author
        ){
        try {
            String uploader = user.getId();
            Date date = Date.valueOf(LocalDate.now());
            String fileoriginname = file.getOriginalFilename();
            String filetype = fileoriginname.substring(fileoriginname.lastIndexOf("."));
            String filename = title + "_" + author+ "_" + String.valueOf(System.currentTimeMillis() % 1000000000) + filetype;
            File file1 =new File("files/" + filename);//创建file对象
            if(!file1.exists())
                file1.createNewFile();//在磁盘创建该文件
            file.transferTo(file1);//将接受的文件存储
            Document document = new Document(title,author,uploader,date,filename);
            AddDocument(document);
            return document; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AddDocument(Document document){
        //TODO：增加其他属性
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
    public Document GetDocumentsbyDID(Long did){
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
    //TODO:合并以下函数(需要同时该html和controller)
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

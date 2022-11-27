package org.xzgtemp.service;


import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.DownloadDocument;
import org.xzgtemp.entity.User;

@Component
public class DocumentService {
    
	@Autowired
	JdbcTemplate jdbctemplate;

    public final String DocumentFileRootPath = System.getProperty("user.dir") + "/files/";

	RowMapper<Document> documentRowMapper = new BeanPropertyRowMapper<>(Document.class);

    //---------------AddDocunment---------------------------------
    
    public Document UploadDocument(User user,MultipartFile file,
        String title,
        String author,
        String doi,
        String literature
        ){
        try {
            Date date = Date.valueOf(LocalDate.now());
            String fileoriginname = file.getOriginalFilename();
            String filetype = fileoriginname.substring(fileoriginname.lastIndexOf("."));
            //String filename = doi + title +  author + literature + String.valueOf(System.currentTimeMillis() % 1000000000) + filetype;
            String filename = UUID.randomUUID().toString().replace("-", "") + filetype;
            File file1 =new File("files/" + filename);//创建file对象
            if(!file1.exists())
                file1.createNewFile();//在磁盘创建该文件
            file.transferTo(file1);//将接受的文件存储
            Document document = new Document(title,author,user.getId(),user.getName(),date,filename,doi,literature);
            AddDocument(document);
            return document; 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void AddDocument(Document document){
        //String title, String author, String uploaduser, Date uploadtime, String filepath
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Document(title,author,uploaderid,uploadername,uploadtime,filepath,downloadtimes,doi,literature) VALUES(?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,document.getTitle());
                    ps.setObject(2,document.getAuthor());
                    ps.setObject(3,document.getUploaderid());
                    ps.setObject(4,document.getUploadername());
                    ps.setObject(5,document.getUploadtime());
                    ps.setObject(6,document.getFilepath());
                    ps.setObject(7,document.getDownloadtimes());
                    ps.setObject(8,document.getDoi());
                    ps.setObject(9,document.getLiterature());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        document.setId(holder.getKey().longValue());
    }

    //------------GetDocunmentByAttribute-----------------------------
    public Document GetDocumentbyDID(Long did){
        String sql = "SELECT * FROM Document WHERE id = ?";
        return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Document(
                            rs.getLong("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("uploaderid"),
                            rs.getString("uploadername"),
                            rs.getDate("uploadtime"),
                            rs.getString("filepath"),
                            rs.getInt("downloadtimes"),
                            rs.getString("doi"),
                            rs.getString("literature")
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

    public void ChangeDocumentAttribute(Document document,String attribute,Object value){
        try {
            Field field = document.getClass().getDeclaredField(attribute);
            field.setAccessible(true);
            field.set(document,value);
            if(1 != jdbctemplate.update(
                "UPDATE Document SET " + attribute + " = ? WHERE id = ? ",
                //attribute,
                field.get(document),
                document.getId()
                )
            ){
                throw new RuntimeException("Failed to change");
            }
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }


    public void AddDownloadDocument(DownloadDocument downloaddocument){
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO DownloadDocument(uid,did,dtitle,downloadtime) VALUES(?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1,downloaddocument.getUid());
                    ps.setObject(2,downloaddocument.getDid());
                    ps.setObject(3,downloaddocument.getDtitle());
                    ps.setObject(4,downloaddocument.getDownloadtime());
                    return ps;
                },
                holder)
        ){
            throw new RuntimeException("Insert failed.");
        }
        downloaddocument.setId(holder.getKey().longValue());
    }


    public void doAddDownloadDocument(User user,Long did){
        Document document = GetDocumentbyDID(did);
        AddDownloadDocument(new DownloadDocument(user.getId(),did,document.getTitle(),Date.valueOf(LocalDate.now())));
    }

}

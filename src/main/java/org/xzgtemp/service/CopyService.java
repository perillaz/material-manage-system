package org.xzgtemp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.reflect.Field;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import org.xzgtemp.entity.Copy;

@Component
public class CopyService {
	
	@Autowired
	JdbcTemplate jdbctemplate;
    
    RowMapper<Copy> copyRowMapper = new BeanPropertyRowMapper<>(Copy.class);
    
    public void AddCopy(Copy copy){
        //TODO:增加copy
    	KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Copy(bid,btitle,loc,isborrowed,isreserved,buyerid,buyername,buytime,borrowtimes) VALUES(?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,copy.getBid());
            ps.setObject(2,copy.getBtitle());
            ps.setObject(3,copy.getLoc());
            ps.setObject(4,copy.getIsborrowed());
            ps.setObject(5,copy.getIsreserved());
            ps.setObject(6,copy.getBuyerid());
            ps.setObject(7,copy.getBuyername());
            ps.setObject(8,copy.getBuytime());
            ps.setObject(9,copy.getBorrowtimes());
            return ps;
        },
        holder)
       )
        {
            throw new RuntimeException("Insert failed.");
        }
        copy.setId(holder.getKey().longValue());
    }

    public Copy Getcopy(Long id) {
    	//获得copy信息
    	String sql="SELECT * FROM COPY WHERE id=?";
    	return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Copy(
                        rs.getLong("id"),
                        rs.getLong("bid"),
                        rs.getString("btilte"),
                        rs.getString("loc"),
                        rs.getBoolean("isborrowed"),
                        rs.getBoolean("isreserved"),
                        rs.getString("buyerid"),
                        rs.getString("buyername"),
                        rs.getDate("buytime"),
                        rs.getInt("borrowtimes")
                    );
                }
                ,id
        );
    }
    
    
    public void ReserveCopy(Long id){
        //TODO:预约图书，参数未定
    	Copy copyreserve=Getcopy(id);
    	if(copyreserve.getIsreserved()==false) {
    		copyreserve.setIsreserved(true);
    	}
    	else {
    		
    	}
    }

    public void BorrowCopy(Long id){
        //TODO:借阅图书，参数未定，返回值也未定
    	Copy copyborrow=Getcopy(id);
    	if(copyborrow.getIsborrowed()==false) {
    		copyborrow.setIsborrowed(true);
    	}
    	else {
    		
    	}
    }
    
    public boolean DeleteCopy(Copy copy, Long id) {
    	//TODO:删除后，是否需要获取title和copyid,显示“已成功删除xxxx”
    	boolean flag=false;
    	try {
    		if(1 != jdbctemplate.update("DELETE frome Cpoy where id=" + id,
    				flag=true)
                )
    		{
                throw new RuntimeException("Failed to delete");
            }
    	}catch (Exception e) {
            throw new RuntimeException(e);
            }
    	return flag;
    }
    	
    
    public void ChageCopy(Copy copy,String attribute,Object value) {
    	//TODO:修改copy信息
    	try {
            Field field = copy.getClass().getDeclaredField(attribute);
            field.setAccessible(true);
            field.set(copy,value);
            if(1 != jdbctemplate.update(
                "UPDATE Copy SET " + attribute + " = ? WHERE id = ? ",
                //attribute,
                field.get(copy),
                copy.getId()
                )
            ){
                throw new RuntimeException("Failed to change");
            }
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }

}

package org.xzgtemp.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.xzgtemp.entity.Book;
import org.xzgtemp.entity.BorrowCopy;
import org.xzgtemp.entity.Copy;
import org.xzgtemp.entity.User;

@Component
public class CopyService {
	
	@Autowired
	JdbcTemplate jdbctemplate;
    
    RowMapper<Copy> copyRowMapper = new BeanPropertyRowMapper<>(Copy.class);
    RowMapper<BorrowCopy> borrowcopyRowMapper = new BeanPropertyRowMapper<>(BorrowCopy.class);
    
    public void AddCopy(Copy copy){
    	KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Copy(bid,btitle,loc,buyerid,buyername,buytime,borrowtimes,canbeborrow,canbereserve,reserver) VALUES(?,?,?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,copy.getBid());
            ps.setObject(2,copy.getBtitle());
            ps.setObject(3,copy.getLoc());
            ps.setObject(4,copy.getBuyerid());
            ps.setObject(5,copy.getBuyername());
            ps.setObject(6,copy.getBuytime());
            ps.setObject(7,copy.getBorrowtimes());
            ps.setObject(8,copy.getCanbeborrow());
            ps.setObject(9,copy.getCanbereserve());
            ps.setObject(10,copy.getReserver());
            return ps;
        },
        holder)
       )
        {
            throw new RuntimeException("Insert failed.");
        }
        copy.setId(holder.getKey().longValue());
    }
    public void AddBorrowCopy(BorrowCopy borrowcopy){
    	KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO BorrowCopy(uid,cid,bid,btitle,borrowtime,sendbacktime,duetime,finished) VALUES(?,?,?,?,?,?,?,?)";
        if(1 != jdbctemplate.update((conn) -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1,borrowcopy.getUid());
            ps.setObject(2,borrowcopy.getCid());
            ps.setObject(3,borrowcopy.getBid());
            ps.setObject(4,borrowcopy.getBtitle());
            ps.setObject(5,borrowcopy.getBorrowtime());
            ps.setObject(6,borrowcopy.getSendbacktime());
            ps.setObject(7,borrowcopy.getDuetime());
            ps.setObject(8,borrowcopy.getFinished());
            return ps;
        },
        holder)
       )
        {
            throw new RuntimeException("Insert failed.");
        }
        borrowcopy.setId(holder.getKey().longValue());
    }

    public Long doAddCopy(User user,Book book,String loc){
        Copy copy = new Copy(book.getId(),book.getTitle(),loc,user.getId(),user.getName(),Date.valueOf(LocalDate.now()));
        AddCopy(copy);
        return copy.getId();
    }

    public void DeleteCopy(Long id){
        if(1 != jdbctemplate.update(
                "DELETE FROM Copy WHERE id = ?",
                id
                )
            ){
                throw new RuntimeException("Failed to delete");
            }
    }

    public Copy GetCopybyCID(Long id) {
    	//获得copy信息
    	String sql="SELECT * FROM COPY WHERE id=?";
    	return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new Copy(
                        rs.getLong("id"),
                        rs.getLong("bid"),
                        rs.getString("btitle"),
                        rs.getString("loc"),
                        rs.getString("buyerid"),
                        rs.getString("buyername"),
                        rs.getDate("buytime"),
                        rs.getInt("borrowtimes"),
                        rs.getBoolean("canbeborrow"),
                        rs.getBoolean("canbereserve"),
                        rs.getString("reserver")
                    );
                }
                ,id
        );
    }


    public BorrowCopy GetBorrowCopybyID(Long id) {
    	//获得copy信息
    	String sql="SELECT * FROM BorrowCopy WHERE id =?";
    	return jdbctemplate.queryForObject(sql,
                (ResultSet rs, int rowNum) -> {
                    return new BorrowCopy(
                        rs.getLong("id"),
                        rs.getString("uid"),
                        rs.getLong("cid"),
                        rs.getLong("bid"),
                        rs.getString("btitle"),
                        rs.getDate("borrowtime"),
                        rs.getDate("sendbacktime"),
                        rs.getDate("duetime"),
                        rs.getBoolean("finished")
                    );
                }
                ,id
        );
    }

    public List<Copy> GetCopybyBID(Long bid){
        return jdbctemplate.query(
            "SELECT * FROM Copy WHERE bid = ?",
            copyRowMapper,
            bid
        );
    }

    public List<BorrowCopy> GetUnfinnishedBorrowCopybyUid(String uid){
        return jdbctemplate.query(
            "SELECT * FROM BorrowCopy WHERE uid = ? AND  finished = false",
            borrowcopyRowMapper,
            uid
        );
    }

    public List<BorrowCopy> GetFinnishedBorrowCopybyUid(String uid){
        return jdbctemplate.query(
            "SELECT * FROM BorrowCopy WHERE uid = ? AND  finished = true",
            borrowcopyRowMapper,
            uid
        );
    }

    public List<Copy> GetCopiesbyBuyerid(String buyerid){
        return jdbctemplate.query(
            "SELECT * FROM Copy WHERE buyerid = ?",
            copyRowMapper,
            buyerid
        );
    }

    
    public void ReserveCopy(Long cid, User user){
        Copy copy = GetCopybyCID(cid);
        ChangeCopy(copy,"canbereserve",false);
        ChangeCopy(copy,"reserver",user.getId());
    }

    public BorrowCopy doBorrowCopy(Long cid, User user){
        Copy copy = GetCopybyCID(cid);
        System.out.println(copy.toString());
        Date today = Date.valueOf(LocalDate.now());
        BorrowCopy borrowcopy = new BorrowCopy(user.getId(),cid,copy.getBid() ,copy.getBtitle(),today,today,getDueTime(today),false);
        AddBorrowCopy(borrowcopy);
        ChangeCopy(copy,"canbeborrow",false);
        ChangeCopy(copy,"canbereserve",true);
        return borrowcopy;
    }
    
    public void ReturnCopy(Long cid,Long bcid){
        Copy copy = GetCopybyCID(cid);
        BorrowCopy borrowcopy = GetBorrowCopybyID(bcid);
        ChangeCopy(copy,"canbeborrow",true);
        ChangeBorrowCopy(borrowcopy, "finished",true);
        ChangeBorrowCopy(borrowcopy, "sendbacktime",Date.valueOf(LocalDate.now()));
    }

    private Date getDueTime( Date starttime ) {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(starttime);
        calendar.add(calendar.DATE, 14);
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
        return new Date(utilDate.getTime());
    }

    public void ChangeCopy(Copy copy,String attribute,Object value) {
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

    public void ChangeBorrowCopy(BorrowCopy borrowcopy,String attribute,Object value) {
    	try {
            Field field = borrowcopy.getClass().getDeclaredField(attribute);
            field.setAccessible(true);
            field.set(borrowcopy,value);
            if(1 != jdbctemplate.update(
                "UPDATE BorrowCopy SET " + attribute + " = ? WHERE id = ? ",
                //attribute,
                field.get(borrowcopy),
                borrowcopy.getId()
                )
            ){
                throw new RuntimeException("Failed to change");
            }
        } catch (Exception e) {
            throw new RuntimeException(e); 
        }
    }

}

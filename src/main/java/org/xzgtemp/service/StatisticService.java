package org.xzgtemp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.xzgtemp.entity.UserStatistic;
import org.xzgtemp.entity.WeekBorrowRank;
import org.xzgtemp.entity.WeekDownloadRank;

@Component
public class StatisticService {

	@Autowired
	JdbcTemplate jdbctemplate;

    RowMapper<UserStatistic> userstatisticRowMapper = new BeanPropertyRowMapper<>(UserStatistic.class);
    RowMapper<WeekBorrowRank> weekborrowrankRowMapper = new BeanPropertyRowMapper<>(WeekBorrowRank.class);
    RowMapper<WeekDownloadRank> weekdownloadrankRowMapper = new BeanPropertyRowMapper<>(WeekDownloadRank.class);

    public List<UserStatistic> GetUserStatistic(){
        return jdbctemplate.query("SELECT * FROM UserStatistics",userstatisticRowMapper);
    }

    public List<WeekBorrowRank> GetWeekBorrowRank(){
        return jdbctemplate.query("SELECT bid AS bookid,btitle AS booktitle, COUNT(*) AS borrowtimes FROM BorrowCopy WHERE borrowtime >= ? GROUP BY bid ORDER BY borrowtimes DESC LIMIT 10",
            weekborrowrankRowMapper,
            getDateM7()
        );
    }

    public List<WeekDownloadRank> GetWeekDownloadRank(){
        return jdbctemplate.query("SELECT did AS documentid,dtitle AS documenttitle, COUNT(*) AS downloadtimes FROM DownloadDocument WHERE downloadtime >= ? GROUP BY did ORDER BY downloadtimes DESC LIMIT 10",
            weekdownloadrankRowMapper,
            getDateM7()
        );
    }

    private Date getDateM7() {
        Calendar calendar =new GregorianCalendar();
        calendar.setTime(Date.valueOf(LocalDate.now()));
        calendar.add(calendar.DATE, -7);
        java.util.Date utilDate = (java.util.Date)calendar.getTime();
        utilDate = (java.util.Date)calendar.getTime();
        return new Date(utilDate.getTime());
    }

}

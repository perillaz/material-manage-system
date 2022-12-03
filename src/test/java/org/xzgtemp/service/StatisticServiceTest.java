package org.xzgtemp.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xzgtemp.AppConfig;
import org.xzgtemp.entity.UserStatistic;
import org.xzgtemp.entity.WeekBorrowRank;
import org.xzgtemp.entity.WeekDownloadRank;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=AppConfig.class)
public class StatisticServiceTest {

	@Autowired
	StatisticService statisticservice;

    @Test
    public void GetUserStatisticTest(){
        List<UserStatistic> ulist = statisticservice.GetUserStatistic();
        for(UserStatistic us:ulist){
            System.out.println(us.toString());
        }
    }

    
    @Test
    public void GetWeekBorrowRankTest(){
        List<WeekBorrowRank> wbrlist = statisticservice.GetWeekBorrowRank();
        for(WeekBorrowRank wbr:wbrlist){
            System.out.println(wbr.toString());
        }
    }

    @Test
    public void GetWeekDownloadRankTest(){
        List<WeekDownloadRank> wdrlist = statisticservice.GetWeekDownloadRank();
        for(WeekDownloadRank wdr:wdrlist){
            System.out.println(wdr.toString());
        }
    }
    
}

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

        long startTime = System.currentTimeMillis();
        List<UserStatistic> ulist = statisticservice.GetUserStatistic();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");
        for(UserStatistic us:ulist){
            System.out.println(us.toString());
        }
    }

    
    @Test
    public void GetWeekBorrowRankTest(){
        long startTime = System.currentTimeMillis();
        List<WeekBorrowRank> wbrlist = statisticservice.GetWeekBorrowRank();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");
        for(WeekBorrowRank wbr:wbrlist){
            System.out.println(wbr.toString());
        }
    }

    @Test
    public void GetWeekDownloadRankTest(){
        long startTime = System.currentTimeMillis();
        List<WeekDownloadRank> wdrlist = statisticservice.GetWeekDownloadRank();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");
        for(WeekDownloadRank wdr:wdrlist){
            System.out.println(wdr.toString());
        }
    }
    
}

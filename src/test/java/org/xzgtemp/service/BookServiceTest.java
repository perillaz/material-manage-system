package org.xzgtemp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xzgtemp.AppConfig;
import org.xzgtemp.entity.Book;
import org.xzgtemp.util.SqlDateUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=AppConfig.class)
public class BookServiceTest {


    @Autowired
	BookService bookservice;

    @Autowired
    JdbcTemplate jdbctemplate;

	@Autowired
	SqlDateUtil sqldateutil;

	public List<Book> getBooksByIO() throws IOException{
        FileReader fr = new FileReader(System.getProperty("user.dir") + "/files/btest1.txt");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Book> list = new ArrayList<>();
		String line = null;
		while ((line = br.readLine())!=null) {
			String[] attr = line.split(",");
			Book book = new Book(
                attr[0],
                attr[1],
                Integer.parseInt(attr[2]),
                Integer.parseInt(attr[3]),
                attr[4],
                attr[5],
                sqldateutil.StringtoDate(attr[6]),
                attr[7],
                attr[8],
                attr[9]
            );
			list.add(book);
		}
		br.close();
		return list;
    }


	public void batchupdate(List<Book> blist) throws IOException{
		String insertSql = "INSERT INTO Book(title,author,copyamount,borrowedcopys,allborrowtimes,isbn,edition,publishtime,publisher,lang,briefinfo) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = blist.stream().map(book -> {
            return new Object[]{
				book.getTitle(),
				book.getAuthor(),
                book.getCopyamount(),
                book.getBorrowedcopys(),
                book.getAllborrowtimes(),
                book.getIsbn(),
                book.getEdition(),
                book.getPublishtime(),
                book.getPublisher(),
                book.getLang(),
                book.getBriefinfo()
			};
        }).collect(Collectors.toList());
        jdbctemplate.batchUpdate(insertSql, batchArgs);
	}


    @Test
	public void AddTest1() throws IOException{
        long startTime = System.currentTimeMillis();
		List<Book> blist = getBooksByIO();
        long endTime = System.currentTimeMillis();
        System.out.println("读入数据时间  :" + (double) (endTime - startTime) / 1000 + "s");
		System.out.println("行数 : " + blist.size());
		jdbctemplate.update("TRUNCATE TABLE Book");
        startTime = System.currentTimeMillis();
		batchupdate(blist);
        endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");

	}
}

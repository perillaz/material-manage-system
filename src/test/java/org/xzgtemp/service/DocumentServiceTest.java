package org.xzgtemp.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xzgtemp.AppConfig;
import org.xzgtemp.entity.Document;
import org.xzgtemp.util.SqlDateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=AppConfig.class)
public class DocumentServiceTest {
    
	@Autowired
	DocumentService documentservice;

	@Autowired
	JdbcTemplate jdbctemplate;

	@Autowired
	SqlDateUtil sqldateutil;



	public List<Document> getDocumentsByapacheCommonsIO() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/files/test7.txt");
		ArrayList<Document> list = new ArrayList<>();
		LineIterator iterator = FileUtils.lineIterator(file, "UTf-8");
		while (iterator.hasNext()) {
		String line = iterator.nextLine();
			String[] attr = line.split(",");
			Document document = new Document(attr[0],attr[1],attr[2],attr[3],sqldateutil.StringtoDate(attr[4]),attr[5],attr[6],attr[7]);
			list.add(document);
		}
		return list;
	  }

	public List<Document> getDocumentsByIO() throws IOException{
        FileReader fr = new FileReader(System.getProperty("user.dir") + "/files/test7.txt");
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Document> list = new ArrayList<>();
		String line = null;
		while ((line = br.readLine())!=null) {
			String[] attr = line.split(",");
			Document document = new Document(attr[0],attr[1],attr[2],attr[3],sqldateutil.StringtoDate(attr[4]),attr[5],attr[6],attr[7]);
			list.add(document);
		}
		br.close();
		return list;
    }

	public void Add1(List<Document> dlist) throws IOException{
		for (Document d : dlist) {
			documentservice.AddDocument(d);
		}
	}


	public void batchupdate(List<Document> dlist) throws IOException{
		String insertSql = "INSERT INTO Document(title,author,uploaderid,uploadername,uploadtime,filepath,downloadtimes,doi,literature) VALUES(?,?,?,?,?,?,?,?,?)";
        List<Object[]> batchArgs = dlist.stream().map(document -> {
            return new Object[]{
				document.getTitle(),
				document.getAuthor(),
				document.getUploaderid(),
				document.getUploadername(),
				document.getUploadtime(),
				document.getFilepath(),
				document.getDownloadtimes(),
				document.getDoi(),
				document.getLiterature()
			};
        }).collect(Collectors.toList());
        jdbctemplate.batchUpdate(insertSql, batchArgs);
	}

	@Test
	public void AddTest2() throws IOException{
        long startTime = System.currentTimeMillis();
		List<Document> dlist = getDocumentsByIO();
        long endTime = System.currentTimeMillis();
        System.out.println("读入数据时间  :" + (double) (endTime - startTime) / 1000 + "s");

		jdbctemplate.update("TRUNCATE TABLE Document");
        startTime = System.currentTimeMillis();
		Add1(dlist);
        endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");

	}

	@Test
	public void AddTest1() throws IOException{
        long startTime = System.currentTimeMillis();
		List<Document> dlist = getDocumentsByIO();
        long endTime = System.currentTimeMillis();
        System.out.println("读入数据时间  :" + (double) (endTime - startTime) / 1000 + "s");
		System.out.println("行数 : " + dlist.size());
		jdbctemplate.update("TRUNCATE TABLE Document");
        startTime = System.currentTimeMillis();
		batchupdate(dlist);
        endTime = System.currentTimeMillis();
        System.out.println("程序运行时间  :" + (double) (endTime - startTime) / 1000 + "s");

	}


	@Test
	public void Test3() throws IOException{
        long startTime = System.currentTimeMillis();
		List<Document> dlist = getDocumentsByapacheCommonsIO();
        long endTime = System.currentTimeMillis();
        System.out.println("读入数据时间  :" + (double) (endTime - startTime) / 1000 + "s");
		System.out.println("行数 : " + dlist.size());
		jdbctemplate.update("TRUNCATE TABLE Document");
	}

}

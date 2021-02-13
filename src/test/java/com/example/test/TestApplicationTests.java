package com.example.test;

import com.example.test.bean.Browse;
import com.example.test.bean.Document;
import com.example.test.controller.BrowseController;
import com.example.test.controller.RecycleController;
import com.example.test.service.BrowseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestApplicationTests {

	@Autowired
	DataSource dataSource;
	@Autowired
	BrowseService browseService;
	@Autowired
	BrowseController browseController;
	@Autowired
	RecycleController recycleController;

	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}

	@Test
	public void testGetBrowse(){
		System.out.println(browseController.getBrowse(1));
	}

	@Test
	public void testDelete(){

	}

}

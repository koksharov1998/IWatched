package com.example.IWatched;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class IWatchedApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(IWatchedApplication.class, args);
    //getConnectionToDatabase();

	}

	private static void getConnectionToDatabase() throws Exception {
    Connection conn = DriverManager.
        getConnection("jdbc:h2:~/test", "sa", "");
    // add application code here
    conn.close();
  }
}

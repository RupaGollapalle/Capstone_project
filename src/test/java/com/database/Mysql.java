package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Mysql {

	
	public boolean dbCheck(String name, String email) {
		String URL = "jdbc:mysql://localhost:3306/FlyAway";
        String UN  = "root";
        String PWD = "";

        String qry = " select * from f_user ";

        try{
            Connection conn = DriverManager.getConnection(URL,UN,PWD);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(qry);

            while(rs.next()){
               
                if(Objects.equals(name, rs.getString(2)) && Objects.equals(email, rs.getString(3))) {
                	 System.out.println(" -- Name : "+rs.getString(2)+"-- Email:  "+rs.getString(3));
                	return true;
                }
                
            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }
        
        return false;

	}
	
		
}

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DbUtil {
	private String dbUrl="jdbc:mysql://localhost:3306/db_studentInfo";
	private String JdbcName="com.mysql.jdbc.Driver";
	private String dbUserName="root";
	private String dbPassword="941013";
	
	
	//������ݿ�����
	public Connection getCon() throws ClassNotFoundException, SQLException{
		Class.forName(JdbcName);
		Connection con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
	    return con;
	}
	
	//�ر����ݿ�����
	public void closeCon(Connection con) throws SQLException{
		if(con!=null)
			con.close();
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException{
		DbUtil dbUtil=new DbUtil();
		dbUtil.getCon();
		System.out.println("���ݿ����ӳɹ�");
		
	}

}

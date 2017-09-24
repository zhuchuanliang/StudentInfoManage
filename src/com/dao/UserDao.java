package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.User;
import java.sql.ResultSet;


public class UserDao {
	
	//��½��֤
	public User login(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
		
	}
	
	

}

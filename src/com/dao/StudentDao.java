package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.httpclient.util.DateUtil;

import com.model.PageBean;
import com.model.Student;
import com.util.StringUtil;


public class StudentDao {

	/*
	 * 查询学生列表
	 */
	public ResultSet studentList(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int studentCount(Connection con)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int studentAdd(Connection con,Student student) throws Exception{
		String sql="insert into t_student values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuNo());
		pstmt.setString(2, student.getStuName());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
		pstmt.setInt(5, student.getGradeId());
		pstmt.setString(6, student.getEmail());
		pstmt.setString(7, student.getStuDesc());
		return pstmt.executeUpdate();
		
	}
	public int studentModify(Connection con,Student student) throws Exception{
		String sql="update t_student set stuNo=?,stuName=?,sex=?,birthday=?,gradeId=?,email=?,stuDesc=? where stuId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuNo());
		pstmt.setString(2, student.getStuName());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
		pstmt.setInt(5, student.getGradeId());
		pstmt.setString(6, student.getEmail());
		pstmt.setString(7, student.getStuDesc());
		pstmt.setInt(8, student.getStuId());
		return pstmt.executeUpdate();
	}	
	public int studentDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_student where stuId in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
	return pstmt.executeUpdate();
}	
	
	
	public boolean getStudentByGradeId(Connection con,String gradeId) throws Exception{
		String sql="select * from t_student where gradeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, gradeId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		else
			return false;
	}
}
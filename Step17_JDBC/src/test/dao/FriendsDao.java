package test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.FriendsDto;
import test.util.DBConnect;

public class FriendsDao {
	
	public boolean insert(FriendsDto dto) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		int rowCount = 0;
		try {
			conn = new DBConnect().getConn();
			
			String sql = "INSERT INTO friend(num, name, phone, birth)"
					+ " VALUES(friend_seq.NEXTVAL,?,?,?)";
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dto.getName());
			pstm.setString(2, dto.getPhone());
			pstm.setDate(3, (Date)dto.getBirth());
			
			rowCount = pstm.executeUpdate();
		} catch (Exception e) {
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean update(FriendsDto dto) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		int rowCount = 0;
		try {
			conn = new DBConnect().getConn();
			String sql = "UPDATE friend"
					+ " SET name = ?, phone = ?, birth = ?"
					+ " WHERE num = ?";
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, dto.getName());
			pstm.setString(2, dto.getPhone());
			pstm.setDate(3, (Date)dto.getBirth());
			pstm.setInt(4, dto.getNum());
			
			rowCount = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(rowCount > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		int rowCount = 0;
		
		try {
			conn = new DBConnect().getConn();
			String sql = "DELETE FROM friend"
					+ " WHERE num = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, num);
			rowCount = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		if(rowCount > 0 ) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<FriendsDto> getList(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<FriendsDto> list = new ArrayList<>();
		try {
			conn = new DBConnect().getConn();
			String sql = "SELECT *"
					+ " FROM friend"
					+ " ORDER BY num ASC" ;
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				FriendsDto dto = new FriendsDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setBirth(rs.getDate("birth"));
				list.add(dto);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;	 
	}
	
	public FriendsDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		FriendsDto dto = new FriendsDto();
		
		try {
			conn = new DBConnect().getConn();
			String sql = "SELECT *"
					+ " FROM friend"
					+ " WHERE num = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, num);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setBirth(rs.getDate("birth"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
}

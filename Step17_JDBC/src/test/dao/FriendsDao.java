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
	// 친구한명의 정보를 저장하고 그 작업의 성공 여부를 리턴해주는 메소드
	public boolean insert(FriendsDto dto) {
		Connection conn = null;
		PreparedStatement pstm = null;
		// 수정된 row의 갯수를 담을 지역변수를 미리 만들고 초기값 0 대입하기
		int rowCount = 0;
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DBConnect().getConn();
			// 실행할  sql문
			String sql = "INSERT INTO friend(num, name, phone, birth)"
					+ " VALUES(friend_seq.NEXTVAL,?,?,?)";
			//PreparedStatement 객체의 참조값 얻어오기
			pstm = conn.prepareStatement(sql);
			// ?에 바인딩하기
			pstm.setString(1, dto.getName());
			pstm.setString(2, dto.getPhone());
			pstm.setDate(3, (Date)dto.getBirth());
			//sql 문 실행하고 변화된(추가, 수정, 삭제) row의 갯수를 리턴받기
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
		// 변화된 rowCount 값을 확인해서 작업의 성공 여부를 리턴해 준다.
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
	}
	
	// 친구 한명의 정보를 수정하고 작업의 성공 여부를 리턴해주는 메소드
	public boolean update(FriendsDto dto) {
		Connection conn = null;
		PreparedStatement pstm = null;
		// 수정된 row의 갯수를 담을 지역변수를 미리 만들고 초기값 0 대입하기
		int rowCount = 0;
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DBConnect().getConn();
			// 실행할 sql문
			String sql = "UPDATE friend"
					+ " SET name = ?, phone = ?, birth = ?"
					+ " WHERE num = ?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstm = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstm.setString(1, dto.getName());
			pstm.setString(2, dto.getPhone());
			pstm.setDate(3, (Date)dto.getBirth());
			pstm.setInt(4, dto.getNum());
			//sql 문 실행하고 변화된(추가, 수정, 삭제) row의 갯수를 리턴받기
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
		// 변화된 rowCount 값을 확인해서 작업의 성공 여부를 리턴해 준다.
		if(rowCount > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	// 친구 한명의 정보를 삭제하고 작업의 성공 여부를 리턴해주는 메소드
	public boolean delete(int num) {
		Connection conn = null;
		PreparedStatement pstm = null;
		// 수정된 row의 갯수를 담을 지역변수를 미리 만들고 초기값 0 대입하기
		int rowCount = 0;
		
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DBConnect().getConn();
			// 실행할 sql문
			String sql = "DELETE FROM friend"
					+ " WHERE num = ?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstm = conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstm.setInt(1, num);
			//sql 문 실행하고 변화된(추가, 수정, 삭제) row의 갯수를 리턴받기
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
		// 변화된 rowCount 값을 확인해서 작업의 성공 여부를 리턴해 준다.
		if(rowCount > 0 ) {
			return true;
		}else {
			return false;
		}
	}
	
	// 전체 친구의 목록을 리턴해주는 메소드
	public List<FriendsDto> getList(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		// 회원 전체 목록을 담을 ArrayList 객체를 생성해서 참조값을 List 인터페이스 type으로 담기			
		List<FriendsDto> list = new ArrayList<>();
		try {
			//Connection 객체의 참조값 얻어오기
			conn = new DBConnect().getConn();
			// 실행할 sql문
			String sql = "SELECT *"
					+ " FROM friend"
					+ " ORDER BY num ASC" ;
			//PreparedStatement 객체의 참조값 얻어오기
			pstm = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			rs = pstm.executeQuery();
			
			while(rs.next()){
				// 커서가 위치한 곳의 회원정보를 FriendsDto 객체에 담기
				FriendsDto dto = new FriendsDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setBirth(rs.getDate("birth"));
				// 친구 한명의 정보가 담기 FriendsDto 객체의 참조값을 ArrayList 객체에 누적 시키기
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
		// 모든 회원의 정보가 누적된 ArrayList 객체의 참조값 리턴해주기.
		return list;	 
	}
	
	// 인자로 전달된 번호에 해당하는 친구 한명의 정보를 리턴하는 메소드
	// 번호에 해당하는 친구의 정보가 없으면 null을 리턴
	public FriendsDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		// FriendsDto 객체의 참조값을 담을 지역변수 미리 만들기
		FriendsDto dto = null;
		
		try {
			// Connection 객체의 참조값 얻어오기
			conn = new DBConnect().getConn();
			// 실행할 sql문
			String sql = "SELECT *"
					+ " FROM friend"
					+ " WHERE num = ?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstm = conn.prepareStatement(sql);
			// ? 에 값 바인딩
			pstm.setInt(1, num);
			rs = pstm.executeQuery();
			// 만일 cursor를 한 칸 내릴 수 있다면(select 된 row가 있다면)
			if(rs.next()) {
				// MemberDto 객체를 생성해서 참조값을 미리 만들어진 지역변수 dto에 담기
				dto = new FriendsDto();
				// cursor가 위치한 곳의 친구정보를 FriendsDto 객체에 담기
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
		// 친구 한명의 정보를 담고 있는 MemberDto 객체 리턴해주기
		return dto;
	}
}

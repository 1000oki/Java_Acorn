package test.dto;

import java.sql.Date;

public class FriendsDto {
	private int num;
	private String name;
	private String phone;
	private Date birth;

	public FriendsDto() {
	}
	

	public FriendsDto(int num, String name, String phone, Date birth) {
		super();
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth2) {
		this.birth = birth2;
	}

}

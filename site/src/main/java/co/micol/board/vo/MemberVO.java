package co.micol.board.vo;

import java.sql.Date;

public class MemberVO {
	private String id;
	private String name;
	private String password;
	private String address;
	private String tel;
	private Date enterdate;
	private String author;
	private String img;
	//페이징 연습
	private Integer first;
	private Integer last;

	public MemberVO() {
		this.id= id;
		this.password=password;
	}

	
	public MemberVO(String id, String name, String password, String address, String tel, Date enterdate,
			String author, String img) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.tel = tel;
		this.enterdate = enterdate;
		this.author = author;
		this.img = img;
	}

	
	public Integer getFirst() {
		return first;
	}


	public void setFirst(Integer first) {
		this.first = first;
	}


	public Integer getLast() {
		return last;
	}


	public void setLast(Integer last) {
		this.last = last;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
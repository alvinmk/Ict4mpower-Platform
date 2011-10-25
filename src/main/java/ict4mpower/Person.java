package ict4mpower;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {
	private static final long serialVersionUID = -231116576640239466L;
	
	private static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private static Person instance;
	
	public static Person getPerson() {
		if(instance == null) {
			try {
				instance = new Person(1, df.parse("01/06/2011"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	private long id;
	private Date birth;
	
	public Person(long id, Date birth) {
		this.id = id;
		this.birth = birth;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
}

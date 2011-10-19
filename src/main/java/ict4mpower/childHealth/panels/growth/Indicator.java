package ict4mpower.childHealth.panels.growth;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.wicket.Component;

public class Indicator implements Serializable {
	private static final long serialVersionUID = -9155283621010002742L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private int age;
	private float headCircumference;
	private float height;
	private float weight;
	private Date date;
	private Component parent;
	
	private static HashMap<Integer, Float> normal_headCircumference = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> normal_height = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> normal_weight = new HashMap<Integer, Float>();
	
	static {
		normal_headCircumference.put(1, 37f);
		normal_headCircumference.put(2, 38f);
		normal_headCircumference.put(3, 40f);
		normal_headCircumference.put(4, 41f);
		normal_headCircumference.put(5, 45f);
		
		normal_height.put(1, 51f);
		normal_height.put(2, 56f);
		normal_height.put(3, 60f);
		normal_height.put(4, 62f);
		normal_height.put(5, 67f);
		
		normal_weight.put(1, 4.1f);
		normal_weight.put(2, 5f);
		normal_weight.put(3, 6f);
		normal_weight.put(4, 6.2f);
		normal_weight.put(5, 7f);
	}
	
	public Indicator(int age, float headCircumference, float height, float weight, Date date, Component parent) {
		this.age = age;
		this.headCircumference = headCircumference;
		this.height = height;
		this.weight = weight;
		this.date = date;
		this.parent = parent;
	}
	
	public String getAge() {
		// TODO Calculate age from date and birth date
		return age+" months";
	}
	
	public String getHeadCircumference() {
		return headCircumference+" "+parent.getString("cm");
	}
	
	public String getHeight() {
		return height+" "+parent.getString("cm");
	}
	
	public String getWeight() {
		return weight+" "+parent.getString("kg");
	}
	
	public String getDate() {
		return df.format(date);
	}
	
	public float getBmi() {
		return Math.round((this.weight/(Math.pow(this.height/100, 2)))*10)/10f;
	}
	
	public float getNormalHeadCircumference() {
		return normal_headCircumference.get(age);
	}
	
	public float getNormalHeight() {
		return normal_height.get(age);
	}
	
	public float getNormalWeight() {
		return normal_weight.get(age);
	}
	
	public float getNormalBmi() {
		return Math.round((normal_weight.get(age)/Math.pow(normal_height.get(age)/100, 2))*10)/10f;
	}

	public float getHeadCircumferenceValue() {
		return headCircumference;
	}

	public float getHeightValue() {
		return height;
	}

	public float getWeightValue() {
		return weight;
	}
}

package ict4mpower.childHealth.panels.growth;

import ict4mpower.Person;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

public class Indicator implements Serializable, Comparable<Indicator> {
	private static final long serialVersionUID = -9155283621010002742L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	private Person person;
	private float headCircumference;
	private float length;
	private float weight;
	private Date date;
	private Component parent;
	
	private static HashMap<String, Float> normal_headCircumference = new HashMap<String, Float>();
	private static HashMap<String, Float> normal_length = new HashMap<String, Float>();
	private static HashMap<String, Float> normal_weight = new HashMap<String, Float>();
	
	static { // TODO Get values from db - different values for boys/girls
		normal_headCircumference.put("0 weeks", 34.5f);
		normal_headCircumference.put("1 weeks", 35.2f);
		normal_headCircumference.put("2 weeks", 35.9f);
		normal_headCircumference.put("3 weeks", 36.5f);
		normal_headCircumference.put("4 weeks", 37.1f);
		normal_headCircumference.put("5 weeks", 37.6f);
		normal_headCircumference.put("6 weeks", 38.1f);
		normal_headCircumference.put("7 weeks", 38.5f);
		normal_headCircumference.put("8 weeks", 38.9f);
		normal_headCircumference.put("9 weeks", 39.2f);
		normal_headCircumference.put("10 weeks", 39.6f);
		normal_headCircumference.put("11 weeks", 39.9f);
		normal_headCircumference.put("12 weeks", 40.2f);
		normal_headCircumference.put("13 weeks", 40.5f);
		normal_headCircumference.put("3 months", 40.5f);
		normal_headCircumference.put("4 months", 41.6f);
		normal_headCircumference.put("5 months", 42.6f);
		normal_headCircumference.put("6 months", 43.3f);
		normal_headCircumference.put("7 months", 44.0f);
		
		normal_length.put("0 weeks", 49.9f);
		normal_length.put("1 weeks", 51.1f);
		normal_length.put("2 weeks", 52.3f);
		normal_length.put("3 weeks", 53.4f);
		normal_length.put("4 weeks", 54.4f);
		normal_length.put("5 weeks", 55.3f);
		normal_length.put("6 weeks", 56.2f);
		normal_length.put("7 weeks", 57.1f);
		normal_length.put("8 weeks", 57.9f);
		normal_length.put("9 weeks", 58.7f);
		normal_length.put("10 weeks", 59.4f);
		normal_length.put("11 weeks", 60.1f);
		normal_length.put("12 weeks", 60.8f);
		normal_length.put("13 weeks", 61.4f);
		normal_length.put("3 months", 61.4f);
		normal_length.put("4 months", 63.9f);
		normal_length.put("5 months", 65.9f);
		normal_length.put("6 months", 67.6f);
		normal_length.put("7 months", 69.2f);
		
		normal_weight.put("0 weeks", 3.3f);
		normal_weight.put("1 weeks", 3.5f);
		normal_weight.put("2 weeks", 3.8f);
		normal_weight.put("3 weeks", 4.1f);
		normal_weight.put("4 weeks", 4.4f);
		normal_weight.put("5 weeks", 4.7f);
		normal_weight.put("6 weeks", 4.9f);
		normal_weight.put("7 weeks", 5.2f);
		normal_weight.put("8 weeks", 5.4f);
		normal_weight.put("9 weeks", 5.6f);
		normal_weight.put("10 weeks", 5.8f);
		normal_weight.put("11 weeks", 6.0f);
		normal_weight.put("12 weeks", 6.2f);
		normal_weight.put("13 weeks", 6.4f);
		normal_weight.put("3 months", 6.4f);
		normal_weight.put("4 months", 7.0f);
		normal_weight.put("5 months", 7.5f);
		normal_weight.put("6 months", 7.9f);
		normal_weight.put("7 months", 8.3f);
	}
	
	public Indicator(Person p, float headCircumference, float length, float weight, Date date, Component parent) {
		this.person = p;
		this.headCircumference = headCircumference;
		this.length = length;
		this.weight = weight;
		this.date = date;
		this.parent = parent;
	}
	
	public String getAge() {
		return getAgeValue().getObject();
	}
	
	public StringResourceModel getAgeValue() {
		Object[] arr = getAccurateAgeArray(true);
		if((Integer)arr[2] == 0) return new StringResourceModel((String)arr[0], parent, new Model<Integer>((Integer)arr[1]));
		return new StringResourceModel((String)arr[0]+"_extra_days", parent, new Model<AgeData>(
				new AgeData((Integer)arr[1], (Integer)arr[2])));
	}
	
	private String getAgeKey() {
		Object[] arr = getAccurateAgeArray();
		return (int)Math.floor((Float)arr[1])+" "+arr[0];
	}
	
	public Object[] getAccurateAgeArray() {
		return getAccurateAgeArray(false);
	}
	
	private Object[] getAccurateAgeArray(boolean returnExtraDays) {
		Calendar measured = Calendar.getInstance();
		measured.setTime(date);
		Calendar birth = Calendar.getInstance();
		birth.setTime(person.getBirth());
		int years = measured.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		int months = measured.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
		int days = measured.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH);
		int extraDays = 0;
		
		// Check days
		if(days < 0) {
			months += years*12-1;
		}
		else {
			months += years*12;
		}
		Calendar b = (Calendar) birth.clone();
		b.add(Calendar.MONTH, months);
		extraDays = measured.get(Calendar.DAY_OF_MONTH) - b.get(Calendar.DAY_OF_MONTH);
		if(extraDays<0) {
			extraDays += b.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		System.out.println("extra days "+extraDays);
		System.out.println("months "+months);
		if(months < 3) {
			// Less than 3 months old
			int weeks = measured.get(Calendar.WEEK_OF_YEAR) - birth.get(Calendar.WEEK_OF_YEAR);
			if(weeks<0) {
				weeks += birth.getActualMaximum(Calendar.WEEK_OF_YEAR);
			}
			b = (Calendar) birth.clone();
			b.add(Calendar.WEEK_OF_YEAR, weeks);
			extraDays = measured.get(Calendar.DAY_OF_WEEK) - b.get(Calendar.DAY_OF_WEEK);
			if(extraDays<0) {
				extraDays += 7;
				weeks -= 1;
			}
			System.out.println("extra days "+extraDays);
			System.out.println("weeks "+weeks);
			if(returnExtraDays) return new Object[]{"weeks", weeks, extraDays};
			return new Object[]{"weeks", weeks+extraDays/7f};
		}
		else {
			// More than or equal to 3 months old, use months
			if(returnExtraDays) return new Object[]{"months", months, extraDays};
			return new Object[]{"months", months+extraDays/(float)measured.getActualMaximum(Calendar.DAY_OF_MONTH)};
		}
	}
	
	public String getHeadCircumference() {
		return headCircumference+" "+parent.getString("cm");
	}
	
	public String getLength() {
		return length+" "+parent.getString("cm");
	}
	
	public String getWeight() {
		return weight+" "+parent.getString("kg");
	}
	
	public String getDate() {
		return df.format(date);
	}
	
	public Date getDateValue() {
		return date;
	}
	
	public float getBmi() {
		return Math.round((this.weight/(Math.pow(this.length/100, 2)))*10)/10f;
	}
	
	public float getNormalHeadCircumference() {
		System.out.println("Normal_head_circumference, age key "+getAgeKey());
		return normal_headCircumference.get(getAgeKey());
	}
	
	public float getNormalLength() {
		return normal_length.get(getAgeKey());
	}
	
	public float getNormalWeight() {
		return normal_weight.get(getAgeKey());
	}
	
	public float getNormalBmi() {
		return Math.round((normal_weight.get(getAgeKey())/Math.pow(normal_length.get(getAgeKey())/100, 2))*10)/10f;
	}

	public float getHeadCircumferenceValue() {
		return headCircumference;
	}

	public float getLengthValue() {
		return length;
	}

	public float getWeightValue() {
		return weight;
	}

	public int compareTo(Indicator other) {
		return date.compareTo(other.date);
	}
}

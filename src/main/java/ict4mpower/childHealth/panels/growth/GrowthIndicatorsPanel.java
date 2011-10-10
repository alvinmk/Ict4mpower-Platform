package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class GrowthIndicatorsPanel extends DivisionPanel {
	private static final long serialVersionUID = 8147585043264253460L;
	
	private DateFormat df = new SimpleDateFormat("d/M/y");

	public GrowthIndicatorsPanel(String id) {
		super(id, "title");
		
		//TODO Temporary
		List<Indicator> indicators = null;
		try {
			indicators = Arrays.asList(new Indicator[]{
					new Indicator(1, 36, 51, 4.2f, df.parse("01/01/10")),
					new Indicator(2, 37, 54, 5.2f, df.parse("01/02/10")),
					new Indicator(3, 38, 59, 5.3f, df.parse("01/03/10")),
					new Indicator(4, 40, 62, 6f, df.parse("01/04/10"))
			});
		} catch(Exception e) {
			//
		}
			
		add(new ListView<Indicator>("indicators", indicators) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Indicator> item) {
				Indicator indi = item.getModelObject();
				item.add(new IndicatorPanel("rowPanel", indi));
			}
		});
		
		// Add form
		GrowthIndicatorsForm form = new GrowthIndicatorsForm("form");
		add(form);
		
		setForm(form, this.getClass().getName()+"Frame");
	}
	
	private class GrowthIndicatorsForm extends SavingForm {
		private static final long serialVersionUID = 1858236985096796569L;

		public GrowthIndicatorsForm(String id) {
			super(id, GrowthIndicatorsForm.class.getName());
			
			GrowthIndicatorsData data = new GrowthIndicatorsData();
			
			// Input fields for todays values
			add(new TextField<Float>("head", new PropertyModel<Float>(data, "head"), Float.class));
			add(new TextField<Float>("height", new PropertyModel<Float>(data, "height"), Float.class));
			add(new TextField<Float>("weight", new PropertyModel<Float>(data, "weight"), Float.class));
		}
	}
}

class Indicator implements Serializable {
	private static final long serialVersionUID = -9155283621010002742L;
	
	public int age;
	public float headCircumference;
	public float height;
	public float weight;
	public Date date;
	
	private static HashMap<Integer, Float> normal_headCircumference = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> normal_height = new HashMap<Integer, Float>();
	private static HashMap<Integer, Float> normal_weight = new HashMap<Integer, Float>();
	
	static {
		normal_headCircumference.put(1, 37f);
		normal_headCircumference.put(2, 38f);
		normal_headCircumference.put(3, 40f);
		normal_headCircumference.put(4, 41f);
		
		normal_height.put(1, 51f);
		normal_height.put(2, 56f);
		normal_height.put(3, 60f);
		normal_height.put(4, 62f);
		
		normal_weight.put(1, 4.1f);
		normal_weight.put(2, 5f);
		normal_weight.put(3, 6f);
		normal_weight.put(4, 6.2f);
	}
	
	public Indicator(int age, float headCircumference, float height, float weight, Date date) {
		this.age = age;
		this.headCircumference = headCircumference;
		this.height = height;
		this.weight = weight;
		this.date = date;
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
}

class IndicatorPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;

	public IndicatorPanel(String id, Indicator indicator) {
		super(id);
		
		// Add child's values
		add(new Label("age", new Model<String>(indicator.age+" months")));
		Label head = new Label("headCircumference", new Model<String>(indicator.headCircumference+" cm"));
		//setClass(head, indicator.headCircumference, indicator.getNormalHeadCircumference());
		add(head);
		Label height = new Label("height", new Model<String>(indicator.height+" cm"));
		//setClass(height, indicator.height, indicator.getNormalHeight());
		add(height);
		Label weight = new Label("weight", new Model<String>(indicator.weight+" kg"));
		//setClass(weight, indicator.weight, indicator.getNormalWeight());
		add(weight);
		Label bmi = new Label("bmi", new Model<Float>(indicator.getBmi()));
		//setClass(bmi, indicator.getBmi(), indicator.getNormalBmi());
		add(bmi);
		add(new Label("date", new Model<Date>(indicator.date)));
		
		// Add normal values
		add(new Label("headCircumference_normal", new Model<String>(indicator.getNormalHeadCircumference()+" cm")));
		add(new Label("height_normal", new Model<String>(indicator.getNormalHeight()+" cm")));
		add(new Label("weight_normal", new Model<String>(indicator.getNormalWeight()+" kg")));
		add(new Label("bmi_normal", new Model<Float>(indicator.getNormalBmi())));
	}

//	private void setClass(Label label, float value, float normal) {
//		if(value > normal*1.2f
//				|| value < normal*0.8f) {
//			label.add(new SimpleAttributeModifier("class", "critical_value"));
//		}
//		else if(value > normal*1.1f
//				|| value < normal*0.9f) {
//			label.add(new SimpleAttributeModifier("class", "low_value"));
//		}
//		else {
//			label.add(new SimpleAttributeModifier("class", "good_value"));
//		}
//	}
}
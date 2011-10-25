package ict4mpower.childHealth.panels.growth;

import ict4mpower.Person;
import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.ValidationClassBehavior;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class GrowthIndicatorsPanel extends DivisionPanel {
	private static final long serialVersionUID = 8147585043264253460L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private ListView<Indicator> list;
	private TodayPanel today;

	public GrowthIndicatorsPanel(String id) {
		super(id, "title", false);
		
		setOutputMarkupId(true);
		
		// Add form
		add(new GrowthIndicatorsForm("form"));
	}
	
	private class GrowthIndicatorsForm extends SavingForm {
		private static final long serialVersionUID = 1858236985096796569L;

		public GrowthIndicatorsForm(String id) {
			super(id);

			//TODO Temporary
			Person p = Person.getPerson();
			List<Indicator> indicators = new ArrayList<Indicator>();
			try {
				indicators.add(new Indicator(p, 37.2f, 54.5f, 4.5f, df.parse("05/07/2011"), this));
				indicators.add(new Indicator(p, 39, 57.5f, 5.5f, df.parse("01/08/2011"), this));
				indicators.add(new Indicator(p, 40, 60, 5.8f, df.parse("28/08/2011"), this));
				indicators.add(new Indicator(p, 42, 64, 7f, df.parse("12/10/2011"), this));
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			GrowthData data = GrowthData.instance();
			// TODO Temporary
			if(data.getIndicators() == null) {
				data.setIndicators(indicators);
			}
			
			list = new ListView<Indicator>("indicators", new PropertyModel<List<Indicator>>(data, "indicators")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Indicator> item) {
					item.add(new IndicatorPanel("rowPanel", item.getModel()));
				}
			};
			list.setOutputMarkupId(true);
			add(list);
			
			today = new TodayPanel("today", this, GrowthIndicatorsPanel.this);
			add(today, false);
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			System.out.println("head: "+today.getHeadField().getConvertedInput()
					+" length: "+today.getLengthField().getConvertedInput()
					+" weight: "+today.getWeightField().getConvertedInput());
			Person p = Person.getPerson();
			List<Indicator> l = list.getModelObject();
			l.add(new Indicator(p,
					today.getHeadField().getConvertedInput(),
					today.getLengthField().getConvertedInput(),
					today.getWeightField().getConvertedInput(),
					new Date(),
					GrowthIndicatorsPanel.this));
		}
	}
}

class TodayPanel extends DivisionPanel {
	private static final long serialVersionUID = -5139751028526033215L;
	
	private TextField<Float> headField;
	private TextField<Float> lengthField;
	private TextField<Float> weightField;

	public TodayPanel(String id, SavingForm form, DivisionPanel panel) {
		super(id, "today_title");
		
		setForm(form, panel);

		// Input fields for todays values
		headField = new TextField<Float>("head", new Model<Float>(), Float.class);
		headField.setRequired(true);
		headField.add(new ValidationClassBehavior());
		add(headField);
		lengthField = new TextField<Float>("length", new Model<Float>(), Float.class);
		lengthField.setRequired(true);
		lengthField.add(new ValidationClassBehavior());
		add(lengthField);
		weightField = new TextField<Float>("weight", new Model<Float>(), Float.class);
		weightField.setRequired(true);
		weightField.add(new ValidationClassBehavior());
		add(weightField);
	}

	public TextField<Float> getHeadField() {
		return headField;
	}

	public FormComponent<Float> getLengthField() {
		return lengthField;
	}
	
	public FormComponent<Float> getWeightField() {
		return weightField;
	}
}

class IndicatorPanel extends Panel {
	private static final long serialVersionUID = 3885944574671683382L;

	public IndicatorPanel(String id, IModel<Indicator> indicator) {
		super(id);
		
		// Add child's values
		add(new Label("age", new PropertyModel<String>(indicator, "age")));
		Label head = new Label("headCircumference", new PropertyModel<String>(indicator, "headCircumference"));
		//setClass(head, indicator.headCircumference, indicator.getNormalHeadCircumference());
		add(head);
		Label length = new Label("length", new PropertyModel<String>(indicator, "length"));
		//setClass(length, indicator.length, indicator.getNormallength());
		add(length);
		Label weight = new Label("weight", new PropertyModel<String>(indicator, "weight"));
		//setClass(weight, indicator.weight, indicator.getNormalWeight());
		add(weight);
		Label bmi = new Label("bmi", new PropertyModel<Float>(indicator, "bmi"));
		//setClass(bmi, indicator.getBmi(), indicator.getNormalBmi());
		add(bmi);
		add(new Label("date", new PropertyModel<String>(indicator, "date")));
		
		// Add normal values
		add(new Label("headCircumference_normal", new PropertyModel<String>(indicator, "normalHeadCircumference")));
		add(new Label("length_normal", new PropertyModel<String>(indicator, "normalLength")));
		add(new Label("weight_normal", new PropertyModel<String>(indicator, "normalWeight")));
		add(new Label("bmi_normal", new PropertyModel<Float>(indicator, "normalBmi")));
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
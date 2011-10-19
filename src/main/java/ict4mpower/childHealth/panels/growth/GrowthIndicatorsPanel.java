package ict4mpower.childHealth.panels.growth;

import ict4mpower.childHealth.SavingForm;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class GrowthIndicatorsPanel extends DivisionPanel {
	private static final long serialVersionUID = 8147585043264253460L;
	
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private ListView<Indicator> list;

	public GrowthIndicatorsPanel(String id) {
		super(id, "title");
		
		setOutputMarkupId(true);
		
		// Add form
		final GrowthIndicatorsForm form = new GrowthIndicatorsForm("form");
		form.add(new AjaxFormSubmitBehavior("onsubmit") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target) {}

			@SuppressWarnings("unchecked")
			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				//TODO Temporary info on age
				System.out.println("head: "+((TextField<Float>)form.getSaveList().get(1)).getConvertedInput()
						+" height: "+((TextField<Float>)form.getSaveList().get(2)).getConvertedInput()
						+" weight: "+((TextField<Float>)form.getSaveList().get(3)).getConvertedInput());
				list.getModelObject().add(new Indicator(5,
						((TextField<Float>)form.getSaveList().get(1)).getConvertedInput(),
						((TextField<Float>)form.getSaveList().get(2)).getConvertedInput(),
						((TextField<Float>)form.getSaveList().get(3)).getConvertedInput(),
						new Date(),
						GrowthIndicatorsPanel.this));
				target.add(GrowthIndicatorsPanel.this, GrowthIndicatorsPanel.class.getName().substring(
						GrowthIndicatorsPanel.class.getName().lastIndexOf('.')+1)+"Frame");
			}
		});
		add(form);
		
		setForm(form, this);
	}
	
	private class GrowthIndicatorsForm extends SavingForm {
		private static final long serialVersionUID = 1858236985096796569L;

		public GrowthIndicatorsForm(String id) {
			super(id);

			//TODO Temporary
			List<Indicator> indicators = new ArrayList<Indicator>();
			try {
				indicators.add(new Indicator(1, 36, 51, 4.2f, df.parse("01/01/2011"), this));
				indicators.add(new Indicator(2, 37, 54, 5.2f, df.parse("01/02/2011"), this));
				indicators.add(new Indicator(3, 38, 59, 5.3f, df.parse("01/03/2011"), this));
				indicators.add(new Indicator(4, 40, 62, 6f, df.parse("01/04/2011"), this));
			} catch(Exception e) {
				e.printStackTrace();
				//
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
			
			// Input fields for todays values
			add(new TextField<Float>("head", new PropertyModel<Float>(data, "head")));
			add(new TextField<Float>("height", new PropertyModel<Float>(data, "height")));
			add(new TextField<Float>("weight", new PropertyModel<Float>(data, "weight")));
		}
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
		Label height = new Label("height", new PropertyModel<String>(indicator, "height"));
		//setClass(height, indicator.height, indicator.getNormalHeight());
		add(height);
		Label weight = new Label("weight", new PropertyModel<String>(indicator, "weight"));
		//setClass(weight, indicator.weight, indicator.getNormalWeight());
		add(weight);
		Label bmi = new Label("bmi", new PropertyModel<Float>(indicator, "bmi"));
		//setClass(bmi, indicator.getBmi(), indicator.getNormalBmi());
		add(bmi);
		add(new Label("date", new PropertyModel<String>(indicator, "date")));
		
		// Add normal values
		add(new Label("headCircumference_normal", new PropertyModel<String>(indicator, "normalHeadCircumference")));
		add(new Label("height_normal", new PropertyModel<String>(indicator, "normalHeight")));
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
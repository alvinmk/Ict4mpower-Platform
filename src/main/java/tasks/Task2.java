package tasks;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

public class Task2 extends Task{
	private static final long serialVersionUID = -8880286421735956692L;
	
	public Task2(String name) {
		super(name);		
		Form form = new Form("form") {
	        protected void onSubmit() {}
	    };
	    add(form);
		
		form.add( new Button("button2", new Model<String>("English")){
			 static final long serialVersionUID = 1L;
				@Override
				public void onSubmit() {					
		        	getSession().setLocale(new Locale("en"));		 
		        	info("EN LOCALE");		        	
		        }
		 });
	}
	
	

}


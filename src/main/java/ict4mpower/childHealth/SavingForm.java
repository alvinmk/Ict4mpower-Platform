package ict4mpower.childHealth;

import ict4mpower.AppSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class SavingForm extends Form<ValueMap> {
	private static final long serialVersionUID = 8931477114572051143L;
	
	private List<Component> saveList = new ArrayList<Component>();
	private List<FormComponent<?>> validateList = new ArrayList<FormComponent<?>>();
	
	public SavingForm(String id) {
		super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
	}
	
	public MarkupContainer add(Component child) {
		return add(child, true, null);
	}
	
	public MarkupContainer add(Component child, String id) {
		return add(child, true, id);
	}
	
	public MarkupContainer add(Component child, boolean save) {
		return add(child, save, null);
	}
	
	@SuppressWarnings("rawtypes")
	public MarkupContainer add(Component child, boolean save, String id) {
		addFormComponents(child);
		if(save) {
			// Check to see if there is a value for this component in the session store
			System.out.println("Adding "+getSaveName()+" value: "+getSession().getAttribute(getSaveName()));
			if(getSession().getAttribute(getSaveName()) != null) {
				// Set value for component
				if(id == null) {
					child.setDefaultModel(new PropertyModel(getSession().getAttribute(getSaveName()), child.getId()));
				}
				else {
					System.out.println("data saved "+getSession().getAttribute(getSaveName()));
					child.setDefaultModel(new PropertyModel(getSession().getAttribute(getSaveName()), id));
				}
			}
			else {
				// TODO Build session value from database
			}
			// Add to save list
			System.out.println("Adding to saveList: "+getSaveName());
			saveList.add(child);
		}
		return super.add(child);
	}
	
	private void addFormComponents(Component c) {
		if(c instanceof FormComponent) {
			validateList.add((FormComponent<?>) c);
		}
		else if(c instanceof Panel) {
			for(int i=0; i<((Panel)c).size(); i++) {
				addFormComponents(((Panel)c).get(i));
			}
		}
	}
	
	private String getSaveName() {
		return ((AppSession)getSession()).getGoal()+":"+((AppSession)getSession()).getTask();
	}
	
	public List<Component> getSaveList() {
		return saveList;
	}
	
	@Override
	protected void onSubmit() {
		for(Component c : saveList) {
			String id = getSaveName();
			System.out.println("name: "+id.substring(id.lastIndexOf('.')+1)+" component: "+c.getId());
			// Get the model target
			Serializable val = (Serializable) ((PropertyModel<?>)c.getDefaultModel()).getTarget();
			System.out.println("Saving in session: "+id+" with value: "+val);
			getSession().setAttribute(id, val);
		}
	}

	public List<FormComponent<?>> getValidateList() {
		return validateList;
	}
}

package ict4mpower.childHealth;

import ict4mpower.AppSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

public class SavingForm extends Form<ValueMap> {
	private static final long serialVersionUID = 8931477114572051143L;
	
	private List<Component> saveList = new ArrayList<Component>();
	
	public SavingForm(String id) {
		super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
	}
	
	public MarkupContainer add(Component child) {
		return add(child, true);
	}
	
	@SuppressWarnings("rawtypes")
	public MarkupContainer add(Component child, boolean save) {
		if(save) {
			// Check to see if there is a value for this component in the session store
			System.out.println("Adding "+getSaveName(child)+" value: "+getSession().getAttribute(getSaveName(child)));
			if(getSession().getAttribute(getSaveName(child)) != null) {
				// Set value for component
				child.setDefaultModel(new PropertyModel(getSession().getAttribute(getSaveName(child)), child.getId()));
			}
			else {
				// TODO Build session value from database
			}
			// Add to save list
			System.out.println("Adding to saveList: "+getSaveName(child));
			saveList.add(child);
		}
		return super.add(child);
	}
	
	private String getSaveName(Component child) {
		return ((AppSession)getSession()).getGoal()+":"+((AppSession)getSession()).getTask();
				//saveNamePath+"."+child.getId();
	}
	
	public List<Component> getSaveList() {
		return saveList;
	}
	
	@Override
	protected void onSubmit() {
		for(Component c : saveList) {
			String id = getSaveName(c);
			System.out.println("name: "+id.substring(id.lastIndexOf('.')+1));
			// Get the model target
			Serializable val = (Serializable) ((PropertyModel<?>)c.getDefaultModel()).getTarget();
			System.out.println("Saving in session: "+id+" with value: "+val);
			getSession().setAttribute(id, val);
		}
	}
}

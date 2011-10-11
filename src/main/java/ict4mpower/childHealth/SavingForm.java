package ict4mpower.childHealth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;

public class SavingForm extends Form<ValueMap> {
	private static final long serialVersionUID = 8931477114572051143L;
	
	private List<Component> saveList = new ArrayList<Component>();
	private String saveNamePath;
	
	public SavingForm(String id, String saveNamePath) {
		super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
		
		this.saveNamePath = saveNamePath;
	}
	
	@Override
	public MarkupContainer add(Component...children) {
		for(Component child : children) {
			// Check to see if there is a value for this component in the session store
			System.out.println("Adding "+getSaveName(child)+" value: "+getSession().getAttribute(getSaveName(child)));
			if(getSession().getAttribute(getSaveName(child)) != null) {
				// Set value for component
				child.setDefaultModelObject(((IModel<?>)getSession().getAttribute(getSaveName(child))).getObject());
			}
			// Add to save list
			System.out.println("Adding to saveList: "+getSaveName(child));
			saveList.add(child);
		}
		
		return super.add(children);
	}
	
	private String getSaveName(Component child) {
		return saveNamePath+"."+child.getId();
	}
	
	public List<Component> getSaveList() {
		return saveList;
	}
	
	@Override
	protected void onSubmit() {
		for(Component c : saveList) {
			String id = getSaveName(c);
			System.out.println("name: "+id.substring(id.lastIndexOf('.')+1));
			// Get the model
			Serializable val = c.getDefaultModel();
			System.out.println("Saving in session: "+id+" with value: "+val);
			getSession().setAttribute(id, val);
		}
	}
}

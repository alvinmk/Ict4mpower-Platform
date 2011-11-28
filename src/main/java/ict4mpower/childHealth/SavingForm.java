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

/**
 * A base form class that saves component data in the session
 * and retrieves it if available
 * @author Joakim Lindskog
 *
 */
public class SavingForm extends Form<ValueMap> {
	private static final long serialVersionUID = 8931477114572051143L;
	
	private List<Component> saveList = new ArrayList<Component>();
	
	/**
	 * Constructor
	 * @param id component id
	 */
	public SavingForm(String id) {
		super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
	}
	
	/**
	 * Adds a child to this form
	 * @param child child component
	 * @return this
	 */
	public MarkupContainer add(Component child) {
		return add(child, true, null);
	}
	
	/**
	 * Adds a child to this form
	 * @param child child component
	 * @param id id to use
	 * @return this
	 */
	public MarkupContainer add(Component child, String id) {
		return add(child, true, id);
	}
	
	/**
	 * Adds a child to this form
	 * @param child child component
	 * @param save whether to save this component or not
	 * @return this
	 */
	public MarkupContainer add(Component child, boolean save) {
		return add(child, save, null);
	}
	
	/**
	 * Adds a child to this form
	 * @param child child component
	 * @param save whether to save this component or not
	 * @param id the id to use
	 * @return this
	 */
	@SuppressWarnings("rawtypes")
	public MarkupContainer add(Component child, boolean save, String id) {
		if(save) {
			// Check to see if there is a value for this component in the session store
			if(getSession().getAttribute(getSaveName()) != null) {
				// Set value for component
				if(id == null) {
					child.setDefaultModel(new PropertyModel(getSession().getAttribute(getSaveName()), child.getId()));
				}
				else { // Use specified id value
					child.setDefaultModel(new PropertyModel(getSession().getAttribute(getSaveName()), id));
				}
			}
			// Add to save list
			saveList.add(child);
		}
		return super.add(child);
	}
	
	/**
	 * Gets the id to use when saving data for this task in the session
	 * @return the save name/id
	 */
	private String getSaveName() {
		return ((AppSession)getSession()).getGoal()+":"+((AppSession)getSession()).getTask();
	}
	
	/**
	 * Gets the list of saved components
	 * @return list of saved components
	 */
	public List<Component> getSaveList() {
		return saveList;
	}
	
	@Override
	protected void onSubmit() {
		for(Component c : saveList) {
			String id = getSaveName();
			// Get the model target
			Serializable val = (Serializable) ((PropertyModel<?>)c.getDefaultModel()).getTarget();
			// Save in session
			getSession().setAttribute(id, val);
		}
	}
}

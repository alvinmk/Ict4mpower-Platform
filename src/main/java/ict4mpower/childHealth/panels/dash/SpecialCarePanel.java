package ict4mpower.childHealth.panels.dash;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.data.AdditionalData;
import ict4mpower.childHealth.panels.DivisionPanel;

/**
 * Panel for showing the reasons for special care
 * @author Joakim Lindskog
 *
 */
public class SpecialCarePanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218871548889654L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public SpecialCarePanel(String id) {
		super(id, "title", false);
		
		// Reasons text
		// Fetch info for special care reasons
		AdditionalData data = AdditionalData.instance();
		if(data.getReasons().isEmpty()) {
			Date max = null;
			try {
				max = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1800");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AdditionalData ad = null;
			// Get from db
			Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
			System.out.println("set "+set.size());
			for(Object o : set) {
				System.out.println("obj "+o.getClass());
				if(o instanceof AdditionalData) {
					ad = (AdditionalData) o;
					if(!ad.getReasons().isEmpty() && ad.getDate().after(max)) {
						data.setReasons(ad.getReasons());
						max = ad.getDate();
					}
				}
			}
		}
		
		add(new ListView<String>("reasonsList", new Model<ArrayList<String>>((ArrayList<String>) data.getReasons())) {
			private static final long serialVersionUID = -3943623377097933936L;

			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new SpecialCareRowPanel("rowPanel", item.getModel(), SpecialCarePanel.this));
			}
		});
	}
}

/**
 * Row panel for each line of text (each reason)
 * @author Joakim Lindskog
 *
 */
class SpecialCareRowPanel extends Panel {
	private static final long serialVersionUID = -2836463365050103653L;

	/**
	 * Constructor
	 * @param id component id
	 * @param model model
	 * @param panel parent panel
	 */
	public SpecialCareRowPanel(String id, IModel<String> model, Panel panel) {
		super(id);
		
		add(new Label("text", panel.getString(model.getObject())));
	}
}

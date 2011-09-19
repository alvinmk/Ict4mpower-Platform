package layoutPanels;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;

@AuthorizeInstantiation("ADMIN")
public class ContentPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public ContentPanel(String id) {
		super(id);
	}

}

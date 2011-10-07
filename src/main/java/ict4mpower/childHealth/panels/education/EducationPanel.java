package ict4mpower.childHealth.panels.education;

import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.ui.accordion.AccordionHeader;

public class EducationPanel extends Panel {
	private static final long serialVersionUID = -1172218871548889654L;

	public EducationPanel(String id) {
		super(id);
		
		// Feeding accordion
		Accordion acc = new Accordion("accordionFeeding");
		acc.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(acc);
		
		// Oral meds accordion
		Accordion oral = new Accordion("accordionOralMeds");
		oral.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(oral);
		
		// Infections accordion
		Accordion inf = new Accordion("accordionInfections");
		inf.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(inf);
	}
}

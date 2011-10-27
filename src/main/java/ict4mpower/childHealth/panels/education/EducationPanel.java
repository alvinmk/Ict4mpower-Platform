package ict4mpower.childHealth.panels.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ict4mpower.childHealth.data.CheckableOption;
import ict4mpower.childHealth.data.EducationData;

import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.ui.accordion.AccordionHeader;

public class EducationPanel extends Panel {
	private static final long serialVersionUID = -1172218871548889654L;

	public EducationPanel(String id) {
		super(id);
		
		// TODO Temporary - get from database
		EducationData data = EducationData.instance();
		// Feeding
		List<List<CheckableOption>> feedingOptions = new ArrayList<List<CheckableOption>>();
		feedingOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("f1_option1"),
				new CheckableOption("f1_option2"),
				new CheckableOption("f1_option3")
		}));
		feedingOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("f2_option1"),
				new CheckableOption("f2_option2"),
				new CheckableOption("f2_option3")
		}));
		feedingOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("f3_option1"),
				new CheckableOption("f3_option2"),
				new CheckableOption("f3_option3")
		}));
		feedingOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("f4_option1"),
				new CheckableOption("f4_option2"),
				new CheckableOption("f4_option3")
		}));
		for(int i=0; i<4; i++) {
			if(data.getFeedingOptions().size() <= i || data.getFeedingOptions().get(i) == null) {
				data.setFeedingHeader(i, "feeding"+(i+1));
				data.setFeedingText(i, "f"+(i+1)+"_text");
				data.setFeedingOptions(i, feedingOptions.get(i));
			}
		}
		// Oral medications
		List<List<CheckableOption>> oralOptions = new ArrayList<List<CheckableOption>>();
		oralOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("o1_option1"),
				new CheckableOption("o1_option2"),
				new CheckableOption("o1_option3")
		}));
		oralOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("o2_option1"),
				new CheckableOption("o2_option2"),
				new CheckableOption("o2_option3")
		}));
		oralOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("o3_option1"),
				new CheckableOption("o3_option2"),
				new CheckableOption("o3_option3")
		}));
		oralOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("o4_option1"),
				new CheckableOption("o4_option2"),
				new CheckableOption("o4_option3")
		}));
		for(int i=0; i<4; i++) {
			if(data.getOralOptions().size() <= i || data.getOralOptions().get(i) == null) {
				data.setOralHeader(i, "oral"+(i+1));
				data.setOralText(i, "o"+(i+1)+"_text");
				data.setOralOptions(i, oralOptions.get(i));
			}
		}
		// Infections
		List<List<CheckableOption>> infectionsOptions = new ArrayList<List<CheckableOption>>();
		infectionsOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("i1_option1"),
				new CheckableOption("i1_option2"),
				new CheckableOption("i1_option3")
		}));
		infectionsOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("i2_option1"),
				new CheckableOption("i2_option2"),
				new CheckableOption("i2_option3")
		}));
		infectionsOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("i3_option1"),
				new CheckableOption("i3_option2"),
				new CheckableOption("i3_option3")
		}));
		infectionsOptions.add(Arrays.asList(new CheckableOption[] {
				new CheckableOption("i4_option1"),
				new CheckableOption("i4_option2"),
				new CheckableOption("i4_option3")
		}));
		for(int i=0; i<4; i++) {
			if(data.getInfectionsOptions().size() <= i || data.getInfectionsOptions().get(i) == null) {
				data.setInfectionsHeader(i, "infections"+(i+1));
				data.setInfectionsText(i, "i"+(i+1)+"_text");
				data.setInfectionsOptions(i, infectionsOptions.get(i));
			}
		}
		
		// Feeding accordion
		Accordion acc = new Accordion("accordion");
		acc.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(new AccordionPanel("feeding", "feeding.title", acc, "feedingData"));
		
		// Oral meds accordion
		Accordion oral = new Accordion("accordion");
		oral.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(new AccordionPanel("oral", "oral.title", oral, "oralData"));
		
		// Infections accordion
		Accordion inf = new Accordion("accordion");
		inf.setHeader(new AccordionHeader(new LiteralOption("h3.header")));
		add(new AccordionPanel("infections", "infections.title", inf, "infectionsData"));
	}
}

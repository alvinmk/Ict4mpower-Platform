/*
 *  This file is part of the ICT4MPOWER platform.
 *
 *  The ICT4MPOWER platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  The ICT4MPOWER platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with the ICT4MPOWER platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package ict4mpower.childHealth.panels.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import ict4mpower.childHealth.data.CheckableOption;
import ict4mpower.childHealth.data.EducationData;

import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.ui.accordion.AccordionHeader;

import storage.ApplicationSocketTemp;

/**
 * Panel for the education task
 * @author Joakim Lindskog
 *
 */
public class EducationPanel extends Panel {
	private static final long serialVersionUID = -1172218871548889654L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public EducationPanel(String id) {
		super(id);
		
		// Get checkable options from database
		EducationData data = EducationData.instance();
		Set<Object> set = ApplicationSocketTemp.getApplicationSocketTemp().getData("ChildHealth", "EducationOptions");
		CheckableOption[][][] options = null;
		for(Object o : set) {
			// Get copy of only first object - there should be only one
			CheckableOption[][][] optArr = ((CheckableOption[][][]) o).clone();
			options = new CheckableOption[optArr.length][][];
			for(int i=0; i<options.length; i++) {
				options[i] = new CheckableOption[optArr[i].length][];
				for(int j=0; j<options[i].length; j++) {
					options[i][j] = new CheckableOption[optArr[i][j].length];
					for(int k=0; k<options[i][j].length; k++) {
						options[i][j][k] = optArr[i][j][k].clone();
					}
				}
			}
			break;
		}
		// Feeding
		List<List<CheckableOption>> feedingOptions = new ArrayList<List<CheckableOption>>();
		feedingOptions.add(Arrays.asList(options[0][0]));
		feedingOptions.add(Arrays.asList(options[0][1]));
		feedingOptions.add(Arrays.asList(options[0][2]));
		feedingOptions.add(Arrays.asList(options[0][3]));
		for(int i=0; i<options[0].length; i++) {
			if(data.getFeedingOptions().size() <= i || data.getFeedingOptions().get(i) == null) {
				data.setFeedingHeader(i, "feeding"+(i+1));
				data.setFeedingText(i, "f"+(i+1)+"_text");
				data.setFeedingOptions(i, feedingOptions.get(i));
			}
		}
		// Oral medications
		List<List<CheckableOption>> oralOptions = new ArrayList<List<CheckableOption>>();
		oralOptions.add(Arrays.asList(options[1][0]));
		oralOptions.add(Arrays.asList(options[1][1]));
		oralOptions.add(Arrays.asList(options[1][2]));
		oralOptions.add(Arrays.asList(options[1][3]));
		for(int i=0; i<options[1].length; i++) {
			if(data.getOralOptions().size() <= i || data.getOralOptions().get(i) == null) {
				data.setOralHeader(i, "oral"+(i+1));
				data.setOralText(i, "o"+(i+1)+"_text");
				data.setOralOptions(i, oralOptions.get(i));
			}
		}
		// Infections
		List<List<CheckableOption>> infectionsOptions = new ArrayList<List<CheckableOption>>();
		infectionsOptions.add(Arrays.asList(options[2][0]));
		infectionsOptions.add(Arrays.asList(options[2][1]));
		infectionsOptions.add(Arrays.asList(options[2][2]));
		infectionsOptions.add(Arrays.asList(options[2][3]));
		for(int i=0; i<options[2].length; i++) {
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

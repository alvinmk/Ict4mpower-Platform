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
package ict4mpower.childHealth.panels.dash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import models.PatientInfo;
import nl.topicus.wqplot.components.JQPlot;
import nl.topicus.wqplot.data.NumberSeries;
import nl.topicus.wqplot.options.PlotOptions;
import nl.topicus.wqplot.options.PlotTick;
import nl.topicus.wqplot.options.PlotTooltipAxes;
import nl.topicus.wqplot.options.PlotTooltipLocation;

import org.apache.wicket.model.util.ListModel;

import storage.ApplicationSocketTemp;
import storage.DataEndPoint;

import ict4mpower.AppSession;
import ict4mpower.childHealth.data.GrowthData;
import ict4mpower.childHealth.panels.DivisionPanel;
import ict4mpower.childHealth.panels.growth.Indicator;

/**
 * A Panel displaying a chart from the growth values
 * using wqPlot (jqPlot+WiQuery binding)
 * @author Joakim Lindskog
 *
 */
public class GrowthChartPanel extends DivisionPanel {
	private static final long serialVersionUID = -1172218234548889654L;

	/**
	 * Constructor
	 * @param id component id
	 */
	public GrowthChartPanel(String id) {
		super(id, "title", false);
		
		// Values - get reference values depending on the sex of the child
		PatientInfo pi = ((AppSession)getSession()).getPatientInfo();
		Float[][] references = null;
		ApplicationSocketTemp store = ApplicationSocketTemp.getApplicationSocketTemp();
		String gb = null;
		if(pi.getSex() == PatientInfo.Sex.FEMALE) {
			gb = "girls";
		}
		else if(pi.getSex() == PatientInfo.Sex.MALE) {
			gb = "boys";
		}
		Set<Object> refs = store.getData("ChildHealth", "GrowthReferenceValues:"+gb);
		for(Object o : refs) {
			// Only get first data entry - there should be only one
			references = (Float[][]) o;
			break;
		}

		/** List of all series in the graph */
		List<NumberSeries<Float, Float>> list = new ArrayList<NumberSeries<Float, Float>>();
		
		// Walk through all reference series
		NumberSeries<Float, Float> series;
		for(Float[] arr : references) {
			series = new NumberSeries<Float, Float>();
			for(int i=0; i<arr.length; i++) {
				// Add entry to series
				series.addEntry((float)i, arr[i]);
			}
			// Add series to list of all series
			list.add(series);
		}
		
		// Get values from session (or db if session is empty)
		GrowthData data = GrowthData.instance();
		NumberSeries<Float, Float> vHead = new NumberSeries<Float, Float>();
		NumberSeries<Float, Float> vLength = new NumberSeries<Float, Float>();
		NumberSeries<Float, Float> vWeight = new NumberSeries<Float, Float>();
		String wmy;
		float val;
		float key = 0;
		List<Indicator> indicators = data.getIndicators();
		if(data.getIndicators() == null) {
			// Get data from db
			int max = 0;
			GrowthData gd = null;
			// Get from db
			Set<Serializable> set = DataEndPoint.getDataEndPoint().getEntriesFromPatientId(((AppSession)getSession()).getPatientInfo().getClientId());
			for(Object o : set) {
				if(o instanceof GrowthData) {
					gd = (GrowthData) o;
					if(gd.getIndicators() != null && gd.getIndicators().size() > max) {
						data.setIndicators(gd.getIndicators());
						max = gd.getIndicators().size();
					}
				}
			}
			indicators = data.getIndicators();
		}
		if(indicators != null) {
			Collections.sort(indicators);
			for(Indicator i : indicators) {
				wmy = (String) i.getAccurateAgeArray()[0];
				val = (Float) i.getAccurateAgeArray()[1];
				if(wmy.equals("weeks")) {
					key = val;
				}
				else if(wmy.equals("months")) {
					key = val+10;
				}
				vHead.addEntry(key, i.getHeadCircumference());
				vLength.addEntry(key, i.getLength());
				vWeight.addEntry(key, i.getWeight());
			}
			list.add(vHead);
			list.add(vLength);
			list.add(vWeight);
		}
		
		// Chart
		JQPlot chart =
			new JQPlot("chart", new ListModel<NumberSeries<Float, Float>>(list));
		
		// Options
		PlotOptions options = chart.getOptions();
		
		// Series
		double lw = 1d;
		float op = 0.7f;
		String cMed = "rgba(0,128,0,"+op+")";
		String c1 = "rgba(85,255,85,"+op+")";
		String c2 = "rgba(153,51,51,"+op+")";
		String c3 = "rgba(255,51,51,"+op+")";
		options.addNewSeries() // Head circumference (median)
			.setLabel("Head circumference")
			.setColor(cMed)
			.setShowMarker(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (-1)
			.setColor(c1)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (-2)
			.setColor(c2)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (-3)
			.setColor(c3)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (+1)
			.setColor(c1)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (+2)
			.setColor(c2)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Head circumference (+3)
			.setColor(c3)
			.setShowMarker(false)
			.setShowLabel(false)
			.setLineWidth(lw)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (median)
			.setLabel("Length")
			.setColor(cMed)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (-1)
			.setColor(c1)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (-2)
			.setColor(c2)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (-3)
			.setColor(c3)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (+1)
			.setColor(c1)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (+2)
			.setColor(c2)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Length (+3)
			.setColor(c3)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y2axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (median)
			.setLabel("Weight")
			.setColor(cMed)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (-1)
			.setColor(c1)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (-2)
			.setColor(c2)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (-3)
			.setColor(c3)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (+1)
			.setColor(c1)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (+2)
			.setColor(c2)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (+3)
			.setColor(c3)
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.getHighlighter()
			.setShow(true)
			.setShowTooltip(true)
			.setSizeAdjust(7.5)
			.setTooltipAxes(PlotTooltipAxes.yx)
			.setUseAxesFormatters(false)
			.setFormatString("%.1f {{ySuffix}}, %.1f {{xSuffix}}")
			.setTooltipLocation(PlotTooltipLocation.se)
			.setxAxisFormatFunction("function(val) {" +
					"if(val<14) {return val;}" +
					"else if(val<22) {return val-10;}" +
					"else if((val-10)%12==0) {return (val-10)/12;}" +
					"else return (val-10)%12;}");
		options.addNewSeries() // Head circumference
			.setColor("black")
			.setYaxis("yaxis")
			.setShowLabel(false);
		options.addNewSeries() // Length
			.setColor("black")
			.setYaxis("y2axis")
			.setShowLabel(false);
		options.addNewSeries() // Weight
			.setColor("black")
			.setYaxis("y3axis")
			.setShowLabel(false);
		
		// Tick marks on x-axis
		List<PlotTick> xTicks = new ArrayList<PlotTick>();
		for(int i=0; i<71; i++) {
			if(i<14) {
				// Weeks
				xTicks.add(new PlotTick(i, "<span style='color: green'>"+i+"</span>"));
			}
			else if(i<22) {
				// Months
				xTicks.add(new PlotTick(i, "<span style='color: red'>"+(i-10)+"</span>"));
			}
			else if((i-10)%12==0) {
				// Years
				xTicks.add(new PlotTick(i, "<span style='color: blue; font-size: larger'>"+((i-10)/12)+"</span>"));
			}
			else {
				// Months inbetween years
				xTicks.add(new PlotTick(i, "<span style='color: red'>"+(i-10)%12+"</span>"));
			}
		}
		options.getAxes().getXaxis()
			.setMin(0)
			.setMax(60)
			.setLabelRenderer("$.jqplot.CanvasAxisLabelRenderer")
			.setTicks(xTicks)
			.setHighlightSuffixFunction("function (val) {"
					+"if(val<14) {return 'weeks'}"
					+"else if(val<22) {return 'months'}"
					+"else if((val-10)%12==0) {return 'years'}"
					+"else {return 'months'}"
					+"}")
			.getTickOptions()
				.setFormatString("%i")
				.setShowLabel(true);
		// Head circumference
		options.getAxes().getYaxis()
			.setMin(0)
			.setMax(57)
			.setTickInterval(5)
			.setLabelRenderer("$.jqplot.CanvasAxisLabelRenderer")
			.setLabel("Head circumference (cm)")
			.setHighlightSuffix(" cm")
			.getTickOptions()
				.setFormatString("%i");
		// Length
		options.getAxes().getY2axis()
			.setMin(20)
			.setMax(165)
			.setTickInterval(5)
			.setShow(true)
			.setLabelRenderer("$.jqplot.CanvasAxisLabelRenderer")
			.setLabel("Length (cm)")
			.setHighlightSuffix(" cm")
			.getTickOptions()
				.setFormatString("%i");
		// Weight
		options.getAxes().getY3axis()
			.setMin(0)
			.setMax(60)
			.setTickInterval(2)
			.setShow(true)
			.setLabelRenderer("$.jqplot.CanvasAxisLabelRenderer")
			.setLabel("Weight (kg)")
			.setHighlightSuffix(" kg")
			.getTickOptions()
				.setFormatString("%i");

		add(chart);
	}
}

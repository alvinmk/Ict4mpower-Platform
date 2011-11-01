package ict4mpower.childHealth.panels.dash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import nl.topicus.wqplot.components.JQPlot;
import nl.topicus.wqplot.data.NumberSeries;
import nl.topicus.wqplot.options.PlotOptions;
import nl.topicus.wqplot.options.PlotTick;
import nl.topicus.wqplot.options.PlotTooltipAxes;
import nl.topicus.wqplot.options.PlotTooltipLocation;

import org.apache.wicket.model.util.ListModel;

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

	public GrowthChartPanel(String id) {
		super(id, "title", false);
		
		// Values TODO These values are for boys only - do same for girls - pick by sex
		Float[][] references = new Float[][] {
				new Float[] { // Head circumference (median)
						34.5f, 35.2f, 35.9f, 36.5f, 37.1f, 37.6f, 38.1f, 38.5f, 38.9f, 39.2f, 39.6f, 39.9f, 40.2f, 40.5f, 41.6f, 42.6f, 43.3f, 44.0f, 44.5f, 45.0f, 45.4f, 45.8f, 46.1f, 46.3f, 46.6f, 46.8f, 47.0f, 47.2f, 47.4f, 47.5f, 47.7f, 47.8f, 48.0f, 48.1f, 48.3f, 48.4f, 48.5f, 48.6f, 48.7f, 48.8f, 48.9f, 49.0f, 49.1f, 49.2f, 49.3f, 49.4f, 49.5f, 49.5f, 49.6f, 49.7f, 49.7f, 49.8f, 49.9f, 49.9f, 50.0f, 50.1f, 50.1f, 50.2f, 50.2f, 50.3f, 50.3f, 50.4f, 50.4f, 50.4f, 50.5f, 50.5f, 50.6f, 50.6f, 50.7f, 50.7f, 50.7f
				},
				new Float[] { // Head circumference (-1)
						33.2f, 33.9f, 34.7f, 35.4f, 35.9f, 36.4f, 36.9f, 37.3f, 37.7f, 38.1f, 38.4f, 38.7f, 39.0f, 39.3f, 40.4f, 41.4f, 42.1f, 42.7f, 43.3f, 43.7f, 44.1f, 44.5f, 44.8f, 45.0f, 45.3f, 45.5f, 45.7f, 45.9f, 46.0f, 46.2f, 46.4f, 46.5f, 46.6f, 46.8f, 46.9f, 47.0f, 47.1f, 47.2f, 47.3f, 47.4f, 47.5f, 47.6f, 47.7f, 47.8f, 47.9f, 48.0f, 48.0f, 48.1f, 48.2f, 48.2f, 48.3f, 48.4f, 48.4f, 48.5f, 48.5f, 48.6f, 48.7f, 48.7f, 48.7f, 48.8f, 48.8f, 48.9f, 48.9f, 49.0f, 49.0f, 49.1f, 49.1f, 49.1f, 49.2f, 49.2f, 49.2f
				},
				new Float[] { // Head circumference (-2)
						31.9f, 32.7f, 33.5f, 34.2f, 34.8f, 35.3f, 35.7f, 36.1f, 36.5f, 36.9f, 37.2f, 37.5f, 37.9f, 38.1f, 39.2f, 40.1f, 40.9f, 41.5f, 42.0f, 42.5f, 42.9f, 43.2f, 43.5f, 43.8f, 44.0f, 44.2f, 44.4f, 44.6f, 44.7f, 44.9f, 45.0f, 45.2f, 45.3f, 45.4f, 45.5f, 45.6f, 45.8f, 45.9f, 46.0f, 46.1f, 46.1f, 46.2f, 46.3f, 46.4f, 46.5f, 46.6f, 46.6f, 46.7f, 46.8f, 46.8f, 46.9f, 46.9f, 47.0f, 47.0f, 47.1f, 47.1f, 47.2f, 47.2f, 47.3f, 47.3f, 47.4f, 47.4f, 47.5f, 47.5f, 47.5f, 47.6f, 47.6f, 47.6f, 47.7f, 47.7f, 47.7f
				},
				new Float[] { // Head circumference (-3)
						30.7f, 31.5f, 32.4f, 33.0f, 33.6f, 34.1f, 34.6f, 35.0f, 35.4f, 35.7f, 36.1f, 36.4f, 36.7f, 37.0f, 38.0f, 38.9f, 39.7f, 40.3f, 40.8f, 41.2f, 41.6f, 41.9f, 42.2f, 42.5f, 42.7f, 42.9f, 43.1f, 43.2f, 43.4f, 43.5f, 43.7f, 43.8f, 43.9f, 44.1f, 44.2f, 44.3f, 44.4f, 44.5f, 44.6f, 44.7f, 44.8f, 44.8f, 44.9f, 45.0f, 45.1f, 45.1f, 45.2f, 45.3f, 45.3f, 45.4f, 45.4f, 45.5f, 45.5f, 45.6f, 45.6f, 45.7f, 45.7f, 45.8f, 45.8f, 45.9f, 45.9f, 45.9f, 46.0f, 46.0f, 46.1f, 46.1f, 46.1f, 46.2f, 46.2f, 46.2f, 46.3f
				},
				new Float[] { // Head circumference (+1)
						35.7f, 36.4f, 37.0f, 37.7f, 38.3f, 38.8f, 39.2f, 39.7f, 40.0f, 40.4f, 40.8f, 41.1f, 41.4f, 41.7f, 42.8f, 43.8f, 44.6f, 45.2f, 45.8f, 46.3f, 46.7f, 47.0f, 47.4f, 47.6f, 47.9f, 48.1f, 48.3f, 48.5f, 48.7f, 48.9f, 49.0f, 49.2f, 49.3f, 49.5f, 49.6f, 49.7f, 49.9f, 50.0f, 50.1f, 50.2f, 50.3f, 50.4f, 50.5f, 50.6f, 50.7f, 50.8f, 50.9f, 51.0f, 51.0f, 51.1f, 51.2f, 51.3f, 51.3f, 51.4f, 51.4f, 51.5f, 51.6f, 51.6f, 51.7f, 51.7f, 51.8f, 51.8f, 51.9f, 51.9f, 52.0f, 52.0f, 52.1f, 52.1f, 52.1f, 52.2f, 52.2f
				},
				new Float[] { // Head circumference (+2)
						37.0f, 37.6f, 38.2f, 38.9f, 39.4f, 39.9f, 40.4f, 40.8f, 41.2f, 41.6f, 41.9f, 42.3f, 42.6f, 42.9f, 44.0f, 45.0f, 45.8f, 46.4f, 47.0f, 47.5f, 47.9f, 48.3f, 48.6f, 48.9f, 49.2f, 49.4f, 49.6f, 49.8f, 50.0f, 50.2f, 50.4f, 50.5f, 50.7f, 50.8f, 51.0f, 51.1f, 51.2f, 51.4f, 51.5f, 51.6f, 51.7f, 51.8f, 51.9f, 52.0f, 52.1f, 52.2f, 52.3f, 52.4f, 52.5f, 52.5f, 52.6f, 52.7f, 52.8f, 52.8f, 52.9f, 53.0f, 53.0f, 53.1f, 53.1f, 53.2f, 53.2f, 53.3f, 53.4f, 53.4f, 53.5f, 53.5f, 53.5f, 53.6f, 53.6f, 53.7f, 53.7f
				},
				new Float[] { // Head circumference (+3)
						38.3f, 38.8f, 39.4f, 40.0f, 40.6f, 41.1f, 41.6f, 42.0f, 42.4f, 42.8f, 43.1f, 43.4f, 43.7f, 44.0f, 45.2f, 46.2f, 47.0f, 47.7f, 48.3f, 48.8f, 49.2f, 49.6f, 49.9f, 50.2f, 50.5f, 50.7f, 51.0f, 51.2f, 51.4f, 51.5f, 51.7f, 51.9f, 52.0f, 52.2f, 52.3f, 52.5f, 52.6f, 52.7f, 52.9f, 53.0f, 53.1f, 53.2f, 53.3f, 53.4f, 53.5f, 53.6f, 53.7f, 53.8f, 53.9f, 54.0f, 54.1f, 54.1f, 54.2f, 54.3f, 54.3f, 54.4f, 54.5f, 54.5f, 54.6f, 54.7f, 54.7f, 54.8f, 54.8f, 54.9f, 54.9f, 55.0f, 55.0f, 55.1f, 55.1f, 55.2f, 55.2f
				},
				new Float[] { // Length (median)
						49.9f, 51.1f, 52.3f, 53.4f, 54.4f, 55.3f, 56.2f, 57.1f, 57.9f, 58.7f, 59.4f, 60.1f, 60.8f, 61.4f, 63.9f, 65.9f, 67.6f, 69.2f, 70.6f, 72.0f, 73.3f, 74.5f, 75.7f, 76.9f, 78.0f, 79.1f, 80.2f, 81.2f, 82.3f, 83.2f, 84.2f, 85.1f, 86.0f, 86.9f, 87.1f, 88.0f, 88.8f, 89.6f, 90.4f, 91.2f, 91.9f, 92.7f, 93.4f, 94.1f, 94.8f, 95.4f, 96.1f, 96.7f, 97.4f, 98.0f, 98.6f, 99.2f, 99.9f, 100.4f, 101.0f, 101.6f, 102.2f, 102.8f, 103.3f, 103.9f, 104.4f, 105.0f, 105.6f, 106.1f, 106.7f, 107.2f, 107.8f, 108.3f, 108.9f, 109.4f, 110.0f
				},
				new Float[] { // Length (-1)
						48.0f, 49.2f, 50.4f, 51.5f, 52.4f, 53.4f, 54.3f, 55.1f, 55.9f, 56.6f, 57.4f, 58.1f, 58.7f, 59.4f, 61.8f, 63.8f, 65.5f, 67.0f, 68.4f, 69.7f, 71.0f, 72.2f, 73.4f, 74.5f, 75.6f, 76.6f, 77.6f, 78.6f, 79.6f, 80.5f, 81.4f, 82.3f, 83.1f, 83.9f, 84.1f, 84.9f, 85.6f, 86.4f, 87.1f, 87.8f, 88.5f, 89.2f, 89.9f, 90.5f, 91.1f, 91.8f, 92.4f, 93.0f, 93.6f, 94.2f, 94.7f, 95.3f, 95.9f, 96.4f, 97.0f, 97.5f, 98.1f, 98.6f, 99.1f, 99.7f, 100.2f, 100.7f, 101.2f, 101.7f, 102.3f, 102.8f, 103.3f, 103.8f, 104.3f, 104.8f, 105.3f
				},
				new Float[] { // Length (-2)
						46.1f, 47.3f, 48.5f, 49.5f, 50.5f, 51.4f, 52.3f, 53.1f, 53.9f, 54.6f, 55.4f, 56.0f, 56.7f, 57.3f, 59.7f, 61.7f, 63.3f, 64.8f, 66.2f, 67.5f, 68.7f, 69.9f, 71.0f, 72.1f, 73.1f, 74.1f, 75.0f, 76.0f, 76.9f, 77.7f, 78.6f, 79.4f, 80.2f, 81.0f, 81.0f, 81.7f, 82.5f, 83.1f, 83.8f, 84.5f, 85.1f, 85.7f, 86.4f, 86.9f, 87.5f, 88.1f, 88.7f, 89.2f, 89.8f, 90.3f, 90.9f, 91.4f, 91.9f, 92.4f, 93.0f, 93.5f, 94.0f, 94.4f, 94.9f, 95.4f, 95.9f, 96.4f, 96.9f, 97.4f, 97.8f, 98.3f, 98.8f, 99.3f, 99.7f, 100.2f, 100.7f
				},
				new Float[] { // Length (-3)
						44.2f, 45.4f, 46.6f, 47.6f, 48.6f, 49.5f, 50.3f, 51.1f, 51.9f, 52.6f, 53.3f, 54.0f, 54.7f, 55.3f, 57.6f, 59.6f, 61.2f, 62.7f, 64.0f, 65.2f, 66.4f, 67.6f, 68.6f, 69.6f, 70.6f, 71.6f, 72.5f, 73.3f, 74.2f, 75.0f, 75.8f, 76.5f, 77.2f, 78.0f, 78.0f, 78.6f, 79.3f, 79.9f, 80.5f, 81.1f, 81.7f, 82.3f, 82.8f, 83.4f, 83.9f, 84.4f, 85.0f, 85.5f, 86.0f, 86.5f, 87.0f, 87.5f, 88.0f, 88.4f, 88.9f, 89.4f, 89.8f, 90.3f, 90.7f, 91.2f, 91.6f, 92.1f, 92.5f, 93.0f, 93.4f, 93.9f, 94.3f, 94.7f, 95.2f, 95.6f, 96.1f
				},
				new Float[] { // Length (+1)
						51.8f, 53.0f, 54.3f, 55.3f, 56.3f, 57.3f, 58.2f, 59.1f, 59.9f, 60.7f, 61.4f, 62.1f, 62.8f, 63.4f, 66.0f, 68.0f, 69.8f, 71.3f, 72.8f, 74.2f, 75.6f, 76.9f, 78.1f, 79.3f, 80.5f, 81.7f, 82.8f, 83.9f, 85.0f, 86.0f, 87.0f, 88.0f, 89.0f, 89.9f, 90.2f, 91.1f, 92.0f, 92.9f, 93.7f, 94.5f, 95.3f, 96.1f, 96.9f, 97.6f, 98.4f, 99.1f, 99.8f, 100.5f, 101.2f, 101.8f, 102.5f, 103.2f, 103.8f, 104.5f, 105.1f, 105.7f, 106.3f, 106.9f, 107.5f, 108.1f, 108.7f, 109.3f, 109.9f, 110.5f, 111.1f, 111.7f, 112.3f, 112.8f, 113.4f, 114.0f, 114.6f
				},
				new Float[] { // Length (+2)
						53.7f, 54.9f, 56.2f, 57.2f, 58.3f, 59.2f, 60.2f, 61.0f, 61.9f, 62.7f, 63.4f, 64.1f, 64.8f, 65.5f, 68.0f, 70.1f, 71.9f, 73.5f, 75.0f, 76.5f, 77.9f, 79.2f, 80.5f, 81.8f, 83.0f, 84.2f, 85.4f, 86.5f, 87.7f, 88.8f, 89.8f, 90.9f, 91.9f, 92.9f, 93.2f, 94.2f, 95.2f, 96.1f, 97.0f, 97.9f, 98.7f, 99.6f, 100.4f, 101.2f, 102.0f, 102.7f, 103.5f, 104.2f, 105.0f, 105.7f, 106.4f, 107.1f, 107.8f, 108.5f, 109.1f, 109.8f, 110.4f, 111.1f, 111.7f, 112.4f, 113.0f, 113.6f, 114.2f, 114.9f, 115.5f, 116.1f, 116.7f, 117.4f, 118.0f, 118.6f, 119.2f
				},
				new Float[] { // Length (+3)
						55.6f, 56.8f, 58.1f, 59.2f, 60.2f, 61.2f, 62.1f, 63.0f, 63.9f, 64.7f, 65.4f, 66.2f, 66.9f, 67.5f, 70.1f, 72.2f, 74.0f, 75.7f, 77.2f, 78.7f, 80.1f, 81.5f, 82.9f, 84.2f, 85.5f, 86.7f, 88.0f, 89.2f, 90.4f, 91.5f, 92.6f, 93.8f, 94.9f, 95.9f, 96.3f, 97.3f, 98.3f, 99.3f, 100.3f, 101.2f, 102.1f, 103.0f, 103.9f, 104.8f, 105.6f, 106.4f, 107.2f, 108.0f, 108.8f, 109.5f, 110.3f, 111.0f, 111.7f, 112.5f, 113.2f, 113.9f, 114.6f, 115.2f, 115.9f, 116.6f, 117.3f, 117.9f, 118.6f, 119.2f, 119.9f, 120.6f, 121.2f, 121.9f, 122.6f, 123.2f, 123.9f
				},
				new Float[] { // Weight (median)
						3.3f, 3.5f, 3.8f, 4.1f, 4.4f, 4.7f, 4.9f, 5.2f, 5.4f, 5.6f, 5.8f, 6.0f, 6.2f, 6.4f, 7.0f, 7.5f, 7.9f, 8.3f, 8.6f, 8.9f, 9.2f, 9.4f, 9.6f, 9.9f, 10.1f, 10.3f, 10.5f, 10.7f, 10.9f, 11.1f, 11.3f, 11.5f, 11.8f, 12.0f, 12.2f, 12.4f, 12.5f, 12.7f, 12.9f, 13.1f, 13.3f, 13.5f, 13.7f, 13.8f, 14.0f, 14.2f, 14.3f, 14.5f, 14.7f, 14.8f, 15.0f, 15.2f, 15.3f, 15.5f, 15.7f, 15.8f, 16.0f, 16.2f, 16.3f, 16.5f, 16.7f, 16.8f, 17.0f, 17.2f, 17.3f, 17.5f, 17.7f, 17.8f, 18.0f, 18.2f, 18.3f
				},
				new Float[] { // Weight (-1)
						2.9f, 3.0f, 3.2f, 3.5f, 3.8f, 4.1f, 4.3f, 4.6f, 4.8f, 5.0f, 5.2f, 5.3f, 5.5f, 5.7f, 6.2f, 6.7f, 7.1f, 7.4f, 7.7f, 8.0f, 8.2f, 8.4f, 8.6f, 8.8f, 9.0f, 9.2f, 9.4f, 9.6f, 9.8f, 10.0f, 10.1f, 10.3f, 10.5f, 10.7f, 10.8f, 11.0f, 11.2f, 11.3f, 11.5f, 11.7f, 11.8f, 12.0f, 12.1f, 12.3f, 12.4f, 12.6f, 12.7f, 12.9f, 13.0f, 13.1f, 13.3f, 13.4f, 13.6f, 13.7f, 13.8f, 14.0f, 14.1f, 14.3f, 14.4f, 14.5f, 14.7f, 14.8f, 15.0f, 15.1f, 15.2f, 15.4f, 15.5f, 15.6f, 15.8f, 15.9f, 16.0f
				},
				new Float[] { // Weight (-2)
						2.5f, 2.6f, 2.8f, 3.1f, 3.3f, 3.5f, 3.8f, 4.0f, 4.2f, 4.4f, 4.5f, 4.7f, 4.9f, 5.0f, 5.6f, 6.0f, 6.4f, 6.7f, 6.9f, 7.1f, 7.4f, 7.6f, 7.7f, 7.9f, 8.1f, 8.3f, 8.4f, 8.6f, 8.8f, 8.9f, 9.1f, 9.2f, 9.4f, 9.5f, 9.7f, 9.8f, 10.0f, 10.1f, 10.2f, 10.4f, 10.5f, 10.7f, 10.8f, 10.9f, 11.0f, 11.2f, 11.3f, 11.4f, 11.5f, 11.6f, 11.8f, 11.9f, 12.0f, 12.1f, 12.2f, 12.4f, 12.5f, 12.6f, 12.7f, 12.8f, 12.9f, 13.1f, 13.2f, 13.3f, 13.4f, 13.5f, 13.6f, 13.7f, 13.8f, 14.0f, 14.1f
				},
				new Float[] { // Weight (-3)
						2.1f, 2.2f, 2.4f, 2.6f, 2.9f, 3.1f, 3.3f, 3.5f, 3.7f, 3.8f, 4.0f, 4.2f, 4.3f, 4.4f, 4.9f, 5.3f, 5.7f, 5.9f, 6.2f, 6.4f, 6.6f, 6.8f, 6.9f, 7.1f, 7.2f, 7.4f, 7.5f, 7.7f, 7.8f, 8.0f, 8.1f, 8.2f, 8.4f, 8.5f, 8.6f, 8.8f, 8.9f, 9.0f, 9.1f, 9.2f, 9.4f, 9.5f, 9.6f, 9.7f, 9.8f, 9.9f, 10.0f, 10.1f, 10.2f, 10.3f, 10.4f, 10.5f, 10.6f, 10.7f, 10.8f, 10.9f, 11.0f, 11.1f, 11.2f, 11.3f, 11.4f, 11.5f, 11.6f, 11.7f, 11.8f, 11.9f, 12.0f, 12.1f, 12.2f, 12.3f, 12.4f
				},
				new Float[] { // Weight (+1)
						3.9f, 4.0f, 4.3f, 4.7f, 5.0f, 5.3f, 5.6f, 5.9f, 6.1f, 6.4f, 6.6f, 6.8f, 7.0f, 7.2f, 7.8f, 8.4f, 8.8f, 9.2f, 9.6f, 9.9f, 10.2f, 10.5f, 10.8f, 11.0f, 11.3f, 11.5f, 11.7f, 12.0f, 12.2f, 12.5f, 12.7f, 12.9f, 13.2f, 13.4f, 13.6f, 13.9f, 14.1f, 14.3f, 14.5f, 14.8f, 15.0f, 15.2f, 15.4f, 15.6f, 15.8f, 16.0f, 16.2f, 16.4f, 16.6f, 16.8f, 17.0f, 17.2f, 17.4f, 17.6f, 17.8f, 18.0f, 18.2f, 18.4f, 18.6f, 18.8f, 19.0f, 19.2f, 19.4f, 19.6f, 19.8f, 20.0f, 20.2f, 20.4f, 20.6f, 20.8f, 21.0f
				},
				new Float[] { // Weight (+2)
						4.4f, 4.6f, 4.9f, 5.3f, 5.7f, 6.0f, 6.3f, 6.6f, 6.9f, 7.2f, 7.4f, 7.6f, 7.8f, 8.0f, 8.7f, 9.3f, 9.8f, 10.3f, 10.7f, 11.0f, 11.4f, 11.7f, 12.0f, 12.3f, 12.6f, 12.8f, 13.1f, 13.4f, 13.7f, 13.9f, 14.2f, 14.5f, 14.7f, 15.0f, 15.3f, 15.5f, 15.8f, 16.1f, 16.3f, 16.6f, 16.9f, 17.1f, 17.4f, 17.6f, 17.8f, 18.1f, 18.3f, 18.6f, 18.8f, 19.0f, 19.3f, 19.5f, 19.7f, 20.0f, 20.2f, 20.5f, 20.7f, 20.9f, 21.2f, 21.4f, 21.7f, 21.9f, 22.2f, 22.4f, 22.7f, 22.9f, 23.2f, 23.4f, 23.7f, 23.9f, 24.2f
				},
				new Float[] { // Weight (+3)
						5.0f, 5.3f, 5.6f, 6.0f, 6.4f, 6.8f, 7.2f, 7.5f, 7.8f, 8.0f, 8.3f, 8.5f, 8.8f, 9.0f, 9.7f, 10.4f, 10.9f, 11.4f, 11.9f, 12.3f, 12.7f, 13.0f, 13.3f, 13.7f, 14.0f, 14.3f, 14.6f, 14.9f, 15.3f, 15.6f, 15.9f, 16.2f, 16.5f, 16.8f, 17.1f, 17.5f, 17.8f, 18.1f, 18.4f, 18.7f, 19.0f, 19.3f, 19.6f, 19.9f, 20.2f, 20.4f, 20.7f, 21.0f, 21.3f, 21.6f, 21.9f, 22.1f, 22.4f, 22.7f, 23.0f, 23.3f, 23.6f, 23.9f, 24.2f, 24.5f, 24.8f, 25.1f, 25.4f, 25.7f, 26.0f, 26.3f, 26.6f, 26.9f, 27.2f, 27.6f, 27.9f
				}
		};

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
			System.out.println("set "+set.size());
			for(Object o : set) {
				System.out.println("obj "+o.getClass());
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
			.setColor("#55ff55")
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (+2)
			.setColor("#993333")
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		options.addNewSeries() // Weight (+3)
			.setColor("#ff3333")
			.setShowMarker(false)
			.setLineWidth(lw)
			.setYaxis("y3axis")
			.setShowLabel(false)
			.getMarkerOptions()
				.setShowHighlight(false);
		// TODO Test - actual values
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
		
		// Tick marks on x-axis TODO 0-13 weeks?
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

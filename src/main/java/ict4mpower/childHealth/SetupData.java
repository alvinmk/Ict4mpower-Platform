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
package ict4mpower.childHealth;

import ict4mpower.childHealth.data.CheckableOption;
import ict4mpower.childHealth.panels.development.MilestoneTests;
import ict4mpower.childHealth.panels.immunization.Vaccination;
import ict4mpower.childHealth.panels.medications.Medicine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import storage.ApplicationSocket;
import storage.dight.ApplicationKlass;

/**
 * Class to set up the application specific data in the database
 * @author Joakim Lindskog
 *
 */
public class SetupData {
	private static final Logger log = Logger.getLogger(SetupData.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationSocket store = new ApplicationSocket();
		String app = "ChildHealth";
		
		
		
		// Dashboard & Growth - Reference values for growth chart and growth indicators
		Float[][] growthValuesGirls = new Float[][] {
				new Float[] { // Head circumference (median)
						33.9f, 34.6f, 35.2f, 35.8f, 36.4f, 36.8f, 37.3f, 37.7f, 38.0f, 38.4f, 38.7f, 39.0f, 39.3f, 39.5f, 40.6f, 41.5f, 42.2f, 42.8f, 43.4f, 43.8f, 44.2f, 44.6f, 44.9f, 45.2f, 45.4f, 45.7f, 45.9f, 46.1f, 46.2f, 46.4f, 46.6f, 46.7f, 46.9f, 47.0f, 47.2f, 47.3f, 47.5f, 47.6f, 47.7f, 47.8f, 47.9f, 48.0f, 48.1f, 48.2f, 48.3f, 48.4f, 48.5f, 48.6f, 48.7f, 48.7f, 48.8f, 48.9f, 49.0f, 49.0f, 49.1f, 49.2f, 49.2f, 49.3f, 49.3f, 49.4f, 49.4f, 49.5f, 49.5f, 49.6f, 49.6f, 49.7f, 49.7f, 49.8f, 49.8f, 49.9f, 49.9f
				},
				new Float[] { // Head circumference (-1)
						32.7f, 33.4f, 34.1f, 34.7f, 35.2f, 35.7f, 36.1f, 36.5f, 36.8f, 37.1f, 37.4f, 37.7f, 38.0f, 38.3f, 39.3f, 40.2f, 40.9f, 41.5f, 42.0f, 42.5f, 42.9f, 43.2f, 43.5f, 43.8f, 44.1f, 44.3f, 44.5f, 44.7f, 44.9f, 45.0f, 45.2f, 45.3f, 45.5f, 45.6f, 45.8f, 45.9f, 46.1f, 46.2f, 46.3f, 46.4f, 46.5f, 46.6f, 46.7f, 46.8f, 46.9f, 47.0f, 47.1f, 47.2f, 47.3f, 47.3f, 47.4f, 47.5f, 47.5f, 47.6f, 47.7f, 47.7f, 47.8f, 47.9f, 47.9f, 48.0f, 48.0f, 48.1f, 48.1f, 48.2f, 48.2f, 48.3f, 48.3f, 48.4f, 48.4f, 48.5f, 48.5f
				},
				new Float[] { // Head circumference (-2)
						31.5f, 32.2f, 32.9f, 33.5f, 34.0f, 34.5f, 34.9f, 35.3f, 35.6f, 35.9f, 36.2f, 36.5f, 36.8f, 37.0f, 38.1f, 38.9f, 39.6f, 40.2f, 40.7f, 41.2f, 41.5f, 41.9f, 42.2f, 42.4f, 42.7f, 42.9f, 43.1f, 43.3f, 43.5f, 43.6f, 43.8f, 44.0f, 44.1f, 44.3f, 44.4f, 44.5f, 44.7f, 44.8f, 44.9f, 45.0f, 45.1f, 45.2f, 45.3f, 45.4f, 45.5f, 45.6f, 45.7f, 45.8f, 45.8f, 45.9f, 46.0f, 46.1f, 46.1f, 46.2f, 46.3f, 46.3f, 46.4f, 46.4f, 46.5f, 46.5f, 46.6f, 46.7f, 46.7f, 46.8f, 46.8f, 46.9f, 46.9f, 46.9f, 47.0f, 47.0f, 47.1f
				},
				new Float[] { // Head circumference (-3)
						30.3f, 31.1f, 31.8f, 32.4f, 32.9f, 33.3f, 33.7f, 34.1f, 34.4f, 34.7f, 35.0f, 35.3f, 35.5f, 35.8f, 36.8f, 37.6f, 38.3f, 38.9f, 39.4f, 39.8f, 40.2f, 40.5f, 40.8f, 41.1f, 41.3f, 41.5f, 41.7f, 41.9f, 42.1f, 42.3f, 42.4f, 42.6f, 42.7f, 42.9f, 43.0f, 43.1f, 43.3f, 43.4f, 43.5f, 43.6f, 43.7f, 43.8f, 43.9f, 44.0f, 44.1f, 44.2f, 44.3f, 44.4f, 44.4f, 44.5f, 44.6f, 44.6f, 44.7f, 44.8f, 44.8f, 44.9f, 45.0f, 45.0f, 45.1f, 45.1f, 45.2f, 45.2f, 45.3f, 45.3f, 45.4f, 45.4f, 45.5f, 45.5f, 45.6f, 45.6f, 45.7f
				},
				new Float[] { // Head circumference (+1)
						35.1f, 35.7f, 36.4f, 37.0f, 37.5f, 38.0f, 38.5f, 38.9f, 39.2f, 39.6f, 39.9f, 40.2f, 40.5f, 40.8f, 41.8f, 42.7f, 43.5f, 44.1f, 44.7f, 45.2f, 45.6f, 45.9f, 46.3f, 46.5f, 46.8f, 47.0f, 47.2f, 47.4f, 47.6f, 47.8f, 48.0f, 48.1f, 48.3f, 48.4f, 48.6f, 48.7f, 48.9f, 49.0f, 49.1f, 49.2f, 49.3f, 49.4f, 49.6f, 49.7f, 49.7f, 49.8f, 49.9f, 50.0f, 50.1f, 50.2f, 50.2f, 50.3f, 50.4f, 50.4f, 50.5f, 50.6f, 50.6f, 50.7f, 50.8f, 50.8f, 50.9f, 50.9f, 51.0f, 51.0f, 51.1f, 51.1f, 51.2f, 51.2f, 51.3f, 51.3f, 51.3f
				},
				new Float[] { // Head circumference (+2)
						36.2f, 36.9f, 37.5f, 38.2f, 38.7f, 39.2f, 39.6f, 40.1f, 40.4f, 40.8f, 41.1f, 41.4f, 41.7f, 42.0f, 43.1f, 44.0f, 44.8f, 45.5f, 46.0f, 46.5f, 46.9f, 47.3f, 47.6f, 47.9f, 48.2f, 48.4f, 48.6f, 48.8f, 49.0f, 49.2f, 49.4f, 49.5f, 49.7f, 49.8f, 50.0f, 50.1f, 50.3f, 50.4f, 50.5f, 50.6f, 50.7f, 50.9f, 51.0f, 51.1f, 51.2f, 51.2f, 51.3f, 51.4f, 51.5f, 51.6f, 51.7f, 51.7f, 51.8f, 51.9f, 51.9f, 52.0f, 52.1f, 52.1f, 52.2f, 52.2f, 52.3f, 52.3f, 52.4f, 52.4f, 52.5f, 52.5f, 52.6f, 52.6f, 52.7f, 52.7f, 52.8f
				},
				new Float[] { // Head circumference (+3)
						37.4f, 38.1f, 38.7f, 39.3f, 39.9f, 40.4f, 40.8f, 41.3f, 41.6f, 42.0f, 42.3f, 42.7f, 43.0f, 43.2f, 44.4f, 45.3f, 46.1f, 46.8f, 47.4f, 47.8f, 48.3f, 48.6f, 49.0f, 49.3f, 49.5f, 49.8f, 50.0f, 50.2f, 50.4f, 50.6f, 50.7f, 50.9f, 51.1f, 51.2f, 51.4f, 51.5f, 51.7f, 51.8f, 51.9f, 52.0f, 52.2f, 52.3f, 52.4f, 52.5f, 52.6f, 52.7f, 52.7f, 52.8f, 52.9f, 53.0f, 53.1f, 53.1f, 53.2f, 53.3f, 53.3f, 53.4f, 53.5f, 53.5f, 53.6f, 53.6f, 53.7f, 53.8f, 53.8f, 53.9f, 53.9f, 54.0f, 54.0f, 54.1f, 54.1f, 54.1f, 54.2f
				},
				new Float[] { // Length (median)
						49.1f, 50.3f, 51.5f, 52.5f, 53.4f, 54.2f, 55.1f, 55.8f, 56.6f, 57.3f, 57.9f, 58.6f, 59.2f, 59.8f, 62.1f, 64.0f, 65.7f, 67.3f, 68.7f, 70.1f, 71.5f, 72.8f, 74.0f, 75.2f, 76.4f, 77.5f, 78.6f, 79.7f, 80.7f, 81.7f, 82.7f, 83.7f, 84.6f, 85.5f, 85.7f, 86.6f, 87.4f, 88.3f, 89.1f, 89.9f, 90.7f, 91.4f, 92.2f, 92.9f, 93.6f, 94.4f, 95.1f, 95.7f, 96.4f, 97.1f, 97.7f, 98.4f, 99.0f, 99.7f, 100.3f, 100.9f, 101.5f, 102.1f, 102.7f, 103.3f, 103.9f, 104.5f, 105.0f, 105.6f, 106.2f, 106.7f, 107.3f, 107.8f, 108.4f, 108.9f, 109.4f
				},
				new Float[] { // Length (-1)
						47.3f, 48.4f, 49.6f, 50.5f, 51.4f, 52.3f, 53.1f, 53.8f, 54.6f, 55.2f, 55.9f, 56.5f, 57.1f, 57.7f, 59.9f, 61.8f, 63.5f, 65.0f, 66.4f, 67.7f, 69.0f, 70.3f, 71.4f, 72.6f, 73.7f, 74.8f, 75.8f, 76.8f, 77.8f, 78.8f, 79.7f, 80.6f, 81.5f, 82.3f, 82.5f, 83.3f, 84.1f, 84.9f, 85.7f, 86.4f, 87.1f, 87.9f, 88.6f, 89.3f, 89.9f, 90.6f, 91.2f, 91.9f, 92.5f, 93.1f, 93.8f, 94.4f, 95.0f, 95.6f, 96.2f, 96.7f, 97.3f, 97.9f, 98.4f, 99.0f, 99.5f, 100.1f, 100.6f, 101.1f, 101.6f, 102.2f, 102.7f, 103.2f, 103.7f, 104.2f, 104.7f
				},
				new Float[] { // Length (-2)
						45.4f, 46.6f, 47.7f, 48.6f, 49.5f, 50.3f, 51.1f, 51.8f, 52.5f, 53.2f, 53.8f, 54.4f, 55.0f, 55.6f, 57.8f, 59.6f, 61.2f, 62.7f, 64.0f, 65.3f, 66.5f, 67.7f, 68.9f, 70.0f, 71.0f, 72.0f, 73.0f, 74.0f, 74.9f, 75.8f, 76.7f, 77.5f, 78.4f, 79.2f, 79.3f, 80.0f, 80.8f, 81.5f, 82.2f, 82.9f, 83.6f, 84.3f, 84.9f, 85.6f, 86.2f, 86.8f, 87.4f, 88.0f, 88.6f, 89.2f, 89.8f, 90.4f, 90.9f, 91.5f, 92.0f, 92.5f, 93.1f, 93.6f, 94.1f, 94.6f, 95.1f, 95.6f, 96.1f, 96.6f, 97.1f, 97.6f, 98.1f, 98.5f, 99.0f, 99.5f, 99.9f
				},
				new Float[] { // Length (-3)
						43.6f, 44.7f, 45.8f, 46.7f, 47.5f, 48.3f, 49.1f, 49.8f, 50.5f, 51.2f, 51.8f, 52.4f, 52.9f, 53.5f, 55.6f, 57.4f, 58.9f, 60.3f, 61.7f, 62.9f, 64.1f, 65.2f, 66.3f, 67.3f, 68.3f, 69.3f, 70.2f, 71.1f, 72.0f, 72.8f, 73.7f, 74.5f, 75.2f, 76.0f, 76.0f, 76.8f, 77.5f, 78.1f, 78.8f, 79.5f, 80.1f, 80.7f, 81.3f, 81.9f, 82.5f, 83.1f, 83.6f, 84.2f, 84.7f, 85.3f, 85.8f, 86.3f, 86.8f, 87.4f, 87.9f, 88.4f, 88.9f, 89.3f, 89.8f, 90.3f, 90.7f, 91.2f, 91.7f, 92.1f, 92.6f, 93.0f, 93.4f, 93.9f, 94.3f, 94.7f, 95.2f
				},
				new Float[] { // Length (+1)
						51.0f, 52.2f, 53.4f, 54.4f, 55.3f, 56.2f, 57.1f, 57.8f, 58.6f, 59.3f, 60.0f, 60.7f, 61.3f, 61.9f, 64.3f, 66.2f, 68.0f, 69.6f, 71.1f, 72.6f, 73.9f, 75.3f, 76.6f, 77.8f, 79.1f, 80.2f, 81.4f, 82.5f, 83.6f, 84.7f, 85.7f, 86.7f, 87.7f, 88.7f, 88.9f, 89.9f, 90.8f, 91.7f, 92.5f, 93.4f, 94.2f, 95.0f, 95.8f, 96.6f, 97.4f, 98.1f, 98.9f, 99.6f, 100.3f, 101.0f, 101.7f, 102.4f, 103.1f, 103.8f, 104.5f, 105.1f, 105.8f, 106.4f, 107.0f, 107.7f, 108.3f, 108.9f, 109.5f, 110.1f, 110.7f, 111.3f, 111.9f, 112.5f, 113.0f, 113.6f, 114.2f
				},
				new Float[] { // Length (+2)
						52.9f, 54.1f, 55.3f, 56.3f, 57.3f, 58.2f, 59.0f, 59.9f, 60.6f, 61.4f, 62.1f, 62.7f, 63.4f, 64.0f, 66.4f, 68.5f, 70.3f, 71.9f, 73.5f, 75.0f, 76.4f, 77.8f, 79.2f, 80.5f, 81.7f, 83.0f, 84.2f, 85.4f, 86.5f, 87.6f, 88.7f, 89.8f, 90.8f, 91.9f, 92.2f, 93.1f, 94.1f, 95.0f, 96.0f, 96.9f, 97.7f, 98.6f, 99.4f, 100.3f, 101.1f, 101.9f, 102.7f, 103.4f, 104.2f, 105.0f, 105.7f, 106.4f, 107.2f, 107.9f, 108.6f, 109.3f, 110.0f, 110.7f, 111.3f, 112.0f, 112.7f, 113.3f, 114.0f, 114.6f, 115.2f, 115.9f, 116.5f, 117.1f, 117.7f, 118.3f, 118.9f
				},
				new Float[] { // Length (+3)
						54.7f, 56.0f, 57.2f, 58.2f, 59.2f, 60.1f, 61.0f, 61.9f, 62.6f, 63.4f, 64.1f, 64.8f, 65.5f, 66.1f, 68.6f, 70.7f, 72.5f, 74.2f, 75.8f, 77.4f, 78.9f, 80.3f, 81.7f, 83.1f, 84.4f, 85.7f, 87.0f, 88.2f, 89.4f, 90.6f, 91.7f, 92.9f, 94.0f, 95.0f, 95.4f, 96.4f, 97.4f, 98.4f, 99.4f, 100.3f, 101.3f, 102.2f, 103.1f, 103.9f, 104.8f, 105.6f, 106.5f, 107.3f, 108.1f, 108.9f, 109.7f, 110.5f, 111.2f, 112.0f, 112.7f, 113.5f, 114.2f, 114.9f, 115.7f, 116.4f, 117.1f, 117.7f, 118.4f, 119.1f, 119.8f, 120.4f, 121.1f, 121.8f, 122.4f, 123.1f, 123.7f
				},
				new Float[] { // Weight (median)
						3.2f, 3.3f, 3.6f, 3.8f, 4.1f, 4.3f, 4.6f, 4.8f, 5.0f, 5.2f, 5.4f, 5.5f, 5.7f, 5.8f, 6.4f, 6.9f, 7.3f, 7.6f, 7.9f, 8.2f, 8.5f, 8.7f, 8.9f, 9.2f, 9.4f, 9.6f, 9.8f, 10.0f, 10.2f, 10.4f, 10.6f, 10.9f, 11.1f, 11.3f, 11.5f, 11.7f, 11.9f, 12.1f, 12.3f, 12.5f, 12.7f, 12.9f, 13.1f, 13.3f, 13.5f, 13.7f, 13.9f, 14.0f, 14.2f, 14.4f, 14.6f, 14.8f, 15.0f, 15.2f, 15.3f, 15.5f, 15.7f, 15.9f, 16.1f, 16.3f, 16.4f, 16.6f, 16.8f, 17.0f, 17.2f, 17.3f, 17.5f, 17.7f, 17.9f, 18.0f, 18.2f
				},
				new Float[] { // Weight (-1)
						2.8f, 2.9f, 3.1f, 3.3f, 3.6f, 3.8f, 4.0f, 4.2f, 4.4f, 4.6f, 4.7f, 4.9f, 5.0f, 5.1f, 5.7f, 6.1f, 6.5f, 6.8f, 7.0f, 7.3f, 7.5f, 7.7f, 7.9f, 8.1f, 8.3f, 8.5f, 8.7f, 8.9f, 9.1f, 9.2f, 9.4f, 9.6f, 9.8f, 10.0f, 10.2f, 10.3f, 10.5f, 10.7f, 10.9f, 11.1f, 11.2f, 11.4f, 11.6f, 11.7f, 11.9f, 12.0f, 12.2f, 12.4f, 12.5f, 12.7f, 12.8f, 13.0f, 13.1f, 13.3f, 13.4f, 13.6f, 13.7f, 13.9f, 14.0f, 14.2f, 14.3f, 14.5f, 14.6f, 14.8f, 14.9f, 15.1f, 15.2f, 15.3f, 15.5f, 15.6f, 15.8f
				},
				new Float[] { // Weight (-2)
						2.4f, 2.5f, 2.7f, 2.9f, 3.1f, 3.3f, 3.5f, 3.7f, 3.8f, 4.0f, 4.1f, 4.3f, 4.4f, 4.5f, 5.0f, 5.4f, 5.7f, 6.0f, 6.3f, 6.5f, 6.7f, 6.9f, 7.0f, 7.2f, 7.4f, 7.6f, 7.7f, 7.9f, 8.1f, 8.2f, 8.4f, 8.6f, 8.7f, 8.9f, 9.0f, 9.2f, 9.4f, 9.5f, 9.7f, 9.8f, 10.0f, 10.1f, 10.3f, 10.4f, 10.5f, 10.7f, 10.8f, 10.9f, 11.1f, 11.2f, 11.3f, 11.5f, 11.6f, 11.7f, 11.8f, 12.0f, 12.1f, 12.2f, 12.3f, 12.4f, 12.6f, 12.7f, 12.8f, 12.9f, 13.0f, 13.2f, 13.3f, 13.4f, 13.5f, 13.6f, 13.7f
				},
				new Float[] { // Weight (-3)
						2.0f, 2.1f, 2.3f, 2.5f, 2.7f, 2.9f, 3.0f, 3.2f, 3.3f, 3.5f, 3.6f, 3.8f, 3.9f, 4.0f, 4.4f, 4.8f, 5.1f, 5.3f, 5.6f, 5.8f, 5.9f, 6.1f, 6.3f, 6.4f, 6.6f, 6.7f, 6.9f, 7.0f, 7.2f, 7.3f, 7.5f, 7.6f, 7.8f, 7.9f, 8.1f, 8.2f, 8.4f, 8.5f, 8.6f, 8.8f, 8.9f, 9.0f, 9.1f, 9.3f, 9.4f, 9.5f, 9.6f, 9.7f, 9.8f, 9.9f, 10.1f, 10.2f, 10.3f, 10.4f, 10.5f, 10.6f, 10.7f, 10.8f, 10.9f, 11.0f, 11.1f, 11.2f, 11.3f, 11.4f, 11.5f, 11.6f, 11.7f, 11.8f, 11.9f, 12.0f, 12.1f
				},
				new Float[] { // Weight (+1)
						3.7f, 3.9f, 4.1f, 4.4f, 4.7f, 5.0f, 5.2f, 5.5f, 5.7f, 5.9f, 6.1f, 6.3f, 6.5f, 6.6f, 7.3f, 7.8f, 8.2f, 8.6f, 9.0f, 9.3f, 9.6f, 9.9f, 10.1f, 10.4f, 10.6f, 10.9f, 11.1f, 11.4f, 11.6f, 11.8f, 12.1f, 12.3f, 12.5f, 12.8f, 13.0f, 13.3f, 13.5f, 13.7f, 14.0f, 14.2f, 14.4f, 14.7f, 14.9f, 15.1f, 15.4f, 15.6f, 15.8f, 16.0f, 16.3f, 16.5f, 16.7f, 16.9f, 17.2f, 17.4f, 17.6f, 17.8f, 18.1f, 18.3f, 18.5f, 18.8f, 19.0f, 19.2f, 19.4f, 19.7f, 19.9f, 20.1f, 20.3f, 20.6f, 20.8f, 21.0f, 21.2f
				},
				new Float[] { // Weight (+2)
						4.2f, 4.4f, 4.7f, 5.0f, 5.4f, 5.7f, 6.0f, 6.2f, 6.5f, 6.7f, 6.9f, 7.1f, 7.3f, 7.5f, 8.2f, 8.8f, 9.3f, 9.8f, 10.2f, 10.5f, 10.9f, 11.2f, 11.5f, 11.8f, 12.1f, 12.4f, 12.6f, 12.9f, 13.2f, 13.5f, 13.7f, 14.0f, 14.3f, 14.6f, 14.8f, 15.1f, 15.4f, 15.7f, 16.0f, 16.2f, 16.5f, 16.8f, 17.1f, 17.3f, 17.6f, 17.9f, 18.1f, 18.4f, 18.7f, 19.0f, 19.2f, 19.5f, 19.8f, 20.1f, 20.4f, 20.7f, 20.9f, 21.2f, 21.5f, 21.8f, 22.1f, 22.4f, 22.6f, 22.9f, 23.2f, 23.5f, 23.8f, 24.1f, 24.4f, 24.6f, 24.9f
				},
				new Float[] { // Weight (+3)
						4.8f, 5.1f, 5.4f, 5.7f, 6.1f, 6.5f, 6.8f, 7.1f, 7.3f, 7.6f, 7.8f, 8.1f, 8.3f, 8.5f, 9.3f, 10.0f, 10.6f, 11.1f, 11.6f, 12.0f, 12.4f, 12.8f, 13.1f, 13.5f, 13.8f, 14.1f, 14.5f, 14.8f, 15.1f, 15.4f, 15.7f, 16.0f, 16.4f, 16.7f, 17.0f, 17.3f, 17.7f, 18.0f, 18.3f, 18.7f, 19.0f, 19.3f, 19.6f, 20.0f, 20.3f, 20.6f, 20.9f, 21.3f, 21.6f, 22.0f, 22.3f, 22.7f, 23.0f, 23.4f, 23.7f, 24.1f, 24.5f, 24.8f, 25.2f, 25.5f, 25.9f, 26.3f, 26.6f, 27.0f, 27.4f, 27.7f, 28.1f, 28.5f, 28.8f, 29.2f, 29.5f
				}
		};
		Float[][] growthValuesBoys = new Float[][] {
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
		if(store.getData(app, "GrowthReferenceValues:girls").size()==0 ){
			log.info("GrowthReferenceValues:girls does not exsist, creating it");
			store.storeData(app, "GrowthReferenceValues:girls", growthValuesGirls);
		}
		if(store.getData(app, "GrowthReferenceValues:boys").size()==0 ){
			log.info("GrowthReferenceValues:boys does not exsist, creating it");
			store.storeData(app, "GrowthReferenceValues:boys", growthValuesBoys);
		}
		// Immunization - standard vaccinations to be given
		List<Vaccination> vaccinations = new ArrayList<Vaccination>(Arrays.asList(new Vaccination[]{
				new Vaccination("BCG", Calendar.WEEK_OF_YEAR, 0, null, "", ""),
				new Vaccination("Oral Polio 0", Calendar.WEEK_OF_YEAR, 0, null, "", ""),
				new Vaccination("Oral Polio 1", Calendar.WEEK_OF_YEAR, 6, null, "", ""),
				new Vaccination("DPT+HepB+Hib 1", Calendar.WEEK_OF_YEAR, 6, null, "", ""),
				new Vaccination("Oral Polio 2", Calendar.WEEK_OF_YEAR, 10, null, "", ""),
				new Vaccination("DPT+HepB+Hib 2", Calendar.WEEK_OF_YEAR, 10, null, "", ""),
				new Vaccination("Oral Polio 2", Calendar.MONTH, 3, null, "", ""),
				new Vaccination("DPT+HepB+Hib 3", Calendar.MONTH, 3, null, "", ""),
				new Vaccination("Measles", Calendar.MONTH, 9, null, "", "")
		}));
		if(store.getData(app, "StandardVaccinations").size()==0 ){
			log.info("StandardVaccinations does not exsist, creating it");
			store.storeData(app, "StandardVaccinations", vaccinations);
		}
		
		// Medications - standard vitamin A supplements
		List<Medicine> vitamins = null;
		vitamins = new ArrayList<Medicine>(Arrays.asList(new Medicine[]{
				new Medicine("Vitamin A", Calendar.WEEK_OF_YEAR, 0, null, "100 000 IU", ""),
				new Medicine("Vitamin A", Calendar.MONTH, 6, null, "100 000 IU", ""),
				new Medicine("Vitamin A", Calendar.YEAR, 1, null, "100 000 IU", ""),
				new Medicine("Vitamin A", Calendar.MONTH, 18, null, "100 000 IU", ""),
				new Medicine("Vitamin A", Calendar.YEAR, 2, null, "100 000 IU", "")
		}));
		if(store.getData(app, "StandardVitaminA").size()==0 ){
			log.info("StandardVitaminA does not exsist, creating it");
			store.storeData(app, "StandardVitaminA", vitamins);
		}
		
		// Medications - standard de-worming medications
		List<Medicine> deworm = null;
		deworm = new ArrayList<Medicine>(Arrays.asList(new Medicine[]{
				new Medicine("De-worming 1", Calendar.WEEK_OF_YEAR, 0, null, "100 000 IU", ""),
				new Medicine("De-worming 2", Calendar.MONTH, 6, null, "100 000 IU", "")
		}));
		if(store.getData(app, "StandardDeworming").size()==0 ){
			log.info("StandardDeworming does not exsist, creating it");
			store.storeData(app, "StandardDeworming", deworm);
		}
		
		// Medications - standard anti-malarial medications
		List<Medicine> antimal = null;
		antimal = new ArrayList<Medicine>(Arrays.asList(new Medicine[]{
				new Medicine("Antimalarial 1", Calendar.WEEK_OF_YEAR, 0, null, "100 000 IU", ""),
				new Medicine("Antimalarial 2", Calendar.MONTH, 6, null, "100 000 IU", "")
		}));
		if(store.getData(app, "StandardAntimalarial").size()==0 ){
			log.info("StandardAntimalarial does not exsist, creating it");
			store.storeData(app, "StandardAntimalarial", antimal);
		}
		
		// Development - milestone tests
		List<MilestoneTests> tests = Arrays.asList(
				new MilestoneTests(Calendar.WEEK_OF_YEAR, 4, "Symmetric spontaneous motor skills",
						null, "Follows objects with eyes", null),
				new MilestoneTests(Calendar.WEEK_OF_YEAR, 6, "Holds up the head lying on stomach<br/>Opens hands",
						null, "Smiles at parents<br/>Responds to sounds", null),
				new MilestoneTests(Calendar.MONTH, 6, "Turns around<br/>Pulls self up towards a sitting position",
						"Transfers objects from one hand to the other",
						"Looks for the dropped object<br/>Makes double syllable sounds such as 'mumum' and 'dada'",
						null),
				new MilestoneTests(Calendar.MONTH, 10, "Rises, walks with support",
						"Picks up objects with pincergrasp",
						"Hits two objects against each other",
						null)
		);
		if(store.getData(app, "MilestoneTests").size()==0 ){
			log.info("MilestoneTests does not exsist, creating it");
			store.storeData(app, "MilestoneTests", tests);
		}
		
		// Education - checkable options that can be shown in the visit summary
		// Note: Make sure to have the text for the properties listed here
		// (f1_option1 etc.) in the DivisionPanel.properties file
		CheckableOption[][][] options = new CheckableOption[][][]{
			new CheckableOption[][]{ // Feeding
					new CheckableOption[] {
							new CheckableOption("f1_option1"),
							new CheckableOption("f1_option2"),
							new CheckableOption("f1_option3")
					},
					new CheckableOption[] {
							new CheckableOption("f2_option1"),
							new CheckableOption("f2_option2"),
							new CheckableOption("f2_option3")
					},
					new CheckableOption[] {
							new CheckableOption("f3_option1"),
							new CheckableOption("f3_option2"),
							new CheckableOption("f3_option3")
					},
					new CheckableOption[] {
							new CheckableOption("f4_option1"),
							new CheckableOption("f4_option2"),
							new CheckableOption("f4_option3")
					}
			},
			new CheckableOption[][]{ // Oral medications
					new CheckableOption[] {
							new CheckableOption("o1_option1"),
							new CheckableOption("o1_option2"),
							new CheckableOption("o1_option3")
					},
					new CheckableOption[] {
							new CheckableOption("o2_option1"),
							new CheckableOption("o2_option2"),
							new CheckableOption("o2_option3")
					},
					new CheckableOption[] {
							new CheckableOption("o3_option1"),
							new CheckableOption("o3_option2"),
							new CheckableOption("o3_option3")
					},
					new CheckableOption[] {
							new CheckableOption("o4_option1"),
							new CheckableOption("o4_option2"),
							new CheckableOption("o4_option3")
					}
			},
			new CheckableOption[][]{ // Local infections
					new CheckableOption[] {
							new CheckableOption("i1_option1"),
							new CheckableOption("i1_option2"),
							new CheckableOption("i1_option3")
					},
					new CheckableOption[] {
							new CheckableOption("i2_option1"),
							new CheckableOption("i2_option2"),
							new CheckableOption("i2_option3")
					},
					new CheckableOption[] {
							new CheckableOption("i3_option1"),
							new CheckableOption("i3_option2"),
							new CheckableOption("i3_option3")
					},
					new CheckableOption[] {
							new CheckableOption("i4_option1"),
							new CheckableOption("i4_option2"),
							new CheckableOption("i4_option3")
					}
			}
		};
		if(store.getData(app, "EducationOptions").size()==0 ){
			log.info("EducationOptions does not exsist, creating it");
			store.storeData(app, "EducationOptions", options);
		}
	}
}

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
package ict4mpower.childHealth.data;

import ict4mpower.childHealth.ChildHealthData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data class for the Education task
 * @author Joakim Lindskog
 *
 */
public class EducationData implements ChildHealthData {
	private static final long serialVersionUID = -5967334292372509608L;

	private static EducationData instance;
	
	public static EducationData instance() {
		if(instance == null) {
			instance = new EducationData();
		}
		return instance;
	}
	
	private List<String> feedingHeaders;
	private List<String> feedingTexts;
	private List<List<CheckableOption>> feedingOptions;
	
	private List<String> oralHeaders;
	private List<String> oralTexts;
	private List<List<CheckableOption>> oralOptions;
	
	private List<String> infectionsHeaders;
	private List<String> infectionsTexts;
	private List<List<CheckableOption>> infectionsOptions;

	private EducationData() {}
	
	public List<String> getFeedingHeaders() {
		if(feedingHeaders == null) {
			feedingHeaders = new ArrayList<String>();
		}
		return feedingHeaders;
	}
	
	public void setFeedingHeader(int index, String header) {
		if(index < getFeedingHeaders().size())
			feedingHeaders.set(index, header);
		else {
			while(feedingHeaders.size() < index) {
				feedingHeaders.add("");
			}
			feedingHeaders.add(header);
		}
	}
	
	public List<String> getFeedingTexts() {
		if(feedingTexts == null) {
			feedingTexts = new ArrayList<String>();
		}
		return feedingTexts;
	}
	
	public void setFeedingText(int index, String text) {
		if(index < getFeedingTexts().size())
			feedingTexts.set(index, text);
		else {
			while(feedingTexts.size() < index) {
				feedingTexts.add("");
			}
			feedingTexts.add(text);
		}
	}
	
	public List<List<CheckableOption>> getFeedingOptions() {
		if(feedingOptions == null) {
			feedingOptions = new ArrayList<List<CheckableOption>>();
		}
		return feedingOptions;
	}
	
	public void setFeedingOptions(List<List<CheckableOption>> options) {
		feedingOptions = options;
	}
	
	public void setFeedingOptions(int index, List<CheckableOption> options) {
		if(index < feedingOptions.size())
			feedingOptions.set(index, options);
		else {
			while(feedingOptions.size() < index) {
				feedingOptions.add(new ArrayList<CheckableOption>());
			}
			feedingOptions.add(options);
		}
	}
	
	public List<String> getOralHeaders() {
		if(oralHeaders == null) {
			oralHeaders = new ArrayList<String>();
		}
		return oralHeaders;
	}
	
	public void setOralHeader(int index, String header) {
		if(index < getOralHeaders().size())
			oralHeaders.set(index, header);
		else {
			while(oralHeaders.size() < index) {
				oralHeaders.add("");
			}
			oralHeaders.add(header);
		}
	}
	
	public List<String> getOralTexts() {
		if(oralTexts == null) {
			oralTexts = new ArrayList<String>();
		}
		return oralTexts;
	}
	
	public void setOralText(int index, String text) {
		if(index < getOralTexts().size())
			oralTexts.set(index, text);
		else {
			while(oralTexts.size() < index) {
				oralTexts.add("");
			}
			oralTexts.add(text);
		}
	}
	
	public List<List<CheckableOption>> getOralOptions() {
		if(oralOptions == null) {
			oralOptions = new ArrayList<List<CheckableOption>>();
		}
		return oralOptions;
	}
	
	public void setOralOptions(List<List<CheckableOption>> options) {
		oralOptions = options;
	}
	
	public void setOralOptions(int index, List<CheckableOption> options) {
		if(index < oralOptions.size())
			oralOptions.set(index, options);
		else {
			while(oralOptions.size() < index) {
				oralOptions.add(new ArrayList<CheckableOption>());
			}
			oralOptions.add(options);
		}
	}
	
	public List<String> getInfectionsHeaders() {
		if(infectionsHeaders == null) {
			infectionsHeaders = new ArrayList<String>();
		}
		return infectionsHeaders;
	}
	
	public void setInfectionsHeader(int index, String header) {
		if(index < getInfectionsHeaders().size())
			infectionsHeaders.set(index, header);
		else {
			while(infectionsHeaders.size() < index) {
				infectionsHeaders.add("");
			}
			infectionsHeaders.add(header);
		}
	}
	
	public List<String> getInfectionsTexts() {
		if(infectionsTexts == null) {
			infectionsTexts = new ArrayList<String>();
		}
		return infectionsTexts;
	}
	
	public void setInfectionsText(int index, String text) {
		if(index < getInfectionsTexts().size())
			infectionsTexts.set(index, text);
		else {
			while(infectionsTexts.size() < index) {
				infectionsTexts.add("");
			}
			infectionsTexts.add(text);
		}
	}
	
	public List<List<CheckableOption>> getInfectionsOptions() {
		if(infectionsOptions == null) {
			infectionsOptions = new ArrayList<List<CheckableOption>>();
		}
		return infectionsOptions;
	}
	
	public void setInfectionsOptions(List<List<CheckableOption>> options) {
		infectionsOptions = options;
	}
	
	public void setInfectionsOptions(int index, List<CheckableOption> options) {
		if(index < infectionsOptions.size())
			infectionsOptions.set(index, options);
		else {
			while(infectionsOptions.size() < index) {
				infectionsOptions.add(new ArrayList<CheckableOption>());
			}
			infectionsOptions.add(options);
		}
	}
	
	public List<EducationInfoData> getFeedingData() {
		List<EducationInfoData> list = Arrays.asList(new EducationInfoData[] {
				new EducationInfoData(feedingHeaders.get(0), feedingTexts.get(0), feedingOptions.get(0)),
				new EducationInfoData(feedingHeaders.get(1), feedingTexts.get(1), feedingOptions.get(1)),
				new EducationInfoData(feedingHeaders.get(2), feedingTexts.get(2), feedingOptions.get(2)),
				new EducationInfoData(feedingHeaders.get(3), feedingTexts.get(3), feedingOptions.get(3))
		});
		return list;
	}
	
	public List<EducationInfoData> getOralData() {
		List<EducationInfoData> list = Arrays.asList(new EducationInfoData[] {
				new EducationInfoData(oralHeaders.get(0), oralTexts.get(0), oralOptions.get(0)),
				new EducationInfoData(oralHeaders.get(1), oralTexts.get(1), oralOptions.get(1)),
				new EducationInfoData(oralHeaders.get(2), oralTexts.get(2), oralOptions.get(2)),
				new EducationInfoData(oralHeaders.get(3), oralTexts.get(3), oralOptions.get(3))
		});
		return list;
	}
	
	public List<EducationInfoData> getInfectionsData() {
		List<EducationInfoData> list = Arrays.asList(new EducationInfoData[] {
				new EducationInfoData(infectionsHeaders.get(0), infectionsTexts.get(0), infectionsOptions.get(0)),
				new EducationInfoData(infectionsHeaders.get(1), infectionsTexts.get(1), infectionsOptions.get(1)),
				new EducationInfoData(infectionsHeaders.get(2), infectionsTexts.get(2), infectionsOptions.get(2)),
				new EducationInfoData(infectionsHeaders.get(3), infectionsTexts.get(3), infectionsOptions.get(3))
		});
		return list;
	}

	public void reset() {
		setFeedingOptions(null);
		setInfectionsOptions(null);
		setOralOptions(null);
	}
	
	public EducationData clone() {
		EducationData data = new EducationData();
		data.setFeedingOptions(feedingOptions);
		data.setInfectionsOptions(infectionsOptions);
		data.setOralOptions(oralOptions);
		return data;
	}
}

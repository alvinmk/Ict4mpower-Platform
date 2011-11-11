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
package storage;

import java.io.Serializable;
import java.util.Set;

//import storage.dight.ApplicationRecord;

public class ApplicationSocket {

	public String storeData(String application, String type, Object data){
		//ApplicationRecord a = new ApplicationRecord();
		//return a.newEntry(application, type, data);
		return null;
	}
	
	public Set<Object> getData(String application, String type){
		//ApplicationRecord a = new ApplicationRecord();
		//return a.getApplicationData(application, type);
		return null;
	}
}

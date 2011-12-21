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

import java.util.Set;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;

import storage.dight.ApplicationRecord;
import storage.dight.BaseRecord;

public class ApplicationSocket {
	private static final Logger log = Logger.getLogger(ApplicationSocket.class);
	
	public String storeData(String application, String type, Object data){
		log.info("Store Data query with arguments, application:"+application +" type:"+type +" Object:" +data.toString() );
		ApplicationRecord a = new ApplicationRecord();
		return a.newEntry(application, type, data);
	}
	
	public Set<Object> getData(String application, String type){
		log.info("Get Data query with arguments, application:"+application +" type:"+type );
		ApplicationRecord a = new ApplicationRecord();
		return a.getApplicationData(application, type);
	}
}

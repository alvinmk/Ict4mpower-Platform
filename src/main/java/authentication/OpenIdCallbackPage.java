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
package authentication;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;

public class OpenIdCallbackPage extends WebPage implements IMarkupResourceStreamProvider{
	private static final long serialVersionUID = -7370038018494236607L;
	final Logger log = Logger.getLogger(OpenIdCallbackPage.class);
	
	 //THIS PART---------------------------------------------------
	  @Override
	  public final void renderPage() {
	  }
	  
	  public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {
	      return new StringResourceStream("");
	  } 
	  
	  //-------------------------IS TO GET THE webservice part to work 
	
	/*
	 * Handles callback from the openid provider.
	 */
	public OpenIdCallbackPage(PageParameters p) {
		log.info("Open ID callback page called");
		OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
		consumer.finishLogin(getRequest(), this);
	}	
}

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

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.openid4java.OpenIDException;


public class LoginPage extends WebPage {
	private static final long serialVersionUID = 5998886818992957601L;
	
	private String identity;

	/*
	 * The login page with a simple login form with just a username
	 * The login method does all the heavy lifting.
	 */
	public LoginPage() {
		add(new FeedbackPanel("feedback"));
		
		Form<?> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			protected void onSubmit() {
				login();
			}
		};
		add(form);

		form.add(new TextField<String>("identity", new PropertyModel<String>(
				this, "identity")).setRequired(true));

	}

	/*
	 * Creates a openId consumer using openid4java
	 */
	private void login() {
		try {
			OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
			consumer.startLogin(identity);
		} catch (OpenIDException e) {
			error("OpenId Authentication Failed");
		}
	}
}

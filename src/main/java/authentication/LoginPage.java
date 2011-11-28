package authentication;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.openid4java.OpenIDException;


public class LoginPage extends WebPage {
	private String identity;

	/*
	 * The login page with a simple login form with just a username
	 * The login method does all the heavy lifting.
	 */
	public LoginPage() {
		add(new FeedbackPanel("feedback"));
		
		Form<?> form = new Form<Void>("form") {
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

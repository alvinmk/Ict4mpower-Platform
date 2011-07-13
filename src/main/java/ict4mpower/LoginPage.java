package ict4mpower;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.openid4java.OpenIDException;
import ict4mpower.openid.OpenIdConsumer;


public class LoginPage extends WebPage {
	private String identity;

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

	private void login() {
		try {
			OpenIdConsumer consumer = OpenIdConsumer.get(getApplication());
			consumer.startLogin(identity);
		} catch (OpenIDException e) {
			error("OpenId Authentication Failed");
		}
	}
}

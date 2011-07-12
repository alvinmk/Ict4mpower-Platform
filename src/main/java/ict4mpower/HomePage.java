package ict4mpower;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.accordion.Accordion;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsScope;

import template.Template;
public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		final Dialog dialog = new Dialog("dialog");
		final Accordion acc1 = new Accordion("A1");
		add(dialog);
		add(acc1);
		add(new BookmarkablePageLink("linky", Template.class, new PageParameters("taskname=Task1")));

		Button button = new Button("open-dialog");
		button.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {

			@Override
			public JsScope callback() {
				return JsScope.quickScope(dialog.open().render());
			}

		}));
		add(button);
	}
}
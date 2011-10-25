package ict4mpower.childHealth;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

public interface Callback extends Serializable {
	/** Return true for a successful call or false for a failed call */
	boolean call(AjaxRequestTarget target);
}
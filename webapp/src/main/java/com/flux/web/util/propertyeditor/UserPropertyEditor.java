package com.flux.web.util.propertyeditor;

import java.beans.PropertyEditorSupport;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.flux.domain.User;

@Component
public class UserPropertyEditor extends PropertyEditorSupport {
	Map<String, User> users;

	public String getAsText() {
		String result = "empty user";

		if (getValue() != null) {
			User currentUser = (User) getValue();
			result = currentUser.getLogin();
		}
		return result;
	}

	public void setAsText(String login) {
		User currentUser = users.get(login);
		setValue(currentUser);
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}

}

package com.notifyforall.api.config.context;

import com.notifyforall.api.model.UserApp;

public class UserContext {
	
	private static UserContext instance = new UserContext();
	
	ThreadLocal<UserApp> globalUser = new ThreadLocal<UserApp>();

	private UserContext() {
	}
	
	public static UserContext getInstance() {
		return instance;
	}
	
	public UserApp getUser() {
		return globalUser.get();
	}
	
	public void setUser(UserApp userApp) {
		globalUser.set(userApp);
	}

	public void clear() {
		globalUser.remove();
	}
	
}

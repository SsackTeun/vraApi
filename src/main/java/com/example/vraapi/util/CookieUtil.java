package com.example.vraapi.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	
	private HttpServletRequest request;
	private Cookie[] cookies;
	private Cookie cookie;
	
	public CookieUtil() {
		// TODO Auto-generated constructor stub
	}	
	
	public CookieUtil(HttpServletRequest request) {
		this.cookies = request.getCookies();
	}

	public String getValue(String name) {
		
		for(int i = 0; i < this.cookies.length; i++) {
			if(cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}
	public CookieUtil addCookie(String key, String value) {
		this.cookie = new Cookie(key, value);
		return this;
	}
	
	public CookieUtil setExpire(int period) {
		this.cookie.setMaxAge(period);
		return this;
	}
	
	public CookieUtil setHttpOnly(boolean setHttpOnly) {
		this.cookie.setHttpOnly(setHttpOnly);
		return this;
	}
	
	public Cookie build() {
		return this.cookie;
	}
}

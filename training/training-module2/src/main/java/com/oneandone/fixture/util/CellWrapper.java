package com.oneandone.fixture.util;

public class CellWrapper {
	
	protected String content;
	
	public CellWrapper(String data) {
		if(data == null) {
			content = "";
		} else {
			content = data;
		}
	}
	
	public String getText() {
		return this.content;
	}
	
	public void setText(String data) {
		content = data;
	}

}

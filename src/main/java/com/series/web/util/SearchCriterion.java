package com.series.web.util;

public class SearchCriterion {
	private String key;
	private String operation;
	private Object value;
	
	public SearchCriterion() {}
	
	public SearchCriterion(final String key, final String operation, final Object value) {
//		TODO What's the purpose of calling super in this context?
//		super();
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(final String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(final String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(final Object value) {
		this.value = value;
	}
}

package com.atuigu.crm.entity;


public class Dict extends IdEntity {

	private String type;
	private String item;
	private String value;
	private boolean editable;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void save(Dict dict) {
		// TODO Auto-generated method stub
		
	}

}

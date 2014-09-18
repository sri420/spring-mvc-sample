package com.sri;

public class QueryParam {
	public QueryParam(String paramType,String paramValue) {
		this.paramType=paramType;
		this.paramValue=paramValue;
	}
	public QueryParam(int paramPosition,String paramName,String paramType,String paramValue) {
		this.paramPosition=paramPosition;
		this.paramName=paramName;
		this.paramType=paramType;
		this.paramValue=paramValue;
	}
	int paramPosition;
	public int getParamPosition() {
		return paramPosition;
	}
	public void setParamPosition(int paramPosition) {
		this.paramPosition = paramPosition;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	String paramName;
	String paramType;
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	String paramValue;
}

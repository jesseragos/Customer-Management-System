package com.cms.exceptions;

public class SQLmsgException extends java.sql.SQLException {

	private static final long serialVersionUID = 1L;
	private String msg;

	public SQLmsgException(String msg) {
		super();
		this.msg = msg;
	}

	public String getErrMsg() {
		return msg;
	}
	
}

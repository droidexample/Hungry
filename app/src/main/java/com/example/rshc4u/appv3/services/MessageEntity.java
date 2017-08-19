package com.example.rshc4u.appv3.services;

public class MessageEntity {

	public String ID;
	public String Message;
	public String StartTime;
	public String EndTime;
	public String URL;

	// SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
	// String format = s.format(new Date());

	MessageEntity(String _ID, String _Message, String _StartTime, String _EndTime, String _URL) {
		Message = _Message;
		StartTime = _StartTime;
		EndTime = _EndTime;
		URL= _URL;
		ID =_ID;
	}
}

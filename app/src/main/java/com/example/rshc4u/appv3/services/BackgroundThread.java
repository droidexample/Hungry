package com.example.rshc4u.appv3.services;

import android.content.Context;

import com.example.rshc4u.appv3.R;

import java.util.List;

public class BackgroundThread implements Runnable {

	private Context appContext;
	private HTTPManager manager;
	private NotificationBL notificationbl;
	
	
	public BackgroundThread(Context applicationContext) {
		appContext = applicationContext;
		manager = new HTTPManager(applicationContext);
		notificationbl = new NotificationBL(applicationContext);
	}

	@Override
	public void run() {
		
		while(true)
		{
			List<MessageEntity> allMessages = manager.getAllNotifications();
			
			
		//	allMessages = new ArrayList<MessageEntity>(); 
		//	MessageEntity entity = new MessageEntity("1","Hiren", "02052013090802", "04052013230802", "http://www.google.com");
		//	allMessages.add(entity);
		//	  entity = new MessageEntity("2","Rakesh", "02052013090802", "04052013230802", "http://www.google.com");
		//	allMessages.add(entity);
			
			notificationbl.processAndNotify(allMessages);
			
			
			/*	Sleep	*/
			try {
				Thread.sleep(1000*60* Integer.parseInt(appContext.getString(R.string.TimeIntervalInSeconds)));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}

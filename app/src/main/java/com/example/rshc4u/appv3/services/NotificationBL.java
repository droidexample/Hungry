package com.example.rshc4u.appv3.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NotificationBL {

    Context appContext;
    private static String SharedPrefName = "menubus";
    private NotificationManager mNM;


    public NotificationBL(Context applicationContext) {
        appContext = applicationContext;
        mNM = (NotificationManager) appContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void processAndNotify(List<MessageEntity> allMessages) {
        if (allMessages == null)
            return;

        List<MessageEntity> messageToBeShown = filterMessagesIfShown(allMessages);

        if (messageToBeShown == null)
            return;

        notifyMessage(messageToBeShown);
    }


    private void notifyMessage(List<MessageEntity> allMessages) {

        for (int i = 0; i < allMessages.size(); i++) {
            showNotification(allMessages.get(i));
        }


        addNotifiedMessageToSharedPref(allMessages);
    }


	/*
    private void showNotification(MessageEntity entity) {

        Notification notification = new Notification(R.drawable.ic_notify, entity.Message, System.currentTimeMillis());

		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		// enable to bug for notification below
		// notification.flags |= Notification.FLAG_INSISTENT;
        
        Intent notificationIntent = new Intent(appContext, MainActivity.class);
        
        notificationIntent.putExtra("URL", entity.URL);
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(appContext, 0,
        		notificationIntent, 0);
       
           
        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(appContext, appContext.getString(R.string.app_name),  entity.Message, contentIntent);

        // Send the notification.
        mNM.notify((int) new Random().nextInt(50000), notification);
    }

*/


    private void showNotification(MessageEntity entity) {

        int icon = R.drawable.ic_notify;
        CharSequence text = "Your notification time is upon us.";

        long time = System.currentTimeMillis();

        PendingIntent contentIntent = PendingIntent.getActivity(appContext, 0, new Intent(appContext, MainActivity.class), 0);

        Notification.Builder builder = new Notification.Builder(appContext)
                .setSmallIcon(icon)
                .setContentTitle(entity.Message)
                .setContentText(text)
                .setContentIntent(contentIntent);
        Notification notification = builder.build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        mNM.notify((int) new Random().nextInt(50000), notification);


    }


    private void addNotifiedMessageToSharedPref(List<MessageEntity> allMessages) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        java.util.Date currentDate = new java.util.Date();

        try {
            SharedPreferences preferences = appContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            for (int i = 0; i < allMessages.size(); i++) {
                editor.putString(allMessages.get(i).ID, sdf.format(currentDate));
            }

            editor.commit();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private List<MessageEntity> filterMessagesIfShown(List<MessageEntity> allMessages) {

        //SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        //java.util.Date currentDate=new java.util.Date();


//		List<MessageEntity> filterMessages =  new ArrayList<MessageEntity>(); 
//		for(int i=0;i<allMessages.size();i++)
//		{
//        	java.util.Date endDate,startDate;
//			try {
//				
//				startDate=sdf.parse(allMessages.get(i).StartTime);
//				endDate = sdf.parse(allMessages.get(i).EndTime);
//						
//					if(currentDate.compareTo(startDate)>=0 && currentDate.compareTo(endDate)<=0 )
//					{
//		        		filterMessages.add(allMessages.get(i));
//					}		 
//				 
//         
//        	
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}


        List<MessageEntity> filterMessages = removeifAlreadyRead(allMessages);
        removeUnwantedNotificatins();

        return filterMessages;

    }

    private List<MessageEntity> removeifAlreadyRead(List<MessageEntity> allMessages) {


        List<MessageEntity> filterMessages = new ArrayList<MessageEntity>();


        SharedPreferences preferences = appContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);

        for (int i = 0; i < allMessages.size(); i++) {

            if (preferences.getString(allMessages.get(i).ID, null) == null) {

                filterMessages.add(allMessages.get(i));
            }
//			else
//			{
//				String value = preferences.getString(allMessages.get(i).ID, null);
//				String merged = allMessages.get(i).Message+allMessages.get(i).URL;
//				if(!value.equals(merged))
//				{
//					filterMessages.add(allMessages.get(i));
//				}
//			}
        }


        return filterMessages;
    }


    private void removeUnwantedNotificatins() {

        try {


            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
            java.util.Date currentDate = new java.util.Date();


            SharedPreferences preferences = appContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            Map<String, ?> values = preferences.getAll();
            Set<String> key = values.keySet();


            Iterator<String> it = key.iterator();
            while (it.hasNext()) {
                String keyVal = (String) it.next();
                String myDate = preferences.getString(keyVal, null);
                if (myDate != null) {
                    java.util.Date keyDate = sdf.parse(myDate);

                    Calendar calendar1 = Calendar.getInstance();
                    Calendar calendar2 = Calendar.getInstance();
                    calendar1.set(keyDate.getDate(), keyDate.getMonth(), keyDate.getYear(), keyDate.getHours(), keyDate.getMinutes(), keyDate.getSeconds());
                    calendar2.set(currentDate.getDate(), currentDate.getMonth(), currentDate.getYear(), currentDate.getHours(), currentDate.getMinutes(), currentDate.getSeconds());
                    long milliseconds1 = calendar1.getTimeInMillis();
                    long milliseconds2 = calendar2.getTimeInMillis();
                    long diff = milliseconds2 - milliseconds1;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    if (diffDays > Integer.parseInt(appContext.getString(R.string.NoOfDaysAfterClearNotificaiton))) {
                        editor.remove(keyVal);
                    }

                }
            }

            editor.commit();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}

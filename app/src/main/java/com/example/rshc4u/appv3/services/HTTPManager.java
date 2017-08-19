package com.example.rshc4u.appv3.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.rshc4u.appv3.R;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class HTTPManager {

	private Context appContext;
	BasicClientCookie cookie;
	HTTPManager(Context context)
	{
		appContext = context;
	}
	private Document getNotifications()
	{

		String SharedPrefName = "menubus";
		SharedPreferences preferences = appContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
		String notifierId  = preferences.getString("notifier-id", "");


		String notificationURL = appContext.getString(R.string.NotificationURL) +"?notifier-id=" + notifierId;
		
		try {
			HttpGet uri = new HttpGet(notificationURL);

			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse resp = client.execute(uri);

			StatusLine status = resp.getStatusLine();
			if (status.getStatusCode() != 200) {
			    Log.d("HTTPManager", "HTTP error, invalid server status code: " + resp.getStatusLine());
			}

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(resp.getEntity().getContent());
			return doc;
		} catch (Exception e) {
			Log.e("MenuBus",e.getMessage());
		}
		
		return null;
	}
	
	@SuppressLint("NewApi")
	private List<MessageEntity> convertXMLintoList(Document doc)
	{
		
		if(doc==null)
		{
			return null;
		}
		
		try
		{
			NodeList nList = doc.getElementsByTagName("Notification");
			if(nList==null)
			{
				return null;
			}
			List<MessageEntity> allMessages = new ArrayList<MessageEntity>();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				String ID=null,Message = null,StartTime = null,EndTime = null,URL = null;
				
				Node nNode = nList.item(temp);
		 
				NodeList notificatioNodes = nNode.getChildNodes();
 
				for(int j=0;j<notificatioNodes.getLength();j++)
				{
					
					Node childNode = notificatioNodes.item(j);
					if(childNode.getNodeType()== Node.ELEMENT_NODE)
					{
 
						  
						if(childNode.getNodeName().equals("id"))
						{
							ID = childNode.getFirstChild().getNodeValue();
							
						}else if(childNode.getNodeName().equals("Message"))
						{
							Message = childNode.getFirstChild().getNodeValue();
							
						}else if(childNode.getNodeName().equals("StartTime"))
						{
							StartTime = childNode.getFirstChild().getNodeValue();
						}else if(childNode.getNodeName().equals("EndTime"))
						{
							EndTime = childNode.getFirstChild().getNodeValue();
						}else if(childNode.getNodeName().equals("URL"))
						{
							URL = childNode.getFirstChild().getNodeValue();
						}
					}
					
				}
				MessageEntity message = new MessageEntity(ID,Message,StartTime ,EndTime,URL);
				allMessages.add(message);
				 
			}
			

		 
			return allMessages;
		}
		catch (Exception e) {
			return null;
		}
		
		
		
	}

	public List<MessageEntity> getAllNotifications()
	{
		Document xmlDoc = getNotifications();
		
		return convertXMLintoList(xmlDoc);
		
		
	}
}

package com.jaliyaninfotech.newtry;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements ServerCallAsyncTask.OnAsyncJSONResponse{
	private static final String AUTH_KEY = "key=AIzaSyDdBKWmaCPqDY8HUn4FfC3wZP8OhUsQ3RQ";
	private static final int SAVE_TOKEN = 1;
	private static final int SEND_NOTIFICATION = 2;
	private TextView mTextView;
	JSONArray ja;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTextView = (TextView) findViewById(R.id.txt);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String tmp = "";
			for (String key : bundle.keySet()) {
				Object value = bundle.get(key);
				tmp += key + ": " + value + "\n\n";
			}
			mTextView.setText(tmp);
		}

		String url="http://smarttrafficalert.16mb.com/includes/getData.php?savetoken";
		HashMap<String,String> hm=new HashMap<>();
		hm.put("token",FirebaseInstanceId.getInstance().getToken());
		ServerCallAsyncTask asyncTask=new ServerCallAsyncTask(MainActivity.this,url,hm,SAVE_TOKEN);
		asyncTask.execute();
	}

	public void send(View view)
	{
		String url="http://smarttrafficalert.16mb.com/includes/getData.php?getToken";
		ServerCallAsyncTask asyncTask=new ServerCallAsyncTask(MainActivity.this,url,null,SEND_NOTIFICATION);
		asyncTask.execute();
	}

	public void showToken(View view) {
		mTextView.setText(FirebaseInstanceId.getInstance().getToken());
		Log.i("token", FirebaseInstanceId.getInstance().getToken());
	}

	public void subscribe(View view) {
		FirebaseMessaging.getInstance().subscribeToTopic("news");
		mTextView.setText(R.string.subscribed);
	}

	public void unsubscribe(View view) {
		FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
		mTextView.setText(R.string.unsubscribed);
	}

	public void sendToken(View view) {
		sendWithOtherThread("token");
	}

	public void sendTokens(View view) {
		sendWithOtherThread("tokens");
	}

	public void sendTopic(View view) {
		sendWithOtherThread("topic");
	}

	private void sendWithOtherThread(final String type) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				pushNotification(type);
			}
		}).start();
	}

	private void pushNotification(String type) {
		JSONObject jPayload = new JSONObject();
		JSONObject jNotification = new JSONObject();
		JSONObject jData = new JSONObject();
		try {
			jNotification.put("title", "Google I/O 2016");
			jNotification.put("body", "Firebase Cloud Messaging (App)");
			jNotification.put("sound", "default");
			jNotification.put("badge", "1");
			jNotification.put("click_action", "OPEN_ACTIVITY_1");

			jData.put("picture_url", "http://opsbug.com/static/google-io.jpg");

			switch(type) {
				case "tokens":
					JSONArray ja = new JSONArray();
					ja.put("c5pBXXsuCN0:APA91bH8nLMt084KpzMrmSWRS2SnKZudyNjtFVxLRG7VFEFk_RgOm-Q5EQr_oOcLbVcCjFH6vIXIyWhST1jdhR8WMatujccY5uy1TE0hkppW_TSnSBiUsH_tRReutEgsmIMmq8fexTmL");
					ja.put(FirebaseInstanceId.getInstance().getToken());
					jPayload.put("registration_ids", ja);
					break;
				case "topic":
					jPayload.put("to", "/topics/news");
					break;
				case "condition":
					jPayload.put("condition", "'sport' in topics || 'news' in topics");
					break;
				default:
					jPayload.put("to", FirebaseInstanceId.getInstance().getToken());
			}

			jPayload.put("priority", "high");
			jPayload.put("notification", jNotification);
			jPayload.put("data", jData);

			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", AUTH_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(jPayload.toString().getBytes());

			// Read FCM response.
			InputStream inputStream = conn.getInputStream();
			final String resp = convertStreamToString(inputStream);

			Handler h = new Handler(Looper.getMainLooper());
			h.post(new Runnable() {
				@Override
				public void run() {
					mTextView.setText(resp);
				}
			});
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	private String convertStreamToString(InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next().replace(",", ",\n") : "";
	}

	@Override
	public void asyncGetSMSResponse(String response, int flag) {
		if(flag==SAVE_TOKEN)
		{
			Toast.makeText(this, "Response : "+response, Toast.LENGTH_SHORT).show();
		}
		if(flag==SEND_NOTIFICATION)
		{
			try {
				JSONArray jsonArray=new JSONArray(response);
				ja = new JSONArray();
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					ja.put(jsonObject.getString("token"));
				}
				new Thread(new Runnable() {
					@Override
					public void run() {
						JSONObject jPayload = new JSONObject();
						JSONObject jNotification = new JSONObject();
						JSONObject jData = new JSONObject();
						try {
							jNotification.put("title", "Google I/O 2016");
							jNotification.put("body", "Firebase Cloud Messaging (App)");
							jNotification.put("sound", "default");
							jNotification.put("badge", "1");
							jNotification.put("click_action", "OPEN_ACTIVITY_1");

							jData.put("picture_url", "http://opsbug.com/static/google-io.jpg");
							jPayload.put("registration_ids", ja);

							jPayload.put("priority", "high");
							jPayload.put("notification", jNotification);
							jPayload.put("data", jData);

							URL url = new URL("https://fcm.googleapis.com/fcm/send");
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("POST");
							conn.setRequestProperty("Authorization", AUTH_KEY);
							conn.setRequestProperty("Content-Type", "application/json");
							conn.setDoOutput(true);

							// Send FCM message content.
							OutputStream outputStream = conn.getOutputStream();
							outputStream.write(jPayload.toString().getBytes());

							// Read FCM response.
							InputStream inputStream = conn.getInputStream();
							final String resp = convertStreamToString(inputStream);

							Handler h = new Handler(Looper.getMainLooper());
							h.post(new Runnable() {
								@Override
								public void run() {
									mTextView.setText(resp);
								}
							});
						} catch (JSONException | IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
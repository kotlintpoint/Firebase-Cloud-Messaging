package com.example.android1to3.xmlparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android1to3.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class PullParsingActivity extends AppCompatActivity {
    ArrayList<News> newsList;
    News news;
    String text;
    String url="http://feeds.feedburner.com/ndtvnews-india-news";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_parsing);

        //doPullParsing();
        listView=findViewById(R.id.listView);
        new NewsAsyncTask().execute();
    }

    class NewsAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            doPullParsing();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayAdapter<News> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, newsList);
            listView.setAdapter(adapter);
        }
    }

    private void doPullParsing() {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            InputStream input = new URL(url).openConnection().getInputStream();
            xpp.setInput(input,null);
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    newsList=new ArrayList<>();
                    System.out.println("Start document");
                } else if (eventType == XmlPullParser.START_TAG) {
                    String name=xpp.getName();
                    if(name.equals("item")){
                        news=new News();
                    }

                    System.out.println("Start tag " + xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag " + xpp.getName());
                    String name=xpp.getName();
                    if(name.equals("item")){
                        newsList.add(news);
                    }else if(name.equals("title")){
                        if(news!=null)
                            news.setTitle(text);
                    }
                    else if(name.equals("description")){
                        if(news!=null)
                            news.setDescription(text);
                    }
                    else if(name.equals("link")){
                        if(news!=null)
                            news.setLink(text);
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    text=xpp.getText();
                    System.out.println("Text " + xpp.getText());
                }
                eventType = xpp.next();
            }
            System.out.println("End document");
        } catch (XmlPullParserException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}


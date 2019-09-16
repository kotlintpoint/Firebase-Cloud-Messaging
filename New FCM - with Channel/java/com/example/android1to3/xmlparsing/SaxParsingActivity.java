package com.example.android1to3.xmlparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android1to3.R;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParsingActivity extends AppCompatActivity {
    private String url="http://feeds.feedburner.com/ndtvnews-india-news";
    ArrayList<News> newsList;
    News news;
    String text;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sax_parsing);
        listView=findViewById(R.id.listView);
        new MyNewsAsyncTask().execute();
    }

    class MyNewsAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            doXmlParsing();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayAdapter<News> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, newsList);
            listView.setAdapter(adapter);
        }
    }

    private void doXmlParsing() {
        newsList=new ArrayList<>();
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            SAXParser parser=factory.newSAXParser();
            DefaultHandler handler=new DefaultHandler(){
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    if(localName.equals("title")){
                        news=new News();
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    if(localName.equals("title")){
                        if(news!=null)
                            news.setTitle(text);
                    }
                    else if(localName.equals("description")){
                        if(news!=null) {
                            news.setDescription(text);
                            newsList.add(news);
                        }
                    }
                    else if(localName.equals("link")){
                        if(news!=null)
                            news.setLink(text);
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    text=new String(ch, start, length);
                }
            };
            parser.parse(url,handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

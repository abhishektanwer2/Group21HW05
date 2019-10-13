package com.example.group21hw05;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetNewsAsync extends AsyncTask<String, Void, ArrayList<News>> {

    InewsData newsdata;
    public GetNewsAsync(InewsData newsdata) {
       this.newsdata=newsdata;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<News> news) {
        newsdata.getNewsData(news);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(ArrayList<News> news) {
        super.onCancelled(news);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        HttpURLConnection connection;
        StringBuilder stringBuilder;
        BufferedReader reader ;
ArrayList<News> newsArrayList=new ArrayList<>();
        stringBuilder = new StringBuilder();
        connection = null;
        reader = null;
        String result = null;

        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
                JSONObject jsonResult = new JSONObject(result);
                JSONArray jsonArray = jsonResult.getJSONArray("articles");
                for (int i = 0; i < jsonArray.length(); i++) {
                   // Log.d("result", jsonArray.get(i).toString());
                    JSONObject newsJSON = jsonArray.getJSONObject(i);
                    News news = new News();
                    news.title = newsJSON.getString("title");
                    news.author = newsJSON.getString("author");
                    news.urlToImage=newsJSON.getString("urlToImage");
                    news.publishedAt=newsJSON.getString("publishedAt");
                    news.url=newsJSON.getString("url");
                    newsArrayList.add(news);

                }
            }

        }
        //Handle the exceptions
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            //Close open connections and reader
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("demo", result);





        return newsArrayList;
    }
    public static interface InewsData{
        public void getNewsData(ArrayList<News> newsArrayList);
    }
}

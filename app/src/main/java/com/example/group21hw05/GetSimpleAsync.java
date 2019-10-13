package com.example.group21hw05;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class GetSimpleAsync extends AsyncTask <String, Void,ArrayList<Source>>{

    Idata idata;
    public GetSimpleAsync(Idata idata) {
        this.idata=idata;
    }
ArrayList<Source>resultsource=new ArrayList<>();
    @Override
    protected ArrayList<Source> doInBackground(String... strings) {
        HttpURLConnection connection;
        StringBuilder stringBuilder;
        BufferedReader reader ;

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
                    JSONArray jsonArray = jsonResult.getJSONArray("sources");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("result", jsonArray.get(i).toString());
                        JSONObject sourceJSON = jsonArray.getJSONObject(i);
                        Source source = new Source();
                        source.id = sourceJSON.getString("id");
                        source.name = sourceJSON.getString("name");


                        resultsource.add(source);

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




        /*HttpURLConnection connection = null;
        String result = null;
        try {
            String strUrl = "http://api.theappsdr.com/params.php" + "?" +
                    "param1=" + URLEncoder.encode("value 1", "UTF-8") + "&" +
                    "param1=" + URLEncoder.encode("value 2", "UTF-8") + "&" +
                    "param1=" + URLEncoder.encode("value 3", "UTF-8");
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //result = IOUtils.toString(connection.getInputStream(), "UTF8");
            }
        }
        catch ( UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            //Handle the exceptions
         finally {
            //Close open connections and reader
        }
        return result;
        return null;*/
        return resultsource;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Source> sources) {
     idata.handledata(sources);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

public static interface Idata{
        public void handledata(ArrayList<Source> sources);

}


}

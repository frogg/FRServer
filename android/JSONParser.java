package larissa.com.kantine.controller;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Larissa Laich on 11.11.14.
 */
public abstract class JSONParser extends AsyncTask<String, Void, JSONArray> {

    protected void onPreExecute(){
       // Log.d("LogLari","is called before doInBackground");
    }
    protected void onPostExecute(JSONArray jArr){
       // Log.d("LogLari","is called after doInBackground");
        if (jArr == null){
            //if json could not be loaded
            JSONNotLoaded();
        }else {
            onJSONLoaded(jArr);
        }
    }
    //abstract methods to be implemented in sublcass
    public abstract void onJSONLoaded(JSONArray jArr);
    public abstract void JSONNotLoaded();

@Override
    protected JSONArray doInBackground(String... urls) {
    JSONArray jObj = null;
    try {
            //for GET Methods
            HttpGet httpGet = new HttpGet(urls[0]);
            //utf-8 important for umlaute
            httpGet.setHeader("Content-Type", "text/html; charset=utf-8");
            httpGet.setHeader("Accept", "application/json");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            // if message entity exits => get it here
            HttpEntity httpEntity = httpResponse.getEntity();
            // get InputStream object of the entity
            InputStream is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line + "n");
                line = reader.readLine();
            }
            is.close();
             String json = sb.toString();
            // try parse the string to a JSON object
            //[ = JSONArray && { = JSONObject, aufpassen welches Object falsch ist
            //JSONObject is an unordered collection of name/value pairs
             jObj= new JSONArray(json);
            //Log.d("LogLari","LogJSON" +json);

        } catch (Exception e) {
            Log.e("LogLari", "Error creating JSON, try  jObj= new JSONObject(json);" + e.toString());
            return null;
        }
        return jObj;
    }
}


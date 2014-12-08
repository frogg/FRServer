package project.server;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Larissa Laich on 11.11.14.
 */
public abstract class JSONParser extends AsyncTask<String, Void, JSONObject> {

    protected void onPreExecute(){
       // Log.d("LogLari","is called before doInBackground");
    }
    protected void onPostExecute(JSONObject jObj){
       // Log.d("LogLari","is called after doInBackground");
        if (jObj == null){
            //if json could not be loaded
            JSONNotLoaded();
        }else {
            onJSONLoaded(jObj);
        }
    }
    //abstract methods to be implemented in sublcass
    public abstract void onJSONLoaded(JSONObject jObj);
    public abstract void JSONNotLoaded();

@Override
    protected JSONObject doInBackground(String... transfer) {
    JSONObject jObj = null;
    try {
        HttpResponse httpResponse = null;
            if(transfer[1].equals("GET")) {
            httpResponse = GETResponse(transfer[0]);
            }else if(transfer[1].equals("POST")){
            httpResponse = POSTResponse(transfer[0]);
            }
             // if message entity exits => get it here => in our example feedback json
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line); // if it is a JSONArray: use sb.append(line + "n");
                line = reader.readLine();
            }
            is.close();
            String json = sb.toString();
            // try parse the string to a JSON object
                //[ = JSONArray && { = JSONObject, aufpassen welches Object falsch ist
            //JSONObject is an unordered collection of name/value pairs
            jObj= new JSONObject(json); // if you get a JSONArray use: jObj= new JSONArray(json);
        } catch (Exception e) {
            Log.e("LogLari", "Error creating JSON, try  jObj= new JSONObject(json);" + e.toString());
            return null;
        }
        return jObj;
    }

    private HttpResponse GETResponse(String url) throws IOException {
        //for GET Methods
        HttpGet httpGet = new HttpGet(url);
        //utf-8 important for umlaute
        httpGet.setHeader("Content-Type", "text/html; charset=utf-8");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        return httpClient.execute(httpGet);
    }

    private HttpResponse POSTResponse(String url) throws IOException{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //set additional information like this => probably better in a own method for general use
        /*List<NameValuePair> postInfo = new ArrayList<NameValuePair>();
        postInfo.add(new BasicNameValuePair("lastName", "User"));
        post.setEntity(new UrlEncodedFormEntity(postInfo));
        */
        //other idea hand JSON within url[2] for this information, e.g jsonString
        //alternative, post whole dictionary at once
        // evtl. sehr nützlich Gson Java library (can convert Java Objects into their JSON representation or jsonString ot an equivalent Java object.
        //String json = new GsonBuilder().create().toJson(comment, Map.class);
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "test");
            jsonObject.put("lastName", "Wabu");
            Log.d("LogsLari", "hi" + jsonObject.toString());
            StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
            stringEntity.setContentType("application/json");
            post.setEntity(stringEntity);

        }catch(Exception e){
            e.printStackTrace();
        }


        return client.execute(post);
    }

}



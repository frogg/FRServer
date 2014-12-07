package project.server;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;


public class test_activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);

            JSONParser json = new JSONParser() {
            @Override
            public void onJSONLoaded(JSONObject jObj) {
            Log.d("LogsLar", jObj.toString());
                try {
                    //to get the values for keys
                    Log.d("LogsLar", jObj.getString("get"));
                    Log.d("LogsLar", jObj.getString("hi"));
                }catch(Exception e){

                }
            }
            @Override
            public void JSONNotLoaded() {
            Log.d("LogsLar","JSONNotLoaded");
            }
        };
        //if you are working with the python test server, run it and put your IP address here
        json.execute("http://192.168.178.22:5000/test/get");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

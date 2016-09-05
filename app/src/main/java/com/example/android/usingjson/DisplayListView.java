package com.example.android.usingjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    contactAdapter contactAdapter1;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        json_string = getIntent().getExtras().getString("jason_data");
        contactAdapter1 = new contactAdapter(this, R.layout.row_layout);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(contactAdapter1);


        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("objects");
            int count = 0;
            String firstname, lastname, party;

            while (count<jsonArray.length())
            {
                JSONObject jo = jsonArray.getJSONObject(count);
                party = jo.getString("party");
                JSONObject person = jo.getJSONObject("person");
                firstname = person.getString("name");
                lastname = person.getString("gender");

                contacts contacts1 = new contacts(firstname,lastname,party);
                contactAdapter1.add(contacts1);

                count++;

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

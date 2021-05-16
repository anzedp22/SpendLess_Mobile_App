package si.uni_lj.fe.tnuv.spendless;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.net.URL;


public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
    EditText GetValue;
    EditText GetValue1;
    EditText GetValue2;
    ImageButton delete_button;
    RequestQueue requestQueue1;
    TextView ID_nakupa_text;


    List<Nakup> nakupi = new ArrayList();
    public List<String> ponudnik1 = new ArrayList <String>();
    public List<String> datum1 = new ArrayList <String>();
    public List<Double> cena2 = new ArrayList <Double>();
    private static final String BASE_url = "http://92.37.74.98:54321/Spendless/API.php";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        /*mToolbar.setTitle(getResources().getString(R.string.app_name));*/
        mListView = (ListView) findViewById(R.id.listview);
        GetValue = (EditText) findViewById(R.id.editText1);
        GetValue1 = (EditText) findViewById(R.id.date);
        GetValue2 = (EditText) findViewById(R.id.price);
        delete_button = (ImageButton) findViewById(R.id.delete);
        ID_nakupa_text = (TextView) findViewById(R.id.ID_nakupa);

        requestQueue1 = Volley.newRequestQueue(getApplicationContext());

        getNakupi();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.seznam);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.seznam:
                        return true;
                    case R.id.dodaj:
                        startActivity(new Intent(MainActivity.this, Categories.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.graf:
                        startActivity(new Intent(MainActivity.this, Graph.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    private void getNakupi (){

        /*podatki iz baze*/

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for(int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                String ponudnik = object.getString("ponudnik");
                                String datum = object.getString("datum");
                                double cena = object.getDouble("cena");
                                int ID_nakupa = object.getInt("ID_nakupa");

                                nakupi.add(new Nakup(ponudnik, datum, cena, ID_nakupa));

                                ponudnik1.add(ponudnik);
                                System.out.println(ponudnik1.toString());
                                datum1.add(datum);
                                System.out.println(datum.toString());
                                cena2.add(cena);
                                System.out.println(cena2.toString());

                                Log.d("myTag", "This is my message");
                                System.out.println(nakupi.toString());

                            }

                        }catch (Exception e) {

                        }

                        // Adapter3

                        MyAdapter3 mAdapter5 = new MyAdapter3(MainActivity.this, nakupi);

                        mListView.setAdapter(mAdapter5);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);
    }

}






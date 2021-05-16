package si.uni_lj.fe.tnuv.spendless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Categories extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
    Button Addbutton;
    EditText ponudnik;
    EditText date;
    String sql_date;
    EditText price;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;
    ImageButton imageButton6;
    ImageButton imageButton7;
    ImageButton imageButton8;
    ImageButton imageButton9;

    DatePickerDialog picker;

    Integer category;

    RequestQueue requestQueue;

    String insertUrl = "http://92.37.74.98:54321/Spendless/API.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Addbutton = (Button) findViewById(R.id.mainButton);
        ponudnik = (EditText) findViewById(R.id.editText1);
        date = (EditText) findViewById(R.id.date);
        price = (EditText) findViewById(R.id.price);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        imageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        imageButton8 = (ImageButton) findViewById(R.id.imageButton8);
        imageButton9 = (ImageButton) findViewById(R.id.imageButton9);

        date.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        price.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Categories.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                sql_date  = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                System.out.println(sql_date);
                            }
                        }, year, month, day);
                picker.show();

            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.dodaj);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dodaj:
                        return true;
                    case R.id.seznam:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.graf:
                        startActivity(new Intent(getApplicationContext(), Graph.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Hrana.", Toast.LENGTH_SHORT).show();
                category = 1;

            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Pijača.", Toast.LENGTH_SHORT).show();
                category = 2;

            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Živila.", Toast.LENGTH_SHORT).show();
                category = 3;

            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Oblačila.", Toast.LENGTH_SHORT).show();
                category = 4;

            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Prevoz.", Toast.LENGTH_SHORT).show();
                category = 5;

            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Zabava.", Toast.LENGTH_SHORT).show();
                category = 6;

            }
        });
        imageButton8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Darila.", Toast.LENGTH_SHORT).show();
                category = 7;

            }
        });
        imageButton9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Izbrali ste kategorijo Ostalo.", Toast.LENGTH_SHORT).show();
                category = 8;

            }
        });


        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(ponudnik.getText().toString()) || "".equals(date.getText().toString()) || "".equals(price.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Vstavi vrednosti v vsa polja!.", Toast.LENGTH_SHORT).show();
                }

                else {
                    StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.toString());
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("ponudnik", ponudnik.getText().toString());
                            parameters.put("datum", sql_date);
                            parameters.put("cena", String.valueOf(price.getText()));
                            parameters.put("ID_kategorije", category.toString());

                            return parameters;

                        }

                    };


                    System.out.println(request.toString());
                    requestQueue.add(request);

                    Toast.makeText(getApplicationContext(), "Podatki so bili uspešno vpisani.", Toast.LENGTH_SHORT).show();
                }

                ponudnik.setText("");
                date.setText("");
                price.setText("");
            }

        });
    }
}

















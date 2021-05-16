package si.uni_lj.fe.tnuv.spendless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.BasePieLegendsView;
import com.razerdp.widget.animatedpieview.DefaultCirclePieLegendsView;
import com.razerdp.widget.animatedpieview.DefaultPieLegendsView;
import com.razerdp.widget.animatedpieview.callback.OnPieLegendBindListener;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    String BASE_url2 = "http://92.37.74.98:54321/Android/getPodatki.php";
    RequestQueue requestQueue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        requestQueue1 = Volley.newRequestQueue(getApplicationContext());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.graf);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dodaj:
                        startActivity(new Intent(getApplicationContext(), Categories.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.seznam:
                        startActivity(new Intent(Graph.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.graf:
                        return true;
                }
                return false;
            }
        });

        drawPie();
    }

    private void drawPie() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for(int i = 0; i<array.length(); i++){

                                JSONObject object = array.getJSONObject(i);

                                double sum_cena1 = object.getDouble("sum_cena1");
                                double sum_cena2 = object.getDouble("sum_cena2");
                                double sum_cena3 = object.getDouble("sum_cena3");
                                double sum_cena4 = object.getDouble("sum_cena4");
                                double sum_cena5 = object.getDouble("sum_cena5");
                                double sum_cena6 = object.getDouble("sum_cena6");
                                double sum_cena7 = object.getDouble("sum_cena7");
                                double sum_cena8 = object.getDouble("sum_cena8");

                                System.out.println(sum_cena1);

                                AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
                                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                                config.startAngle(0)// Starting angle offset
                                        .addData(new SimplePieInfo(sum_cena1, Color.parseColor("#f29b1d"), "HRANA"))
                                        .addData(new SimplePieInfo(sum_cena2, Color.parseColor("#f47e55"), "PIJAČA"))
                                        .addData(new SimplePieInfo(sum_cena3, Color.parseColor("#00a99a"), "ŽIVILA"))
                                        .addData(new SimplePieInfo(sum_cena4, Color.parseColor("#62ccf5"), "OBLAČILA"))//Data (bean that implements the IPieInfo interface)
                                        .addData(new SimplePieInfo(sum_cena5, Color.parseColor("#ffb7ae"), "PREVOZ"))
                                        .addData(new SimplePieInfo(sum_cena6, Color.parseColor("#19A979"), "ZABAVA"))
                                        .addData(new SimplePieInfo(sum_cena7, Color.parseColor("#6C8893"), "DARILA"))
                                        .addData(new SimplePieInfo(sum_cena8, Color.parseColor("#EE6868"), "OSTALO")).drawText(true).strokeMode(true).floatExpandSize(15).textSize(30).textGravity(AnimatedPieViewConfig.ABOVE).strokeWidth(40).floatShadowRadius(1f).autoSize(true).guideLineWidth(15).splitAngle(2).focusAlpha(1).duration(1500);

                                TextView tv;
                                tv = (TextView) findViewById(R.id.textView0);
                                tv.setText(String.valueOf(sum_cena1) + "€");
                                tv = (TextView) findViewById(R.id.textView2);
                                tv.setText(String.valueOf(sum_cena2) + "€");
                                tv = (TextView) findViewById(R.id.textView3);
                                tv.setText(String.valueOf(sum_cena3) + "€");
                                tv = (TextView) findViewById(R.id.textView4);
                                tv.setText(String.valueOf(sum_cena4) + "€");
                                tv = (TextView) findViewById(R.id.textView5);
                                tv.setText(String.valueOf(sum_cena5) + "€");
                                tv = (TextView) findViewById(R.id.textView6);
                                tv.setText(String.valueOf(sum_cena6) + "€");
                                tv = (TextView) findViewById(R.id.textView7);
                                tv.setText(String.valueOf(sum_cena7) + "€");
                                tv = (TextView) findViewById(R.id.textView8);
                                tv.setText(String.valueOf(sum_cena8) + "€");


                                mAnimatedPieView.start (config);

                            }

                        }catch (Exception e) {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Graph.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(Graph.this).add(stringRequest);

    }
}
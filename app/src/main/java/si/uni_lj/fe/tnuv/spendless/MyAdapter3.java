package si.uni_lj.fe.tnuv.spendless;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter3 extends BaseAdapter {

    Context context;
    List<Nakup> nak;
    ImageButton delete_button;
    private static final String BASE_url1 = "http://92.37.74.98:54321/Spendless/API.php?ID_nakupa=";
    RequestQueue requestQueue1;

    // Create constructor

    public MyAdapter3(Context context, List<Nakup> nak) {
        this.context = context;
        this.nak = nak;
    }

    @Override
    public int getCount() {
        return nak.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.rowdesign, parent, false);

        requestQueue1 = Volley.newRequestQueue(context.getApplicationContext());

        delete_button = (ImageButton) convertView.findViewById(R.id.delete);

        TextView ponudnik_text = (TextView) convertView.findViewById(R.id.ponudnik_text);
        TextView datum_text = (TextView) convertView.findViewById(R.id.datum_text);
        TextView cena_text = (TextView) convertView.findViewById(R.id.cena_text);
        final TextView ID_nakupa_text = (TextView) convertView.findViewById(R.id.ID_nakupa);

        // Set data into textviews

        ponudnik_text.setText(nak.get(position).getPonudnik());
        datum_text.setText(nak.get(position).getDatum());
        cena_text.setText(" " + nak.get(position).getCena());
        ID_nakupa_text.setText(" " + nak.get(position).getID_nakupa());

        delete_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringRequest request1 = new StringRequest(Request.Method.DELETE, BASE_url1+String.valueOf(nak.get(position).getID_nakupa()), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Hello");

                    }

                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("ID_nakupa", String.valueOf(nak.get(position).getID_nakupa()));
                        return parameters;

                    }

                };

                System.out.println(request1.toString());

                requestQueue1.add(request1);

                Toast.makeText(context.getApplicationContext(), "Podatki so bili uspešno izbrisani.", Toast.LENGTH_SHORT).show();

                nak.remove(position);
                notifyDataSetChanged();

            }

        });

        return convertView;
    }
}

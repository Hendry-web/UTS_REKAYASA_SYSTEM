package com.example.uts_rekayasa_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  DatePickerDialog.OnDateSetListener{
    private EditText editnoinduk;
    private EditText editnama;
    private EditText edittempat;
    private EditText editalamat;
    private EditText editwali;
    private EditText edittelp;


    private Button buttonAdd;
    private Button buttonView;

    Spinner spinner6;
    Spinner spinner3;

    ArrayList<String> sekolahList = new ArrayList<>();
    ArrayList<String> sekolahIdList = new ArrayList<>();
    ArrayList<String> paketList = new ArrayList<>();
    ArrayList<String> paketIdList = new ArrayList<>();
    ArrayAdapter<String> sekolahAdapter;
    ArrayAdapter<String> paketAdapter;
    RequestQueue requestQueue;
    RequestQueue requestQueuePaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inisialisasi dari View
        editnama = (EditText) findViewById(R.id.editnama);
        editnoinduk = (EditText) findViewById(R.id.editnoinduk);
        edittempat = (EditText) findViewById(R.id.edittempat);
        editalamat = (EditText) findViewById(R.id.editalamat);
        editwali = (EditText) findViewById(R.id.editwali);
        edittelp = (EditText) findViewById(R.id.edittelp);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);
        spinner6 = findViewById(R.id.spinner6);

        String url = konfigurasi.URL_GET_SEKOLAH;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String sekolah = jsonObject.optString("sekolah");
                        String sekolahId = jsonObject.optString("id");
                        sekolahList.add(sekolah);
                        sekolahIdList.add(sekolahId);
                        sekolahAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, sekolahList);
                        sekolahAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner6.setAdapter(sekolahAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        
        String urlPaket = konfigurasi.URL_GET_PAKET;
        JsonObjectRequest jsonObjectRequestPaket = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String paket = jsonObject.optString("paket");
                        String paketId = jsonObject.optString("id");
                        paketList.add(paket);
                        paketIdList.add(paketId);
                        paketAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, paketList);
                        paketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner3.setAdapter(paketAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueuePaket.add(jsonObjectRequestPaket);


        Button button2 =(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String CurrentDateString = DateFormat.getDateInstance().format(c.getTime());

        TextView textView11 = (TextView) findViewById(R.id.textView11);
        textView11.setText(CurrentDateString);
    }



    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addSiswa() {

        final String induk = editnoinduk.getText().toString().trim();
        final String nama = editnama.getText().toString().trim();
        final String tempat = edittempat.getText().toString().trim();
        final String alamat = editalamat.getText().toString().trim();
        final String wali = editwali.getText().toString().trim();
        final String sekolah = sekolahIdList.get((int) spinner6.getSelectedItemId()).trim();
        final String paket = paketIdList.get((int) spinner3.getSelectedItemId()).trim();
        final String telp = edittelp.getText().toString().trim();


        class AddSiswa extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_INDUK, induk);
                params.put(konfigurasi.KEY_EMP_NAMA, nama);
                params.put(konfigurasi.KEY_EMP_TEMPAT, tempat);
                params.put(konfigurasi.KEY_EMP_ALAMAT, alamat );
                params.put(konfigurasi.KEY_EMP_WALI, wali);
                params.put(konfigurasi.KEY_EMP_TELP, telp);
                params.put(konfigurasi.KEY_EMP_SEKOLAH, sekolah);
                params.put(konfigurasi.KEY_EMP_PAKET, paket);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddSiswa ae = new AddSiswa();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addSiswa();
        }

        if (v == buttonView) {
            startActivity(new Intent(this, tampilSemuaSiswa.class));
        }

    }
}

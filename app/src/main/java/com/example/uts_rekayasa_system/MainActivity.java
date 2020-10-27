package com.example.uts_rekayasa_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.example.uts_rekayasa_system.R;

import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,  DatePickerDialog.OnDateSetListener {
    private EditText editnoinduk;
    private EditText editnama;
    private EditText edittempat;
    private EditText editalamat;
    private EditText editwali;
    private EditText edittelp;
    private TextView texttanggal;
    private Button buttonAdd;
    private Button buttonView;
    private Button buttonSekolah;

    Spinner spinner3;
    Spinner spinner6;
    String radioButton = "Laki-laki";

    ArrayList<String> sekolahList = new ArrayList<>();
    ArrayList<String> sekolahIdList = new ArrayList<>();

    ArrayList<String> paketList = new ArrayList<>();
    ArrayList<String> paketIdList = new ArrayList<>();

    ArrayAdapter<String> sekolahAdapter;
    ArrayAdapter<String> paketAdapter;
    RequestQueue requestQueue;

    Bitmap bitmap;
    Button SelectImageGallery;
    ImageView imageView;

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
        texttanggal = (TextView) findViewById(R.id.textView11);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonSekolah = (Button) findViewById(R.id.buttonsekolah);
        imageView = (ImageView) findViewById(R.id.imageView);
        SelectImageGallery = (Button) findViewById(R.id.buttonSelect);

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
            }
        });

        //Setting listeners to button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editnoinduk.getText().toString().length() == 0 && editnama.getText().toString().length() == 0) {
                    editnoinduk.setError("No. induk dan nama wajib dimasukkan.");
                    editnama.setError("No. induk dan nama wajib dimasukkan.");
                } else if (editnoinduk.getText().toString().length() == 0) {
                    editnoinduk.setError("No. induk wajib dimasukkan.");
                } else if (editnama.getText().toString().length() == 0) {
                    editnama.setError("Nama wajib dimasukkan.");
                } else if(imageView.getDrawable() == null){
                    Toast.makeText(MainActivity.this, "Please insert image.", Toast.LENGTH_SHORT).show();
                } else{
                    addSiswa();
                }
            }
        });

        buttonView.setOnClickListener(this);
        buttonSekolah.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);

        spinner6 = findViewById(R.id.spinner6);
        spinner3 = findViewById(R.id.spinner3);

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
        JsonObjectRequest jsonObjectRequestPaket = new JsonObjectRequest(Request.Method.POST, urlPaket, null, new Response.Listener<JSONObject>() {
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
        requestQueue.add(jsonObjectRequestPaket);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {
        super.onActivityResult(RC, RQC, I);
        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {
            Uri uri = I.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(MainActivity.this, "No image uploaded.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date CurrentDateString = c.getTime();

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(CurrentDateString);

        texttanggal.setText(mysqlDateString);
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    radioButton = "Laki-Laki";
                break;
            case R.id.radioButton2:
                if (checked)
                    radioButton = "Perempuan";
                break;
            default:
                radioButton = "Laki-Laki";
        }
    }

    //Dibawah ini merupakan perintah untuk menambahkan pedobear (CREATE)
    private void addSiswa() {
        final String induk = editnoinduk.getText().toString().trim();
        final String nama = editnama.getText().toString().trim();
        final String tempat = edittempat.getText().toString().trim();
        final String alamat = editalamat.getText().toString().trim();
        final String wali = editwali.getText().toString().trim();
        final String sekolah = sekolahIdList.get((int) spinner6.getSelectedItemId()).trim();
        final String telp = edittelp.getText().toString().trim();
        final String paket = paketIdList.get((int) spinner3.getSelectedItemId()).trim();
        final String jenis = radioButton;
        final String tanggal = texttanggal.getText().toString().trim();

        ByteArrayOutputStream byteArrayOutputStreamObject;
        byteArrayOutputStreamObject = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);
        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
        final String foto = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

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
                params.put(konfigurasi.KEY_EMP_JENIS, jenis);
                params.put(konfigurasi.KEY_EMP_TANGGAL, tanggal);
                params.put(konfigurasi.KEY_EMP_FOTO_PATH, foto);

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
            startActivity(new Intent(this, TampilSemuaSiswa.class));
        }

        if(v == buttonSekolah){
            startActivity(new Intent(this, TambahSekolah.class));
        }
    }
}

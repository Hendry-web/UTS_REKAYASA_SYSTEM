package com.example.uts_rekayasa_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.NumberPicker;
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
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.function.ToIntFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TampilSiswa extends AppCompatActivity implements View.OnClickListener,  DatePickerDialog.OnDateSetListener {
    private EditText editTextId;
    private EditText editnoinduk;
    private EditText editnama;
    private EditText edittempat;
    private EditText editalamat;
    private EditText editwali;
    private EditText edittelp;

    private TextView texttanggal;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    RadioGroup radioGroup;

    Spinner spinner3;
    Spinner spinner6;

    String radioButton;

    ArrayList<String> sekolahList = new ArrayList<>();
    ArrayList<String> sekolahIdList = new ArrayList<>();

    ArrayList<String> paketList = new ArrayList<>();
    ArrayList<String> paketIdList = new ArrayList<>();

    ArrayAdapter<String> sekolahAdapter;
    ArrayAdapter<String> paketAdapter;
    ArrayAdapter<String> paketAdapter2;

    RequestQueue requestQueue;

    Bitmap bitmap;
    Button SelectImageGallery;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_siswa);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.SIS_ID);

        //Inisialisasi dari View
        editTextId = (EditText) findViewById(R.id.editTextId);
        editnama = (EditText) findViewById(R.id.editnama);
        editnoinduk = (EditText) findViewById(R.id.editnoinduk);
        edittempat = (EditText) findViewById(R.id.edittempat);
        editalamat = (EditText) findViewById(R.id.editalamat);
        editwali = (EditText) findViewById(R.id.editwali);
        edittelp = (EditText) findViewById(R.id.edittelp);
        texttanggal = (TextView) findViewById(R.id.textView11);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
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
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(TampilSiswa.this, "Please insert image.", Toast.LENGTH_SHORT).show();
                } else {
                    updateSiswa();
                }
            }

        });

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(this);
        spinner6 = findViewById(R.id.spinner6);
        spinner3 = findViewById(R.id.spinner3);

        editTextId.setText(id);
        getSiswa();

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
                        sekolahAdapter = new ArrayAdapter<>(TampilSiswa.this, android.R.layout.simple_spinner_item, sekolahList);
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
                        paketAdapter = new ArrayAdapter<>(TampilSiswa.this, android.R.layout.simple_spinner_item, paketList);
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
            Toast.makeText(TampilSiswa.this, "No image uploaded.", Toast.LENGTH_SHORT).show();
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

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    radioButton = "Laki-laki";
                break;
            case R.id.radioButton2:
                if (checked)
                    radioButton = "Perempuan";
                break;
            default:
                radioButton = "";
        }
    }

    private void getSiswa(){
        class GetSiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSiswa.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showSiswa(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP, id);
                return s;
            }
        }
        GetSiswa ge = new GetSiswa();
        ge.execute();
    }
    private void showSiswa(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String paket = c.getString(konfigurasi.TAG_PAKET);
            String induk = c.getString(konfigurasi.TAG_INDUK);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String tempat = c.getString(konfigurasi.TAG_TEMPAT);
            String alamat= c.getString(konfigurasi.TAG_ALAMAT);
            String wali = c.getString(konfigurasi.TAG_WALI);
            String sekolah = c.getString(konfigurasi.TAG_SEKOLAH);
            String telp = c.getString(konfigurasi.TAG_TELP);
            String jenis = c.getString(konfigurasi.TAG_JENIS);
            String tanggal = c.getString(konfigurasi.TAG_TANGGAL);
            String foto = c.getString(konfigurasi.TAG_FOTO);

            Picasso.get().load(foto).resize(100,100).noFade().into(imageView);

            switch (jenis) {
                case "Laki-laki":
                    radioGroup.check(R.id.radioButton);
                    break;
                case "Perempuan":
                    radioGroup.check(R.id.radioButton2);
                    break;
                default:
                    radioGroup.check(R.id.radioButton);
            }

            spinner3.setSelection(2);
            editnoinduk.setText(induk);
            editnama.setText(nama);
            edittempat.setText(tempat);
            editalamat.setText(alamat);
            editwali.setText(wali);
            spinner6.setSelection(2);
            edittelp.setText(telp);
            radioButton = jenis;
            texttanggal.setText(tanggal);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Dibawah ini merupakan perintah untuk mengupdate pedobear (CREATE)
    private void updateSiswa() {
        final String id = editTextId.getText().toString().trim();
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

        class UpdateSiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSiswa.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilSiswa.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID, id);
                hashMap.put(konfigurasi.KEY_EMP_INDUK, induk);
                hashMap.put(konfigurasi.KEY_EMP_NAMA, nama);
                hashMap.put(konfigurasi.KEY_EMP_TEMPAT, tempat);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT, alamat);
                hashMap.put(konfigurasi.KEY_EMP_WALI, wali);
                hashMap.put(konfigurasi.KEY_EMP_TELP, telp);
                hashMap.put(konfigurasi.KEY_EMP_SEKOLAH, sekolah);
                hashMap.put(konfigurasi.KEY_EMP_PAKET, paket);
                hashMap.put(konfigurasi.KEY_EMP_JENIS, jenis);
                hashMap.put(konfigurasi.KEY_EMP_TANGGAL, tanggal);
                hashMap.put(konfigurasi.KEY_EMP_FOTO_PATH, foto);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP, hashMap);
                return s;
            }
        }
        UpdateSiswa ue = new UpdateSiswa();
        ue.execute();
    }

    private void deleteSiswa(){
        class DeleteSiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSiswa.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilSiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }
        DeleteSiswa de = new DeleteSiswa();
        de.execute();
    }

    private void confirmDeleteSiswa(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data Pedobear ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteSiswa();
                        startActivity(new Intent(TampilSiswa.this,TampilSemuaSiswa.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateSiswa();
        }

        if(v == buttonDelete){
            confirmDeleteSiswa();
        }
    }
}
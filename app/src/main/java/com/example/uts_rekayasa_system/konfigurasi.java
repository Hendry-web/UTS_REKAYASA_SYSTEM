package com.example.uts_rekayasa_system;

public class konfigurasi {
    public static final String URL_ADD=     "http://192.168.100.2:8080/uts/siswa/tambahSiswa.php";
    public static final String URL_GET_ALL = "http://192.168.100.2:8080/uts/siswa/tampilSemuaSiswa.php";
    public static final String URL_GET_EMP = "http://192.168.100.2:8080/uts/siswa/tampilSiswa.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.100.2:8080/uts/siswa/updateSiswa.php";
    public static final String URL_DELETE_EMP = "http://192.168.100.2:8080/uts/siswa/hapusSiswa.php?id=";
    public static final String URL_GET_SEKOLAH    = "http://192.168.100.2:8080/uts/siswa/getSekolah.php";
    public static final String URL_GET_PAKET   = "http://192.168.100.2:8080/uts/siswa/getPaket.php";
    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_PAKET = "paket";
    public static final String KEY_EMP_INDUK = "induk";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_JENIS = "jenis";
    public static final String KEY_EMP_TEMPAT = "tempat";
    public static final String KEY_EMP_TANGGAL = "tanggal";
    public static final String KEY_EMP_SEKOLAH = "sekolah";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_WALI = "wali";
    public static final String KEY_EMP_TELP = "telp";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_PAKET = "paket";
    public static final String TAG_INDUK = "induk";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_JENIS = "jenis";
    public static final String TAG_TEMPAT = "tempat";
    public static final String TAG_TANGGAL = "tanggal";
    public static final String TAG_SEKOLAH = "sekolah";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_WALI = "wali";
    public static final String TAG_TELP = "telp";

    //ID siswa
    //sis itu singkatan dari siswa
    public static final String SIS_ID = "sis_id";
}


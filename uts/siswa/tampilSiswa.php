<?php 
//Mendapatkan Nilai Dari Variable ID Pegawai yang ingin ditampilkan
$id = $_GET['id'];

//Importing database
require_once('koneksi.php');

//Membuat SQL Query dengan pegawai yang ditentukan secara spesifik sesuai ID
$sql = "SELECT * FROM siswa WHERE id = $id";
$sqlPaket = "SELECT paket.paket FROM siswa INNER JOIN paket ON siswa.paket_id = paket.id WHERE siswa.id = $id";
$sqlSekolah = "SELECT sekolah_asal.sekolah FROM siswa INNER JOIN sekolah_asal ON siswa.sekolah_asal_id = sekolah_asal.id WHERE siswa.id = $id";

//Mendapatkan Hasil 
$r = mysqli_query($con, $sql);
$rPaket = mysqli_query($con, $sqlPaket);
$rSekolah = mysqli_query($con, $sqlSekolah);

//Memasukkan Hasil Kedalam Array
$result = array();
$row = mysqli_fetch_array($r);
$rowPaket = mysqli_fetch_array($rPaket);
$rowSekolah = mysqli_fetch_array($rSekolah);
array_push($result, array(
	"id"=>$row['id'],
	"paket"=>$rowPaket['paket'],
	"induk"=>$row['no_induk'],
	"nama"=>$row['nama'],
	"jenis"=>$row['jenis_kelamin'],
	"tempat"=>$row['tempat_lahir'],
	"tanggal"=>$row['tanggal_lahir'],
	"sekolah"=>$rowSekolah['sekolah'],
	"alamat"=>$row['alamat'],
	"wali"=>$row['nama_wali'],
	"telp"=>$row['telp'],
	"foto"=>$row['foto'],
));

//Menampilkan dalam format JSON
echo json_encode(array('result'=>$result));

//Menutup koneksi database
mysqli_close($con);
?>

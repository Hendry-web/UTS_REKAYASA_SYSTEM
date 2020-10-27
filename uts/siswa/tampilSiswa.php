<?php 
$id = $_GET['id'];

require_once('koneksi.php');
$sql = "SELECT * FROM siswa WHERE id = $id";
$sqlPaket = "SELECT paket.paket FROM siswa INNER JOIN paket ON siswa.paket_id = paket.id WHERE siswa.id = $id";
$sqlSekolah = "SELECT sekolah_asal.sekolah FROM siswa INNER JOIN sekolah_asal ON siswa.sekolah_asal_id = sekolah_asal.id WHERE siswa.id = $id";

$r = mysqli_query($con, $sql);
$rPaket = mysqli_query($con, $sqlPaket);
$rSekolah = mysqli_query($con, $sqlSekolah);

$result = array();
$row = mysqli_fetch_array($r);
$rowPaket = mysqli_fetch_array($rPaket);
$rowSekolah = mysqli_fetch_array($rSekolah);

array_push($result, array(
	"id" => $row['id'],
	"paket" => $rowPaket['paket'],
	"induk" => $row['no_induk'],
	"nama" => $row['nama'],
	"jenis" => $row['jenis_kelamin'],
	"tempat" => $row['tempat_lahir'],
	"tanggal" => $row['tanggal_lahir'],
	"sekolah" => $rowSekolah['sekolah'],
	"alamat" => $row['alamat'],
	"wali" => $row['nama_wali'],
	"telp" => $row['telp'],
	"foto" => $row['foto_link'],
));

echo json_encode(array('result' => $result));
mysqli_close($con);
?>
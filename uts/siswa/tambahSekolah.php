<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	$sekolah = $_POST['sekolah'];
	$sql = "INSERT INTO sekolah_asal (sekolah) VALUES ('$sekolah')";
	require_once('koneksi.php');
	

	if(mysqli_query($con,$sql)){
		echo 'Berhasil Menambahkan Pegawai';
	}else{
		echo 'Gagal Menambahkan Pegawai';
	}
	
	mysqli_close($con);
}
?>
<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    require_once('koneksi.php');
	$sekolah = $_POST['sekolah'];
	$sql = "INSERT INTO sekolah_asal (sekolah) VALUES ('$sekolah')";

	if(mysqli_query($con,$sql)){
		echo 'Berhasil menambahkan sekolah.';
	}else{
		echo 'Gagal menambahkan sekolah.';
	}
	
	mysqli_close($con);
}
?>
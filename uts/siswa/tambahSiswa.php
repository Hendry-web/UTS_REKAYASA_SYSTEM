<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	//Mendapatkan Nilai Variable
	$paket = $_POST['paket'];
	$induk = $_POST['induk'];
	$nama = $_POST['nama'];
	$jenis = $_POST['jenis'];
	$tempat = $_POST['tempat'];
	$tanggal = $_POST['tanggal'];
	$sekolah = $_POST['sekolah'];
	$alamat = $_POST['alamat'];
	$wali = $_POST['wali'];
	$telp = $_POST['telp'];

	//Pembuatan Syntax SQL
	$sql = "INSERT INTO siswa (paket_id,no_induk,nama,jenis_kelamin,tempat_lahir,tanggal_lahir,sekolah_asal_id,alamat,nama_wali,telp) VALUES ('$paket','$induk','$nama','$jenis','$tempat','$tanggal','$sekolah','$alamat','$wali','$telp')";

	//Import File Koneksi database
	require_once('koneksi.php');

	//Eksekusi Query database
	if(mysqli_query($con,$sql)){
		echo 'Berhasil Menambahkan Pegawai';
	}else{
		echo 'Gagal Menambahkan Pegawai';
	}

	mysqli_close($con);
}
?>

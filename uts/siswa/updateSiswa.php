<?php 
 
 /*

 
 */
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable 
		$id = $_POST['id'];
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
		
		//import file koneksi database 
		require_once('koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE siswa SET paket = '$paket', induk = '$induk', nama = '$nama', jenis = '$jenis', tempat = '$tempat', tanggal = '$tanggal',sekolah = '$sekolah', alamat = '$alamat', wali = '$wali' ,telp = '$telp'WHERE id = $id;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Pegawai';
		}else{
			echo 'Gagal Update Data Pegawai';
		}
		
		mysqli_close($con);
	}
?>
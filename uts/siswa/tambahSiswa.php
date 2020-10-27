<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
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
	$foto = $_FILES['foto']['name'];

	if($foto != ""){
		$ekstensi_diperbolehkan = array('png', 'jpg');
		$x = explode('.', $foto);
		$ekstensi = strtolower(end($x));
		$file_tmp = $_FILES['foto']['tmp_name'];   
		$angka_acak = rand(1, 999);
		$nama_foto_baru = $angka_acak.'-'.$foto;

		if(in_array($ekstensi, $ekstensi_diperbolehkan) === true){   
            move_uploaded_file($file_tmp, 'foto/'.$nama_foto_baru);
            require_once('koneksi.php');
			$query = "INSERT INTO siswa (paket_id, no_induk, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, sekolah_asal_id, alamat, nama_wali, telp, foto) VALUES ('$paket', '$induk', '$nama', '$jenis', '$tempat', '$tanggal', '$sekolah', '$alamat', '$wali', '$telp', '$nama_foto_baru')";
			$result = mysqli_query($con, $query);

			if($result){
				echo 'Berhasil menambahkan siswa.';
			} 

			else{
				echo 'Gagal menambahkan siswa.';
			}
		}
	}
		
	else{     
        echo 'Ekstensi foto yang diperbolehkan hanyalah .jpg atau .png!';
    }

	mysqli_close($con);
}
?>
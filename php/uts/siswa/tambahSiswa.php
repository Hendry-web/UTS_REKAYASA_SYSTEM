<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
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
	$ImageData = $_POST['foto_path'];

    include('koneksi.php');
    $GetOldIdSQL ="SELECT id FROM siswa ORDER BY id ASC";
    $Query = mysqli_query($con, $GetOldIdSQL);

    while($row = mysqli_fetch_array($Query)){
        $DefaultId = $row['id'];
    }

    $ImagePath = "images/$DefaultId.png";
    $ServerURL = "https://hexadoge.fun/uts/siswa/$ImagePath";

    $query = "INSERT INTO siswa (paket_id, no_induk, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, sekolah_asal_id, alamat, nama_wali, telp, foto_name, foto_link) VALUES ('$paket', '$induk', '$nama', '$jenis', '$tempat', '$tanggal', '$sekolah', '$alamat', '$wali', '$telp', '$DefaultId', '$ServerURL')";
    $result = mysqli_query($con, $query);

    if($result){
        file_put_contents($ImagePath,base64_decode($ImageData));
        echo 'Berhasil menambahkan siswa.';
    } 

    else{
        echo 'Gagal menambahkan siswa.';
    }

	mysqli_close($con);
}
?>

<?php 
if($_SERVER['REQUEST_METHOD']=='POST'){
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
    $ImageData = $_POST['foto_path'];

    include('koneksi.php');
    $GetOldIdSQL ="SELECT id FROM siswa ORDER BY id ASC";
    $Query = mysqli_query($con, $GetOldIdSQL);

    while($row = mysqli_fetch_array($Query)){
        $DefaultId = $row['id'];
    }

    $ImagePath = "images/$DefaultId.png";
    $ServerURL = "http://hexadoge.fun/uts/siswa/$ImagePath";

    $query = "UPDATE siswa SET paket_id = '$paket', no_induk = '$induk', nama = '$nama', jenis_kelamin = '$jenis', tempat_lahir = '$tempat', tanggal_lahir = '$tanggal', sekolah_asal_id = '$sekolah', alamat = '$alamat', nama_wali = '$wali', telp = '$telp', foto_name = '$DefaultId', foto_link = '$ServerURL' WHERE id = '$id'";
    $result = mysqli_query($con, $query);

    if($result){
        file_put_contents($ImagePath,base64_decode($ImageData));
        echo 'Berhasil mengupdate siswa.';
    } 

    else{
        echo 'Gagal mengupdate siswa.';
    }
    
    mysqli_close($con);
}
?>

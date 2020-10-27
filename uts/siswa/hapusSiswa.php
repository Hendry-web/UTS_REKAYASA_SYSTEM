<?php 
require_once('koneksi.php');
$id = $_GET['id'];
$sql = "DELETE FROM siswa WHERE id = $id";
 
if(mysqli_query($con, $sql)){
    echo 'Berhasil menghapus siswa.';
}

else{
    echo 'Gagal menghapus siswa.';
}
 
mysqli_close($con);
?>
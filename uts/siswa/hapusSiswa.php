<?php 
$id = $_GET['id'];
require_once('koneksi.php');
$sql = "DELETE FROM siswa WHERE id = $id;";
 
if(mysqli_query($con, $sql)){
    echo 'Berhasil menghapus siswa.';
}

else{
    echo 'Gagal menghapus siswa.';
}
 
mysqli_close($con);
?>
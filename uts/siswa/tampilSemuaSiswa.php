<?php 
require_once('koneksi.php');
$sql = "SELECT * FROM siswa";
$r = mysqli_query($con, $sql);
$result = array();

while($row = mysqli_fetch_array($r)){
    array_push($result, array(
        "id"=> $row['id'],
        "nama" => $row['nama'],
    ));
}

echo json_encode(array('result' => $result));
mysqli_close($con);
?>
<?php
function getSekolah(){
    require_once('koneksi.php');
	$sql = "SELECT* FROM sekolah_asal";
	$r = mysqli_query($con, $sql);
	$result = array();

	while($row = mysqli_fetch_array($r)){
		array_push($result, array(
			"id" => $row['id'],
			"sekolah" => $row['sekolah'],
		));
    }
    
	echo json_encode(array('result'=>$result));
	mysqli_close($con);
}

getSekolah();
?>
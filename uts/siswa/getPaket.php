<?php


function getPaket(){
require_once('koneksi.php');

	//membuat sql query
	$sql = "SELECT* FROM paket";

	//Mendapat Hasil
	$r = mysqli_query($con,$sql);

	//membuat array kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//memasukkan Nama dan ID kedalam Array Kosong Yang telah di buat
		array_push($result,array(
			"id"=>$row['id'],
			"paket"=>$row['paket']
		));
	}

	//menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
}
getPaket();
?>
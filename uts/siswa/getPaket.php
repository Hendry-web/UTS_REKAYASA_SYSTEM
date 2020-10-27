  
<?php
function getPaket(){
    require_once('koneksi.php');
    $sql = "SELECT * FROM paket";
    $r = mysqli_query($con, $sql);
    $result = array();

    while($row = mysqli_fetch_array($r)){
        array_push($result, array(
            "id" => $row['id'],
            "paket" => $row['paket'],
        ));
    }

    echo json_encode(array('result'=>$result));
    mysqli_close($con);
}

getPaket();
?>
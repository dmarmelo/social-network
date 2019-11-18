<?php
include '../mysql/mysqlConnect.php';

$idPost = $_POST["idPost"];
//$idPost = $_GET["idPost"];

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$sql = "DELETE FROM likes WHERE idPost = ? AND idAutor = ?";
$stmt = $GLOBALS["db.connection"]->prepare($sql);
$stmt->bind_param("ii", $idPost, $id);

if ($stmt->execute() == TRUE) {
    echo "OK";
}
else {
    echo "FALSE";
}

$stmt->close();

include '../mysql/mysqlClose.php';
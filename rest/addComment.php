<?php
include '../mysql/mysqlConnect.php';

$idPost = $_POST["idPost"];
$texto = $_POST["texto"];
//$idPost = $_GET["idPost"];
//$texto = $_GET["texto"];

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$sql = "INSERT INTO comentarios (idPost, idAutor, texto, data) VALUES (?, ?, ?, NOW())";
$stmt = $GLOBALS["db.connection"]->prepare($sql);
$stmt->bind_param("iis", $idPost, $id, $texto);

if ($stmt->execute() == TRUE) {
    echo "OK";
}
else {
    echo "FALSE";
}

$stmt->close();

include '../mysql/mysqlClose.php';
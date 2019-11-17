<?php
include '../mysql/mysqlConnect.php';

$texto = $_POST["texto"];
//$texto = $_GET["texto"];

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$sql = "INSERT INTO post (idAutor, texto, data) VALUES (?, ?, NOW())";
$stmt = $GLOBALS["db.connection"]->prepare($sql);
$stmt->bind_param("is", $id, $texto);

if ($stmt->execute() == TRUE) {
    echo "OK";
}
else {
    echo "FALSE";
}

$stmt->close();

include '../mysql/mysqlClose.php';

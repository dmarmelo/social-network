<?php
include './mysql/mysqlConnect.php';

$id = $_GET['id'];

$sql = "DELETE FROM facehub.utilizador WHERE id = ?";
$stmt = $GLOBALS["db.connection"]->prepare($sql);
$stmt->bind_param("i", $id);

if ($stmt->execute() === false) {
    echo "Erro: " . $sql . "<br>" . $mysqli->error;
}

$stmt->close();
include './mysql/mysqlClose.php';

include "index.php";
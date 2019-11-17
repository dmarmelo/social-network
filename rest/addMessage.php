<?php

include '../mysql/mysqlConnect.php';

$mensagem = $_POST["mensagem"];


if (!isset($_SESSION)) {
    session_start();
}
$destinatario = $_POST["destinatario"];
$id = $_SESSION["id"];

$sql = "insert into mensagem (data,texto,idAutor,idTarget) "
        . " VALUES(NOW(),'$mensagem',$id,$destinatario)";

$result = $GLOBALS["db.connection"]->query($sql);

include '../mysql/mysqlClose.php';

if($result == TRUE)
    echo "OK";
else
    echo "FALSE";


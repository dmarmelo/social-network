<?php
header("Content-type: application/json");
include '../mysql/mysqlConnect.php';

$idPost = $_GET["idPost"];

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$result = $GLOBALS["db.connection"]->query("SELECT c.*, u.nome FROM comentarios c
                                            JOIN utilizador u ON u.id = c.idAutor
                                            WHERE idPost = '$idPost';");

if($result == false)
{
    echo $GLOBALS["db.connection"]->error;
}

$todos = array();
while ($row = $result->fetch_assoc()) {
    $todos[] = $row; //atribui o array do user à ultima prosicao do array geral
}
echo json_encode($todos);

include '../mysql/mysqlClose.php';
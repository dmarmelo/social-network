<?php
header("Content-type: application/json");

include '../mysql/mysqlConnect.php';

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$result = $GLOBALS["db.connection"]->query("SELECT * FROM utilizador u
                                            JOIN amigos a ON a.idAmigo1 = u.id
                                            WHERE a.idAmigo2 = $id;");

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
<?php
header("Content-type: application/json");

include '../mysql/mysqlConnect.php';

$result = $GLOBALS["db.connection"]->query("SELECT * FROM utilizador");

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
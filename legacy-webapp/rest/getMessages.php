<?php

//necessário para dizer ao receptor que o conteudo é json
header("Content-type: application/json");

if (isset($_GET["lastId"])) {
    $lastId = $_GET["lastId"];
}
else {
    $lastId = 0;
}

include '../mysql/mysqlConnect.php';

$amigoDeConversaId = $_GET["amigoDeConversaId"];

session_start();
$id = $_SESSION["id"];
$result = $GLOBALS["db.connection"]->query("SELECT m.*, autor.nome FROM mensagem m
                                            JOIN utilizador autor ON autor.id = m.idAutor
                                            WHERE ((idAutor = $id AND idTarget = $amigoDeConversaId ) OR ( idAutor = $amigoDeConversaId AND idTarget = $id )) 
                                            AND m.id > $lastId;");

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

?>


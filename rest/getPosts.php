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

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

/*$result = $GLOBALS["db.connection"]->query("
SELECT p.id, u.nome AS nome, p.texto, p.data, COUNT(l.idPost) AS likes, COUNT(l2.idPost) AS liked FROM post AS p
JOIN utilizador u ON p.idAutor = u.id
LEFT JOIN likes l ON p.id = l.idPost
LEFT JOIN likes l2 ON  l2.idAutor = l.idAutor AND l2.idPost = l.idPost AND l2.idAutor = $id
WHERE p.id > $lastId
GROUP BY p.id;
");*/

$result = $GLOBALS["db.connection"]->query("
SELECT p.*, u.nome AS nome, COUNT(l.idPost) AS likes, COUNT(l2.idPost) AS liked FROM post p
JOIN utilizador u ON p.idAutor = u.id
LEFT JOIN likes l ON l.idPost = p.id
LEFT JOIN likes l2 ON  l2.idAutor = l.idAutor AND l2.idPost = l.idPost AND l2.idAutor = $id
WHERE p.id > $lastId AND (p.idAutor IN (SELECT u.id FROM utilizador u JOIN amigos a ON a.idAmigo1 = u.id WHERE a.idAmigo2 = $id) OR p.idAutor = $id)
GROUP BY p.id;
");

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
<?php
header("Content-type: application/json");
include '../mysql/mysqlConnect.php';

$idPost = $_GET["idPost"];

if (!isset($_SESSION)) {
    session_start();
}

$id = $_SESSION["id"];

$result = $GLOBALS["db.connection"]->query("SELECT COUNT(*) AS likes FROM likes
                                            WHERE idPost = $idPost;");

if($result == false)
{
    echo $GLOBALS["db.connection"]->error;
}
$todos = $result->fetch_assoc();
echo json_encode($todos);

include '../mysql/mysqlClose.php';
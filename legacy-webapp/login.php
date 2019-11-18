<?php

if (!isset($_SESSION)) {
    session_start();
}

$username = $_POST["username"];
$password = $_POST["password"];

$found = false;

include './mysql/mysqlConnect.php';
$sql = "SELECT * FROM utilizador WHERE username = '$username' AND password = '$password'";
$result = $GLOBALS["db.connection"]->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $found = true;
    $_SESSION["nome"] = $row["nome"];
    $_SESSION["id"] = $row["id"];
}
include './mysql/mysqlClose.php';

include "index.php";

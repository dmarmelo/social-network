<?php

if (!isset($_SESSION)) {
    session_start();
}

unset($_SESSION['nome']);
unset($_SESSION['id']);
session_destroy();

include "index.php";

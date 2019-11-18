<?php
require_once(dirname(__FILE__) . '/config.php');

$conecao = new mysqli($GLOBALS["db.host"], $GLOBALS["db.user"],$GLOBALS["db.pass"], $GLOBALS["db.schema"], $GLOBALS["db.port"]);
    
//Comentar isto caso dê problemas com caracteres
$conecao->set_charset("utf8");
    

if ($conecao->connect_errno)
{
    echo '{"erro":"Falha a conectar ao MySQL:' . $conecao->connect_error . '}';
}

$GLOBALS["db.connection"] = $conecao;

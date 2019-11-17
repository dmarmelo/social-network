<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Facehub</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="angular/angular.min.js"></script>

    <link rel="stylesheet" href="css/customNavbar.css">

    <style>
        body {
            height: 100vh;
            background: url(/img/background.png) 0 bottom repeat-x;
            background: linear-gradient(white, #D3D8E8);
        }
    </style>

</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.php"><strong>Facehub</strong></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <form class="navbar-form navbar-right" action="login.php" method="POST">
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in"></span> Sign In</button>
            </form>
        </div>
    </div>
</nav>

<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-6">
            <h3 class="text-center"><strong>O Facehub ajuda-te a comunicar e a partilhar com as pessoas que fazem parte da tua vida.</strong></h3>
            <img src="img/connectedPeople.png" style="width: 100%">
        </div>
        <div class="col-md-6">
            <?php
            if (isset($found) && $found == false) {
                echo "<div class='alert alert-warning alert-dismissible'>
                    <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                    <span class='glyphicon glyphicon-exclamation-sign'></span> Username ou Password Errado!</div>";
            }
            ?>
            <h1 class="text-center" style="padding-bottom: 30px"><strong>Regista-te</strong></h1>
            <form class="form-horizontal" action="register.php" method="POST">

                <div class="form-group">
                    <label class="control-label col-sm-2">Username:</label>
                    <div class="col-sm-10">
                        <input name="username" class="form-control" placeholder="Coloque o username">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Email:</label>
                    <div class="col-sm-10">
                        <input name="email" class="form-control" placeholder="Coloque a email">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Password:</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" class="form-control" placeholder="Coloque a password">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Nome:</label>
                    <div class="col-sm-10">
                        <input name="nome" class="form-control" placeholder="Coloque o nome">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Telefone:</label>
                    <div class="col-sm-10">
                        <input name="telefone" class="form-control" placeholder="Coloque o telefone">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Morada:</label>
                    <div class="col-sm-10">
                        <input name="morada" class="form-control" placeholder="Coloque a morada">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2">Idade:</label>
                    <div class="col-sm-10">
                        <input name="idade" class="form-control" placeholder="Coloque a idade">
                    </div>
                </div>

                <button class="btn btn-success btn-lg" type="submit" style="margin-top: 20px;">Regista-te</button>

            </form>
        </div>
    </div>
<?php
//include "usersTable.php";
?>
</div>
</body>
</html>
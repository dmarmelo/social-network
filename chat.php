<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Facehub - Chat</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="angular/angular.min.js"></script>

    <link rel="stylesheet" href="css/customNavbar.css">
    <script src="js/chatApp.js"></script>
    
    <style>
        @media (min-width: 768px) {
            .noScroll {
                overflow: hidden
            }
        }
    </style>

</head>
<body class="noScroll" style="height: 100vh;">

<?php
include "header.php";
?>

<div class="container-fluid" id="chatApp" ng-app="chatApp" ng-controller="chatController" ng-init="iniciaTimer()" style="margin-top: 60px">
    <div class="col-md-3 col-md-offset-1 col-sm-4" ng-init="getFriends()">
        <div class="panel-group visible-xs">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><a data-toggle="collapse" href="#friends">Amigos</a></h4>
                </div>
                <div id="friends" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="list-group">
                            <a ng-repeat="f in friends" ng-class="{active: idAmigo == f.id}" ng-click="selectAmigo(f.id)" class="list-group-item">{{ f.nome }}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel-group hidden-xs">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Amigos</h4>
                </div>
                <div class="panel-body" style="height: calc(100vh - 135px); overflow-y: auto;">
                    <div class="list-group">
                        <a ng-repeat="f in friends" ng-class="{active: idAmigo == f.id}" ng-click="selectAmigo(f.id)" class="list-group-item">{{ f.nome }}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-7 col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Chat</h4>
            </div>
            <div id="chat" class="panel-body" style="height: calc(100vh - 190px); overflow-y: auto;">
                <div class='row' ng-repeat="m in mensagens">
                    <div class='col-md-12'>
                        <label ng-class="{'pull-left': m.idAutor == <?php echo $id?>,'pull-right' : m.idAutor != <?php echo $id?>}">
                            <label class='label' ng-class="{'label-success': m.idAutor == <?php echo $id?>, 'label-info': m.idAutor != <?php echo $id?>}">
                                {{ m.nome }}
                            </label>
                            - {{ m.data }} - {{ m.texto }}
                        </label>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <div class="input-group">
                    <input ng-model="mensagem" ng-keyup="$event.keyCode == 13 ? addMensagem() : null" type="text" class="form-control" placeholder="Escreve uma mensagem...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addMensagem()">Enviar</button>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
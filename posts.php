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
    <script>
        var minhasApps = [];
        angular.module("rootApp",minhasApps);

    </script>
    <script src="js/postApp.js"></script>
    <script src="js/chatApp.js"></script>

    <style>
        body {
            height: 100vh;
            background: url(/img/background.png) 0 bottom repeat-x;
            background: linear-gradient(white, #D3D8E8);-
        }
    </style>

</head>
<body ng-app="rootApp">

<?php
include "header.php";
include "chatBlock.php";
$id = $_SESSION["id"];
?>

<div class="container" id="postApp" ng-app="postApp" ng-controller="postController" ng-init="iniciaTimer()" style="margin-top: 60px">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Criar Publicação</h4>
            </div>
            <div class="panel-body">
                <textarea ng-model="postInput" ng-keyup="$event.keyCode == 13 ? addPost() : null" class="form-control" rows="3" placeholder="Em que estás a pensar?"></textarea>
            </div>
            <div class="panel-footer">
                <button class="btn btn-default" ng-click="addPost()">Publicar</button>
            </div>
        </div>

        <div class="panel panel-default" ng-repeat="p in posts | orderBy: id : true">
            <div class="panel-heading">
                <h4>{{ p.nome }}
                    <span ng-show="{{p.idAutor != <?php echo $id ?>}}">
                    <span ng-click="abreChat(p.idAutor)" class="glyphicon glyphicon-comment"> </span>
                    </span>
                </h4>
                <div>{{ p.data }}</div>
            </div>
            <div class="panel-body" ng-init="postTimer(p)">
                <p>{{ p.texto }}</p>
                <hr>
                <div>
                    <button class="btn" ng-click="toggleLike(p)" ng-class="{'btn-default' : p.liked == 0, 'btn-info' : p.liked == 1}">{{ p.liked == 0 ? "Like" : "Liked"}}</button>
                    <span>{{ p.likes }} {{ p.likes == 1 ? "Like" : "Likes" }}</span>
                </div>
            </div>
            <div class="panel-footer">
                <div class="comentarios">
                    <h4>Comentários</h4>
                    <p ng-repeat="c in p.comentarios">{{ c.nome }} - {{ c.texto }}</p>
                </div>
                <hr>
                <div class="input-group">
                    <input ng-model="p.commentInput" ng-keyup="$event.keyCode == 13 ? addComment(p) : null" type="text" class="form-control" placeholder="Escreve um comentário...">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" ng-click="addComment(p)">Comentar</button>
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
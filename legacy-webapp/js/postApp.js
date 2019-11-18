var app = angular.module('postApp', []);
minhasApps.push("postApp");
app.controller('postController', function ($scope) {
    $scope.postInput = "";
    $scope.addPost = function () {
        $.post(
            "rest/addPost.php",
            {
                "texto" :  $scope.postInput
            },
            function(dados)
            {
                //alert(dados);
                if (dados.indexOf("OK") >= 0) {
                    $scope.postInput = "";
                }
                else {
                    $scope.status = "FALHOU";
                }
                $scope.$apply();
            }
        );
    };

    $scope.posts = [];
    $scope.getPosts = function () {
        var lastId = 0;
        if ($scope.posts.length > 0) {
            lastId = $scope.posts[$scope.posts.length - 1].id;
        }

        $.getJSON(
            "rest/getPosts.php",
            {
                "lastId" : lastId
            },
            function(jsonData) {
                //alert(jsonData);
                if (jsonData.length > 0) {
                    $scope.posts = $scope.posts.concat(jsonData);
                    $scope.$apply();
                }
            }
        );
    };
    $scope.iniciaTimer = function () {
        setInterval(angular.element($("#postApp")).scope().getPosts, 1000);
    };

    $scope.getLikes = function (post) {
        $.getJSON(
            "rest/getLikes.php",
            {
                "idPost" : post.id
            },
            function(jsonData) {
                post.likes = jsonData.likes;
                $scope.$apply();
            }
        );
    };

    $scope.getComments = function (post) {
        $.getJSON(
            "rest/getComments.php",
            {
                "idPost" : post.id
            },
            function (jsonData) {
                post.comentarios = jsonData;
                $scope.$apply();
            }
        );
    };

    $scope.postTimer = function (post) {
        setInterval(angular.element($("#postApp")).scope().getLikes, 2000, post);
        setInterval(angular.element($("#postApp")).scope().getComments, 2000, post);
    };

    $scope.addComment = function (post) {
        $.post(
            "rest/addComment.php",
            {
                "idPost" :  post.id,
                "texto" : post.commentInput
            },
            function(dados)
            {
                //alert(dados);
                if (dados.indexOf("OK") >= 0) {
                    post.commentInput = "";
                }
                else {
                    $scope.status = "FALHOU";
                }
                $scope.$apply();
            }
        );
    };

    $scope.toggleLike = function (post) {
        if (post.liked === '0') {
            $.post(
                "rest/addLike.php",
                {
                    "idPost" :  post.id
                },
                function(dados)
                {
                    //alert(dados);
                    if (dados.indexOf("OK") >= 0) {
                        post.liked = '1';
                        post.likes++;
                    }
                    else {
                        $scope.status = "FALHOU";
                    }
                    $scope.$apply();
                }
            );
        }
        else {
            $.post(
                "rest/remLike.php",
                {
                    "idPost" :  post.id
                },
                function(dados)
                {
                    //alert(dados);
                    if (dados.indexOf("OK") >= 0) {
                        post.liked = '0';
                        post.likes--;
                    }
                    else {
                        $scope.status = "FALHOU";
                    }
                    $scope.$apply();
                }
            );
        }
    };

    $scope.abreChat = function(idAutor)
    {
        /*angular.element($("#chatApp")).scope().idAmigo = idAutor;
         angular.element($("#chatApp")).scope().$apply();*/
        angular.element($("#chatApp")).scope().selectAmigo(idAutor);
    };
});
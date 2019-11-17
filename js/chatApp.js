var app = angular.module('chatApp', []);
if (typeof minhasApps !== 'undefined') {
    minhasApps.push("chatApp");
}
app.controller('chatController', function ($scope) {
    $scope.status = "";

    $scope.friends = [];
    $scope.getFriends = function () {
        $.getJSON("rest/getFriends.php",
            {

            },
            function (jsonData) {
            $scope.friends = jsonData;
            $scope.$apply();
        });
    };

    $scope.idAmigo = -1;
    $scope.selectAmigo = function (id) {
        $scope.idAmigo = id;
        $scope.mensagens = [];
        $scope.getMensagens();
    };

    $scope.mensagens = [];
    $scope.getMensagens = function () {
        if ($scope.idAmigo !== -1) {
            var lastId = 0;
            if ($scope.mensagens.length > 0) {
                lastId = $scope.mensagens[$scope.mensagens.length - 1].id;
            }

            $.getJSON(
                "rest/getMessages.php",
                {
                    "amigoDeConversaId" : $scope.idAmigo,
                    "lastId" : lastId
                },
                function(jsonData)
                {
                    if (jsonData.length > 0) {
                        $scope.mensagens = $scope.mensagens.concat(jsonData);
                        $scope.$apply();
                        $("#chat").scrollTop($("#chat")[0].scrollHeight);
                    }
                }
            );
        }
    };
    $scope.iniciaTimer = function () {
        setInterval(angular.element($("#chatApp")).scope().getMensagens, 1000);
    };

    $scope.mensagem = "";
    $scope.addMensagem = function () {
        $.post(
            "rest/addMessage.php",
            {
                "destinatario" : $scope.idAmigo,
                "mensagem" :  $scope.mensagem
            },
            function(dados)
            {
                //alert(dados);
                if (dados.indexOf("OK") >= 0) {
                    $scope.mensagem = "";
                    $scope.getMensagens();
                }
                else {
                    $scope.status = "FALHOU";
                }
                $scope.$apply();
            }
        );
    };
});

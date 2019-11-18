<style>

    #chatApp .panel {
        position: fixed;
        width: 350px;
        z-index: 1000;
        bottom: 0;
        right: 0;
        margin-bottom: 10px;
        margin-right: 10px;
    }

    #chat {
        height: 400px;
    }

</style>

<div class="container-fluid" id="chatApp" ng-app="chatApp" ng-controller="chatController" ng-init="iniciaTimer()">

    <div ng-show="idAmigo != -1" class="panel panel-default">
        <div class="panel-heading">
            <button type="button" class="close" ng-click="idAmigo = -1">&times;</button>
            <h4>Chat</h4>
        </div>
        <div id="chat" class="panel-body" style="overflow-y: scroll;">
            <div class='row' ng-repeat="m in mensagens">
                <div class='col-md-12'>
                    <label ng-class="{'pull-left': m.idAutor == <?php echo $id?>,'pull-right' : m.idAutor != <?php echo $id?>}">
                        <label class='label' ng-class="{'label-success': m.idAutor == <?php echo $id?>, 'label-info': m.idAutor != <?php echo $id?>}">
                            {{ m.nome }}
                        </label>
                        <!-- - {{ m.data }}--> - {{ m.texto }}
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
<div class="panel panel-default">
    <div class="panel-heading">
        <a data-toggle="collapse" href="#users">Users</a>
    </div>
    <div id="users" class="panel-collapse collapse">
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Nome</th>
                        <th>Password</th>
                        <th>Telefone</th>
                        <th>Morada</th>
                        <th>Idade</th>
                        <th>Del</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                    include './mysql/mysqlConnect.php';
                    $sql = "SELECT * FROM facehub.utilizador;";
                    $result = $GLOBALS["db.connection"]->query($sql);
                    if ($result->num_rows > 0) {
                        while ($row = $result->fetch_assoc()) {
                            echo "<tr>";
                            echo "<td>".$row['id']."</td>";
                            echo "<td>".$row['email']."</td>";
                            echo "<td>".$row['username']."</td>";
                            echo "<td>".$row['nome']."</td>";
                            echo "<td>".$row['password']."</td>";
                            echo "<td>".$row['telefone']."</td>";
                            echo "<td>".$row['morada']."</td>";
                            echo "<td>".$row['idade']."</td>";
                            echo "<td><a href='delete.php?id=".$row['id']."' class='btn btn-danger btn-xs' role='button'><span class='glyphicon glyphicon-remove'></span></a></td>";
                            echo "</tr>";
                        }
                    }
                    ?>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel-footer">
        <?php
        echo "Total: ".$result->num_rows;
        include './mysql/mysqlClose.php';
        ?>
    </div>
</div>

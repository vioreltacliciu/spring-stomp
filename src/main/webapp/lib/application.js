function ExampleController($scope){
    $scope.usersConnect=function(){
        var socket=new SockJS('/spring-stomp/users');
        this.stompClient=Stomp.over(socket);
        var stmCl=this.stompClient;
        stmCl.connect('paulson', 'bond', function(frame) {
            stmCl.subscribe("/user/queue/users-list",function(message){
                console.log(JSON.parse(message.body));
//            $scope.users=JSON.parse(message.body);

            });

            stmCl.subscribe("/user/queue/errors", function(message) {
                console.log("Error " + message.body);
            });
        }, function(error) {
            console.log("STOMP protocol error " + error);
        });

    }
}
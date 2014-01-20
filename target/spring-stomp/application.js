function ExampleController($scope){
    $scope.usersConnect=function(){
        var socket=new SockJS('/spring-stomp/users');
        this.stompClient=Stomp.over(socket);
        this.stompClient.subscribe("/topic/users.list.*",function(message){
           console.log(JSON.parse(message.body));
//            $scope.users=JSON.parse(message.body);

        });

        this.stompClient.subscribe("/user/queue/errors", function(message) {
            console.log("Error " + message.body);
        });
    }
}
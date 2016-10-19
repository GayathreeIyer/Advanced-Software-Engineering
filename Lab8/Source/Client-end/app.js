var app=angular.module("calculate",[]);
app.controller("calculatectrl",function ($scope,$http) {
    $scope.FPC = function () {
        var weight=$scope.w;
        var height=$scope.h;
		 var age=$scope.a;



        var words = $http.get("http://localhost:8080/RESTExample/mashup/"+weight+"/"+height+"/"+age);
        words.success(function (data) {
            console.log(data);
            $scope.cal={"calorie":data.calorie,"fat":data.fat,"protein":data.protein,"carb":data.carb};

        });
    }
});

var app=angular.module("validate",[]);
app.controller("validatectrl",function ($scope,$http) {
    $scope.get = function () {
        var num=$scope.n;

        var words = $http.get("http://127.0.0.1:8081/getdata/"+num)
        words.success(function (data) {
            console.log(data);

            $scope.details={"valid":data.body[0].valid,"number":data.body[0].number,"local_format":data.body[0].local_format,"international_format":data.body[0].international_format,"country_prefix":data.body[0].country_prefix,"country_code":data.body[0].country_code,"country_name":data.body[0].country_name,"location":data.body[0].location,"carrier":data.body[0].carrier,"line_type":data.body[0].line_type,"positive":data.body[0].positive,"negative":data.body[0].negative
            };
            console.log(data.body[0].positive)
        });
    }
});


// Declare app level module which depends on views, and components
angular.module('myApp', [])


    .controller('MainCtrl', function ($scope, $http) {
        $scope.venueList = new Array();
        $scope.mostRecentReview;
 $scope.navigator.splashscreen.show();
        $scope.getVenues = function () {
            var placeEntered = document.getElementById("city").value;
            var searchQuery = document.getElementById("cloth").value;
            if (placeEntered != null && placeEntered != "" && searchQuery != null && searchQuery != "") {
                document.getElementById('div_ReviewList').style.display = 'none';
                //This is the API that gives the list of venues based on the place and search query.
                var handler = $http.get("https://api.foursquare.com/v2/venues/search" +
                    "?client_id=QLX5YX3SEGBFC41CQ4KKGGHMTL2KMSGRIPHOOFAI0GURWGWL" +
                    "&client_secret=1TCYKWCQYGJTOWUNDWOUCGM5DUFU5LA3FPA1M5UFJ10QZYHN" +
                    "&v=20160215&limit=5" +
                    "&near=" + placeEntered +
                    "&query=" + searchQuery);
                handler.success(function (data) {

                    if (data != null && data.response != null && data.response.venues != undefined && data.response.venues != null) {
                        for (var i = 0; i < data.response.venues.length; i++) {
                            $scope.venueList[i] = {
                                "name": data.response.venues[i].name,
                                "id": data.response.venues[i].id,
                                "location": data.response.venues[i].location
                            };
                        }
                    }

                })
                handler.error(function (data) {
                    alert("There was some error processing your request.");
                });
            }
        }
        $scope.getReviews = function (venueSelected) {
            if (venueSelected != null) {
                 var handler = $http.get("https://api.foursquare.com/v2/venues/" + venueSelected.id + "/tips" +
                    "?sort=recent" +
                    "&client_id=Q0ENF1YHFTNPJ31DCF13ALLENJW0P5MTH13T1SA0ZP1MUOCI" +
                    "&client_secret=ZH4CRZNEWBNTALAE3INIB5XG0QI12R4DT5HKAJLWKYE1LHOG&v=20160215" +
                    "&limit=5");
                handler.success(function (result) {
                    if (result != null && result.response != null && result.response.tips != null &&
                        result.response.tips.items != null) {
                        $scope.mostRecentReview = result.response.tips.items[0];
                      var callback = $http.get("https://api.uclassify.com/v1/uclassify/genderanalyzer_v5/Classify?readkey=sfvA14Vws29a&text=" + $scope.mostRecentReview.text);
                        callback.success(function (data) {
                            if(data!=null)
                            {
                                $scope.GenderAnalyzer = {"female" : data.female,
                                                            "male":data.male,
															
                                                             };
                                document.getElementById('div_ReviewList').style.display = 'block';


                            }
                        })
                    }
                })
                handler.error(function (result) {
                    alert("There was some error processing your request.")
                })
            }

        }

    });

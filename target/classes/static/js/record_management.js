
        app.controller("RecordController", function($rootScope, $scope, $http) {
            $scope.records = [];
            recordList();
            function recordList() {
                $http({
                    method : 'GET',
                    url : "/getRecordList"
                }).then(function successCallback(response) {
                	console.log(response.data);
                	$scope.records = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }
            $rootScope.$on("refreshRecord", function (event, args) {
            	console.log(args.message + "successful");
            	recordList();
            });
        });
        app.controller("PortalController", function($rootScope, $scope, $http) {
            
        	$scope.portal = {};
        	$scope.portal.student = {};
        	$scope.portal.book = {};
        	$scope.portal.record = {};
        	portalList();
        	$scope.portal.messageS = false;
        	$scope.portal.messageE = false;
            function portalList() {
                $http({
                    method : 'GET',
                    url : "/getArraylist"
                }).then(function successCallback(response) {
                	console.log(response.data);
                	$("#portal_std_search").autocomplete({
        				source : response.data[0]
        			});
                	$("#portal_book_search").autocomplete({
        				source : response.data[1]
        			});
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            };
            $scope.portal_issueBook = function(){
            	var url = "/portalIssueBook?query=" + $scope.portal.student.email + "," + $scope.portal.book.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
            };
            $scope.portal_reIssueBook = function(){
            	var url = "/portalReIssueBook?query=" + $scope.portal.student.email + "," + $scope.portal.book.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
            };
            $scope.portal_returnBook = function(){
            	var url = "/portalReturnBook?query=" + $scope.portal.student.email + "," + $scope.portal.book.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
            };
            $scope.populatePortalDetails = function(){
            	var url = "/portalDetails?query=" + $scope.portal.student.search + "," + $scope.portal.book.search;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _successSearch, _error );
            };
            
            function _success(response){
            	console.log(response.data);
            	if(response.data.responseCode == "success"){
                	$scope.portal.messageS = true;
            		$scope.portal.response_message_success = response.data.response;
                	$rootScope.$emit("refreshRecord", {message: "Refresh Record" });
            	}else if(response.data.responseCode == "error"){
            		$scope.portal.messageE = true;
            		$scope.portal.response_message_error = response.data.response;
            	}
            };
            
            function _successSearch(response) {
                clearForm();
                $scope.portal.messageS = false;
            	$scope.portal.messageE = false;
                if(null != response.data[0])	populateStudent(response.data[0]);
                if(null != response.data[1])	populateBook(response.data[1]);
                if(null != response.data[2])	populateRecord(response.data[2]);
            };
            function _error(response) {
            	console.log("Error:"+response.data);
                console.log(response.statusText);
            };
            function populateStudent(object){
            	$scope.portal.student.id = object.id;
            	$scope.portal.student.name = object.name ;
            	$scope.portal.student.email = object.email ;
            	$scope.portal.student.year = object.year ;
            	$scope.portal.student.stream = object.stream ;
            	$scope.portal.student.roll = object.roll ;
            	$scope.portal.student.max_books = object.max_books ;
            	$scope.portal.student.book_count = object.book_count ;
            };
            function populateBook(object){
            	$scope.portal.book.name = object.name ;
            	$scope.portal.book.id = object.id ;
            	$scope.portal.book.author = object.author ;
            	$scope.portal.book.category = object.category ;
            	$scope.portal.book.sub_category = object.sub_category ;
            	$scope.portal.book.count = object.count ;
            	$scope.portal.book.total = object.total ;
            };
            function populateRecord(object){
            	$scope.portal.record.issue_date = object.issue_date ;
            	$scope.portal.record.renew_date = object.renew_date ;
            	$scope.portal.record.due = object.due ;
            	$scope.portal.record.rate = object.rate ;
            	$scope.portal.record.fine = object.fine ;
            };
            function clearForm(){
            	$scope.portal.student.id = "";
            	$scope.portal.student.name = "" ;
            	$scope.portal.student.email = "" ;
            	$scope.portal.student.year = "" ;
            	$scope.portal.student.stream = "" ;
            	$scope.portal.student.roll = "" ;
            	$scope.portal.student.max_books = "" ;
            	$scope.portal.student.book_count = "" ;
            	$scope.portal.book.name = "" ;
            	$scope.portal.book.id = "";
            	$scope.portal.book.author = "" ;
            	$scope.portal.book.category = "" ;
            	$scope.portal.book.sub_category = "" ;
            	$scope.portal.book.count = "" ;
            	$scope.portal.book.total = "" ;
            	$scope.portal.record.issue_date = "" ;
            	$scope.portal.record.renew_date = "" ;
            	$scope.portal.record.due = "" ;
            	$scope.portal.record.rate = "" ;
            	$scope.portal.record.fine = "" ;
            };
        });
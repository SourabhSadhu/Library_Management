
        app.controller("BookController", function($scope, $http, $filter) {
            $scope.countries = [];
            $scope.bookForm = {
                id : -1,
                name : "",
                author : "",
                category : "",
                sub_category : "",
                count : "",
                total : ""
            };
            bookList();
			//Logic to populate book details in bookEdit form
            $scope.editBook = function(book) {
            	$scope.create_book=true;
    		    $scope.bookForm.name = book.name;
    		    $scope.bookForm.author = book.author;
    		    $scope.bookForm.category = book.category;
    		    $scope.bookForm.sub_category = book.sub_category;
    		    $scope.bookForm.count = book.count;
    		    $scope.bookForm.total = book.total;
    		};
    		//Logic to submit bookDetails to controller
            $scope.submitBook = function(){
    		    var url = "";
    		    if ($scope.bookForm.id == -1) {
    		        url = '/createBook';
    		    } else {
    		        url = '/editBook';
    		    }
    		
    		    $http({
    		        method : 'POST',
    		        url : url,
    		        data : angular.toJson($scope.bookForm),
    		        headers : {
    		            'Content-Type' : 'application/json'
    		        }
    		    }).then( _success, _error );
    		};
    		$scope.deleteBook = function (book){
    			var url = "/deleteBook?id=" + book.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
    		};
    		//Logic to get userDetails from controller
            function bookList() {
                $http({
                    method : 'GET',
                    url : "/getBookList"
                }).then(function successCallback(response) {
                	console.log(response.data);
                	$scope.books = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }
    		$scope.clearBook = function clearBook(){
    		    $scope.bookForm.id = -1;
    		    $scope.bookForm.name = "";
    		    $scope.bookForm.author = "";
    		    $scope.bookForm.category = "";
    		    $scope.bookForm.sub_category= "";
    		    $scope.bookForm.count= "";
    		    $scope.bookForm.total= "";
    		}
            function _success(response) {
            	bookList();
                $scope.bookForm.id = -1;
    		    $scope.bookForm.name = "";
    		    $scope.bookForm.author = "";
    		    $scope.bookForm.category = "";
    		    $scope.bookForm.sub_category= "";
    		    $scope.bookForm.count= "";
    		    $scope.bookForm.total= "";
            }
            function _error(response) {
                console.log(response.statusText);
            }
        });
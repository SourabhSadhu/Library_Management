        app.controller("StudentController", function($scope, $http, newStudent) {
            $scope.students = [];
            $scope.studentForm = {
                id: -1,
            	name: "",
                email: "",
                year: "",
                stream: "",
                roll: "",
                max_books: 3,
                book_count: ""
            };
            studentList();
            $scope.createNewEntry = function(){
            	$scope.create_student=true;
            	$scope.clearStudent;
    			var stdList = newStudent.getStudObject();
    			var stdNames = [];
    			var stdEmails = [];
    			for(var iter = 0; iter < stdList.length; iter++){
    				stdNames.push(stdList[iter].name);
    				stdEmails.push(stdList[iter].email);
    			}
    			$("#student_name").autocomplete({
    				source : stdNames
    			});
            	$("#student_email").autocomplete({
    				source : stdEmails
    			});
            };
			//Logic to populate Student details in StudentEdit form
            $scope.editStudent = function(student) {   
            	$scope.create_student=true;
    		    $scope.studentForm.id = student.id;
    		    $scope.studentForm.name = student.name;
    		    $scope.studentForm.email = student.email;
    		    $scope.studentForm.year = student.year;
    		    $scope.studentForm.stream = student.stream;
    		    $scope.studentForm.roll = student.roll;
    		    $scope.studentForm.max_books = student.max_books;
    		    $scope.studentForm.book_count = student.book_count;
    		};
    		//Logic to submit studentDetails to controller
            $scope.submitStudent = function(){
    		    var url = "";
    		    if ($scope.studentForm.id == -1) {
    		        url = '/createStudent';
    		    } else {
    		        url = '/editStudent';
    		    }
    		
    		    $http({
    		        method : 'POST',
    		        url : url,
    		        data : angular.toJson($scope.studentForm),
    		        headers : {
    		            'Content-Type' : 'application/json'
    		        }
    		    }).then( _success, _error );
    		};
    		$scope.deleteStudent = function (student){
    			var url = "/deleteStudent?id=" + student.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
    		};
    		//Logic to get studentDetails from controller
            function studentList() {
                $http({
                    method : 'GET',
                    url : "/getStudentList"
                }).then(function successCallback(response) {
                	console.log(response.data);
                	$scope.students = response.data;
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            };
    		$scope.clearStudent = function clearStudent(){
    			$scope.studentForm.id = -1,
    			$scope.studentForm.name = "",
    			$scope.studentForm.email = "",
    			$scope.studentForm.year = "",
    			$scope.studentForm.stream = "",
    			$scope.studentForm.roll = "",
    			$scope.studentForm.max_books = 3,
    			$scope.studentForm.book_count = ""
    		};
            function _success(response) {
                studentList();
                $scope.studentForm.id = -1,
                $scope.create_student=false;
    			$scope.studentForm.name = "",
    			$scope.studentForm.email = "",
    			$scope.studentForm.year = "",
    			$scope.studentForm.stream = "",
    			$scope.studentForm.roll = "",
    			$scope.studentForm.max_books = 3,
    			$scope.studentForm.book_count = ""
            };
            function _error(response) {
                console.log(response.statusText);
            };
            /*function autoComplete(){
            	console.log("************Get Data************");
            	console.log(newStudent.getStudentName());
            	console.log(newStudent.getStudentEmail());
            };*/
        });
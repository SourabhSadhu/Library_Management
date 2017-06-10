
        app.controller("UserController", function($scope, $http, $filter, newStudent) {
            $scope.users = [];
            $scope.user = [];
            $scope.userForm = {
                role_name : "",
                id : -1,
                name : "",
                email : "",
                password : "",
                active_till : ""
            };
            //Test
            $scope.testCall = function() {
	           	console.log("2 is called");
            };
            userList();
            hideDiv();
            function hideDiv(){
	            $scope.user.messageS = false;
	        	$scope.user.messageE = false;
            };
			//Logic to populate user details in userEdit form
            $scope.editUser = function(user) {
            	hideDiv();
            	$scope.create_user=true;
    		    $scope.userForm.role_name = user.role_name;
    		    $scope.userForm.id = user.id;
    		    $scope.userForm.name = user.name;
    		    $scope.userForm.email = user.email;
    		    $scope.userForm.password = user.password;
    		    $scope.userForm.active_till = $filter('date')(user.active_till,'yyyy-MM-dd HH:mm:ss Z');
    		    //$scope.fomated_date = user.active_till;
    		};
    		//Logic to submit userDetails to controller
            $scope.submitUser = function(){
            	hideDiv();
    		    var url = "";
    		    var today = new Date($scope.userForm.active_till);
        		console.log(today.getTime());
        		$scope.userForm.active_till = today.getTime();
    		    
    		    if ($scope.userForm.id == -1) {
    		        url = '/createUser';
    		    } else {
    		        url = '/editUser';
    		    }
    		
    		    $http({
    		        method : 'POST',
    		        url : url,
    		        data : angular.toJson($scope.userForm),
    		        headers : {
    		            'Content-Type' : 'application/json'
    		        }
    		    }).then( _success, _error );
    		};
    		$scope.deleteUser = function (user){
    			hideDiv();
    			var url = "/deleteUser?id=" + user.id;
    			console.log(url);
    			$http({
    		        method : 'GET',
    		        url : url,
    		    }).then( _success, _error );
    		};
    		//Logic to get userDetails from controller
    		var studentAutoCompleteList = [];
            function userList() {
                $http({
                    method : 'GET',
                    url : "/getUserList"
                }).then(function successCallback(response) {
                	console.log(response.data);
                	$scope.users = response.data;
                	for(var i = 0; i<$scope.users.length;i++){
                		if($scope.users[i].role == 3){
                			var studentObject = { email : $scope.users[i].email , name : $scope.users[i].name};
                			studentAutoCompleteList.push(studentObject);
	                		/*newStudent.addStudentName($scope.users[i].name);
	                		newStudent.addStudentEmail($scope.users[i].email);*/
                		}
                	}
                	newStudent.setStudObject(studentAutoCompleteList);
                }, function errorCallback(response) {
                    console.log(response.statusText);
                });
            }
    		$scope.clearUser = function clearUser(){
    			hideDiv();
    			$scope.userForm.role_name = "";
    		    $scope.userForm.id = -1;
    		    $scope.userForm.name = "";
    		    $scope.userForm.email = "";
    		    $scope.userForm.password = "";
    		    $scope.userForm.active_till = "";
    		}
            function _success(response) {
                userList();
                if(response.data.responseCode == "success"){
	        		$scope.user.messageS = true;
            		$scope.user.response_message_success = response.data.response;
            	}else if(response.data.responseCode == "error"){
            		$scope.user.messageE = true;
            		$scope.user.response_message_error = response.data.response;
            	}
	        	
	        	
                $scope.create_user=false;
                $scope.userForm.role_name = "";  
    		    $scope.userForm.id = -1;
    		    $scope.userForm.name = "";
    		    $scope.userForm.email = "";
    		    $scope.userForm.password = "";
    		    $scope.userForm.active_till = "";
            }
            function _error(response) {
                console.log(response.statusText);
            }
            
        });
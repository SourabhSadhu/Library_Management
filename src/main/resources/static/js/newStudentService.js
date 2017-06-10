app.service("newStudent", function(){
	var studentName = [];
	var studentEmail = [];
	var studObject = [];
	
	/*var addStudentName = function (objName){
		studentName.push(objName);
	};
	var addStudentEmail = function (objEmail){
		studentEmail.push(objEmail);
	};
	var getStudentName = function (){
		return studentName;
	};
	var getStudentEmail = function (){
		return studentEmail;
	};*/
	var setStudObject = function(obj){
		studObject = obj;
	};
	var getStudObject = function(){
		return studObject;
	};
	
	
	return {
		/*addStudentName : addStudentName,
		addStudentEmail : addStudentEmail,
		getStudentName : getStudentName,
		getStudentEmail : getStudentEmail,*/
		setStudObject : setStudObject,
		getStudObject : getStudObject
	};
});
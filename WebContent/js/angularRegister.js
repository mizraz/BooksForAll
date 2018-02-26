angular.module('myApp').controller("Ctrl", 
			['$scope','$rootScope','$http', function($scope, $rootScope, $http) {	
		$scope.errormsgg="";

		//remain on hide when the page is loaded
		$scope.errormsg1="";
		$scope.errormsg2="";
		$scope.errormsg3="";
		$scope.errormsg4="";
		$scope.errormsg5="";
		$scope.errormsg6="";
		$scope.errormsg7="";
		$scope.errormsg8="";
		$scope.errormsg9="";
		$scope.errormsg10="";
		$scope.errormsg12="Nickname already exists";
		$('#alert1').hide();
		$('#alert2').hide();
		$('#alert3').hide();
		$('#alert4').hide();
		$('#alert5').hide();
		$('#alert6').hide();
		$('#alert7').hide();
		$('#alert8').hide();
		$('#alert9').hide();
		$('#alert10').hide();
		$('#alert12').hide();
	    $scope.SubmitFunction = function()
	    {
	    	
	    	var CheckFlag=1;//checks if all parameters were entered correct way
			 var letters = /^[A-Z a-z]+$/;//for parameters that have to match only letters
			 var numbers = /[0-9]/;//for parameters that have to match only numbers
			 var homePhone1 = /^04([0-9]{7})?$/;//pattern to phone number which starts with 04
			 var homePhone2 = /^02([0-9]{7})?$/;//pattern to phone number which starts with 02
			 var homePhone3 = /^03([0-9]{7})?$/;//pattern to phone number which starts with 03
			 var homePhone4 = /^09([0-9]{7})?$/;//pattern to phone number which starts with 09
			 var cellPhone = /^05([0-9]{8})?$/;//pattern to phone number which starts with 05
			 var validEmail =  
			    		/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;//pattern to email
			$('#alert1').hide();
			$('#alert2').hide();
			$('#alert3').hide();
			$('#alert4').hide();
			$('#alert5').hide();
			$('#alert6').hide();
			$('#alert7').hide();
			$('#alert8').hide();
			$('#alert9').hide();
			$('#alert10').hide();
			$('#alert12').hide();
			 if(!($scope.name.match(letters)))//checks validity of name
			 {
		    	    CheckFlag=0;
				    $scope.errormsg1="Use letters only";
					$('#alert1').show();
			 }

			 if((!($scope.nick.match(letters)))&&(!($scope.nick.match(numbers))))//checks validity of userName
			 {
				    CheckFlag=0;
				    $scope.errormsg2="Use letters and numbers";
					$('#alert2').show();
			 }
			 if($scope.pwd.length==0)//check that password is not empty
			 {
			    CheckFlag=0;
			    $scope.errormsg3="You must enter password";
				$('#alert3').show();
			 }
		     if($scope.pwd.length<3)//checks that password is not too short
		     {
		        CheckFlag=0;
			    $scope.errormsg3="Your Password is too short";
				$('#alert3').show();
		     }
		     if(!($scope.email.match(validEmail)))//checks email validity
		     {
		         CheckFlag=0;
		         $scope.errormsg5="Your email invalid";
		         $('#alert5').show()
		     }
		     //validates phone number
			 if(!(($scope.phone.match(homePhone1))||($scope.phone.match(homePhone2))||($scope.phone.match(homePhone3))||($scope.phone.match(homePhone4))|| ($scope.phone.match(cellPhone))))
			  {
			      CheckFlag=0;
				  $scope.errormsg4="Your phone number is invalid";
				  $('#alert4').show();
			  }
			 //country validation
			  if(($scope.country.length < 3) || (!($scope.country.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg6="Your country must be letters long at least";
				  $('#alert6').show();
			  }
			  if(($scope.city.length < 3) || (!($scope.city.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg7="Your city must be letters long at least";
				  $('#alert7').show();
			  }
			  if(($scope.street.length < 3) || (!($scope.street.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg8="Your street must be letters long at least";
				  $('#alert8').show();
			  }
			  if(($scope.hnumb.length == 0) || (!($scope.hnumb.match(numbers))))
			  {
			      CheckFlag=0;
			      $scope.errormsg9="Please enter your block number";
				  $('#alert9').show();
			  }
			  if($scope.zip.length != 7)
			  {
			      CheckFlag=0;
			      $scope.errormsg10="All zipcodes must have 7 digits";
				  $('#alert10').show();
			  }
	  
	    	if(CheckFlag==1)
	    	{
	    		if ($scope.userImageUrl == 0)
	    			$scope.userImageUrl="https://vignette.wikia.nocookie.net/simpsons/images/1/11/Homersimpson.jpg/revision/latest?cb=20121229201104";
	    		var data =
	    		{
	    			userName: $scope.name,
	    			email: $scope.email,
	    			userNickname : $scope.nick,
	    			pwd: $scope.pwd,
	    			address:$scope.country +"," + $scope.city +","+ $scope.street + ","+ $scope.hnumb +","+ $scope.zip,
	    			phoneNumber: $scope.phone,
	    			description: $scope.desc,
	    			userImageUrl: $scope.userImageUrl	
	    		}
	    		request = $.post("http://localhost:8080/BooksForAll/UserRegisterServlet",JSON.stringify(data));
	    		console.log(request);
	    		request.then(function (response, textStatus, jqXHR)
	    		{      console.log("HELLO");
	    	       	window.location = 'http://localhost:8080/ExampleServletv3/index.html';
	    	    });
	    		request.fail(function() {			
	    			$scope.errormsg12="Nickname "+$scope.nick+" already exists, enter a new one and try again";
	    			$('#alert12').show();
					//window.location = 'http://localhost:8080/ExampleServletv3/KREbooks/index.html';
	    		});


	    		
	    	}
	    	else
	    	{
	    		
	    		$scope.errormsgg="The Username or Password are too short";
	    		//$('.alert').show();
	    	}
	    };
	}]);
	
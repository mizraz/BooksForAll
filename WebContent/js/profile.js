// user profile controller
	angular.module('myApp').controller("CtrlDetail", 
			['$scope','$rootScope','$http', function($scope, $rootScope, $http) {
	
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
	    $scope.Update = function()
	    {
	    	//validating the input
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
		    if(!($scope.userLogedIn.userName.match(letters)))//checks validity of name
			 {
		    	    CheckFlag=0;
				    $scope.errormsg1="Use letters only";
					$('#alert1').show();
			 }

			 if((!($scope.userLogedIn.userNickname.match(letters)))&&(!($scope.userLogedIn.userNickname.match(numbers))))//checks validity of userName
			 {
				    CheckFlag=0;
				    $scope.errormsg2="Use letters only";
					$('#alert2').show();
			 }
			 if($scope.userLogedIn.pwd.length==0)//check that password is not empty
			 {
			    CheckFlag=0;
			    $scope.errormsg3="OOPS! You forgot your password";
				$('#alert3').show();
			 }
		     if($scope.userLogedIn.pwd.length<3)//checks that password is not too short
		     {
		        CheckFlag=0;
			    $scope.errormsg3="Your Password is too short";
				$('#alert3').show();
		     }
		     if(!($scope.userLogedIn.email.match(validEmail)))//checks email validity
		     {
		         CheckFlag=0;
		         $scope.errormsg5="Your email invalid";
		         $('#alert5').show()
		     }
		     //validates phone number
			 if(!(($scope.userLogedIn.phoneNumber.match(homePhone1))||($scope.userLogedIn.phoneNumber.match(homePhone2))||($scope.userLogedIn.phoneNumber.match(homePhone3))||($scope.userLogedIn.phoneNumber.match(homePhone4))|| ($scope.userLogedIn.phoneNumber.match(cellPhone))))
			  {
			      CheckFlag=0;
				  $scope.errormsg4="Your phone number is invalid";
				  $('#alert4').show();
			  }
			 //country validation
			  if(($scope.userLogedIn.country.length < 3) || (!($scope.userLogedIn.country.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg6="Your country must be letters long at least";
				  $('#alert6').show();
			  }
			  if(($scope.userLogedIn.city.length < 3) || (!($scope.userLogedIn.city.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg7="Your city must be letters long at least";
				  $('#alert7').show();
			  }
			  if(($scope.userLogedIn.street.length < 3) || (!($scope.userLogedIn.street.match(letters))))
			  {
			      CheckFlag=0;
			      $scope.errormsg8="Your street must be letters long at least";
				  $('#alert8').show();
			  }
			  if(($scope.userLogedIn.hnumb.length == 0) || (!($scope.userLogedIn.hnumb.match(numbers))))
			  {
			      CheckFlag=0;
			      $scope.errormsg9="Please enter your block number";
				  $('#alert9').show();
			  }
			  if($scope.userLogedIn.zip.length != 7)
			  {
			      CheckFlag=0;
			      $scope.errormsg10="All zipcodes must have 7 digits";
				  $('#alert10').show();
			  }
			    	if(CheckFlag==1)

			    	{
			    		if ($scope.userLogedIn.userImageUrl == 0)
			    			$scope.userLogedIn.userImageUrl="https://vignette.wikia.nocookie.net/simpsons/images/1/11/Homersimpson.jpg/revision/latest?cb=20121229201104";
			    		//making data to send to the server via JSON
			    		var data =
			    		{
			    			userName: $scope.userLogedIn.userName,
			    			email: $scope.userLogedIn.email,
			    			userNickname : $scope.userLogedIn.userNickname,
			    			pwd: $scope.userLogedIn.pwd,
			    			address:$scope.userLogedIn.country +"," + $scope.userLogedIn.city +","+ $scope.userLogedIn.street + ","+ $scope.userLogedIn.hnumb +","+ $scope.userLogedIn.zip,
			    			phoneNumber: $scope.userLogedIn.phoneNumber,
			    			description: $scope.userLogedIn.description,
			    			userImageUrl: $scope.userLogedIn.userImageUrl,
			    			
			    		}//data
			    		request = $.post("http://localhost:8080/BooksForAll/updateUserDetails",JSON.stringify(data));//sends request to the server to update users details
			    		request.done(function (response, textStatus, jqXHR)
			    		{
			    			    window.alert("Your Information has been updated");
			    	        	
			    	    });//then

			
			    		request.fail(function() {
			    			
							$scope.errormsg12="Nickname "+$scope.nick+" already exists, enter a new one and try again";
							$('#alert12').show();
			    		});//fail
			    		
			    	}//if
			    };//update
	}]);//myAPP
	
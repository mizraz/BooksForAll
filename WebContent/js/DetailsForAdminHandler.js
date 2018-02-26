	angular.module('myApp').controller("CtrlDetailsForAdmin", 
			['$scope','$rootScope','$http', function($scope, $rootScope, $http) {

				
				
				$rootScope.curUserAdminSelected;
				console.log("on CtrlDetailsForAdmin controller, $rootScope.curUserAdminSelected: "+ $rootScope.curUserAdminSelected);
				
	    		var data =
	    		{
	    			//TODO : Pass email  of the user from users list you want to see
	    				user:$rootScope.curUserAdminSelected

	    		}
	    		
				
	    		$http.get("http://localhost:8080/BooksForAll/returnUserDetails/user/"+$rootScope.curUserAdminSelected.email )

				.then(function(response) {
					$scope.records = response;
				        console.log(response);
						$scope.name = response.data[0].userName;
						$scope.email = response.data[0].email;
						$scope.nick = response.data[0].userNickname;
						$scope.desc = response.data[0].description;
						$scope.phoneNumber = response.data[0].phoneNumber;
						$scope.photo = response.data[0].userImageUrl;
						console.log(response.data[0].address.split(",")[0]);
						
						if(response.data[0].address.split(",")[0] != null)
						$scope.country = response.data[0].address.split(",")[0];
						if(response.data[0].address.split(",")[1] != null)
							$scope.city = response.data[0].address.split(",")[1];
						if(response.data[0].address.split(",")[2] != null)
							$scope.street = response.data[0].address.split(",")[2];
						if(response.data[0].address.split(",")[3] != null)
							$scope.hnumb = response.data[0].address.split(",")[3];
						if(response.data[0].address.split(",")[4] != null)
							$scope.zip = response.data[0].address.split(",")[4];
						

						

						 	

				});
	    		

	}]);
	
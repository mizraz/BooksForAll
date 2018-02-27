	angular.module('myApp').controller("CtrlDetailsForAdmin", 
			['$scope','$rootScope','$http', function($scope, $rootScope, $http) {

				var ctrl = this;
				this.$onInit = function () {
					ctrl = this;
				};
				
				//user the admin selected
				$rootScope.curUserAdminSelected;
				console.log("on CtrlDetailsForAdmin controller, $rootScope.curUserAdminSelected: "+ $rootScope.curUserAdminSelected);
				
	    		var data =
	    		{
	    			
	    				user:$rootScope.curUserAdminSelected

	    		}
	    		//get request to server: the server has to send the chosen user details
	    		$http.get("http://localhost:8080/BooksForAll/returnUserDetails/user/"+$rootScope.curUserAdminSelected.email )

				.then(function(response) {
					$scope.records = response;
	    			

	    		
	    		        //the chosen user details
						$scope.name = response.data[0].userName;
						$scope.email = response.data[0].email;
						$scope.nick = response.data[0].userNickname;
						$scope.desc = response.data[0].description;
						$scope.phoneNumber = response.data[0].phoneNumber;
						$scope.photo = response.data[0].userImageUrl;
						
						
						
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
	    		
	    		
	    		
	    		$scope.confirmDeleteUser = function() {
	    			console.log("in confirmDeleteUser");
	    			window.curUserToDelete = $rootScope.curUserAdminSelected;
	    			console.log("window.curUserToDelete.email: " + window.curUserToDelete.email);
	    			$("#confirmDeleteUserModal").modal();

	    		};
                
                //if admin wants to delete user
	    		$scope.deleteUser = function(isToDeleteUser) {
	    			console.log("window.curUserToDelete.email in deleteUser: " + window.curUserToDelete.email);

	    			console.log("ctrl.user.email: " + $rootScope.curUserAdminSelected.email);

	    			if(!window.curUserToDelete.email.includes('admin')) {

	    				if (isToDeleteUser) {
	    					var userToDelete =
	    					{
	    							email: window.curUserToDelete.email
	    					};
                            //the chosen users email sent to server with request to delete
	    					$http.post("http://localhost:8080/BooksForAll/deleteUser", JSON.stringify(userToDelete)) 
	    					.then(function(response) {

	    						console.log("delete request sent!");
	    						$rootScope.curPage = $rootScope.pagesPaths.usersList;
	    		    			$("#confirmDeleteUserModal").modal('hide');

	    					});    			
	    				}
	    				else {
	    	    			$("#confirmDeleteUserModal").modal('hide');
	    				}
	    			}
	    		};
	    		
	    		

	}]);
	
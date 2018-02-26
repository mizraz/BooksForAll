	angular.module('myApp').controller("CtrlDetailsForAdmin", 
			['$scope','$rootScope','$http', function($scope, $rootScope, $http) {

				var ctrl = this;
				this.$onInit = function () {
					ctrl = this;
				};
				
				
				$rootScope.curUserAdminSelected;
				console.log("on CtrlDetailsForAdmin controller, $rootScope.curUserAdminSelected: "+ $rootScope.curUserAdminSelected);
				
	    		var data =
	    		{
	    			//TODO : Pass email  of the user from users list you want to see
	    				user:$rootScope.curUserAdminSelected

	    		}
	    		$http.get("http://localhost:8080/BooksForAll/returnUserDetails/user/"+$rootScope.curUserAdminSelected.email )

//				.then(function(response) {
//					$scope.records = response;
	    			
//				        console.log(response);
	    		
	    		
						$scope.name = $rootScope.curUserAdminSelected.userName;
						$scope.email = $rootScope.curUserAdminSelected.email;
						$scope.nick = $rootScope.curUserAdminSelected.userNickname;
						$scope.desc = $rootScope.curUserAdminSelected.description;
						$scope.phoneNumber = $rootScope.curUserAdminSelected.phoneNumber;
						$scope.photo = $rootScope.curUserAdminSelected.userImageUrl;
						
						console.log($rootScope.curUserAdminSelected.address.split(",")[0]);
						
						if($rootScope.curUserAdminSelected.address.split(",")[0] != null)
						$scope.country = $rootScope.curUserAdminSelected.address.split(",")[0];
						if($rootScope.curUserAdminSelected.address.split(",")[1] != null)
							$scope.city = $rootScope.curUserAdminSelected.address.split(",")[1];
						if($rootScope.curUserAdminSelected.address.split(",")[2] != null)
							$scope.street = $rootScope.curUserAdminSelected.address.split(",")[2];
						if($rootScope.curUserAdminSelected.address.split(",")[3] != null)
							$scope.hnumb = $rootScope.curUserAdminSelected.address.split(",")[3];
						if($rootScope.curUserAdminSelected.address.split(",")[4] != null)
							$scope.zip = $rootScope.curUserAdminSelected.address.split(",")[4];
						

//				});
	    		
	    		
	    		
	    		$scope.confirmDeleteUser = function() {
	    			console.log("in confirmDeleteUser");
	    			window.curUserToDelete = $rootScope.curUserAdminSelected;
	    			console.log("window.curUserToDelete.email: " + window.curUserToDelete.email);
	    			$("#confirmDeleteUserModal").modal();

	    		};


	    		$scope.deleteUser = function(isToDeleteUser) {
	    			console.log("window.curUserToDelete.email in deleteUser: " + window.curUserToDelete.email);

	    			console.log("ctrl.user.email: " + $rootScope.curUserAdminSelected.email);

	    			if(!window.curUserToDelete.email.includes('admin')) {

	    				if (isToDeleteUser) {
	    					var userToDelete =
	    					{
	    							email: window.curUserToDelete.email
	    					};

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
	
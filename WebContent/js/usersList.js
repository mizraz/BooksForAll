angular.module('myApp').controller("usersListController", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			ctrl.usersList = [];

			
			// get all users registered store.
			$http.get("http://localhost:8080/BooksForAll/usersList") ///name/Alfreds Futterkiste
			.then(function(response) {
				$scope.records = response;
				$scope.usersRegisteredList = $scope.records;//this variable will hold the search usersRegisteredLists
				console.log($scope.usersRegisteredList);
				console.log('arr length ' + $scope.usersRegisteredList.data.length);
				for (var userIndex in $scope.usersRegisteredList.data) {
					ctrl.usersList.push($scope.usersRegisteredList.data[userIndex]);
					
					
					console.log("$scope.usersRegisteredList.data[userIndex] : " + $scope.usersRegisteredList.data[userIndex]);
					console.log("ctrl.usersList[userIndex].name : " + ctrl.usersList[userIndex].userName);
					
				}

			});
			
			ctrl.goToUserDetailsPage = function(userSelectedByAdmin) {
				console.log("in: ctrl.goToUserDetailsPage, user: " + userSelectedByAdmin.userName);
				$rootScope.curUserAdminSelected = userSelectedByAdmin;
				$rootScope.curPage = $rootScope.pagesPaths.userDetailsPageForAdmin;
				
			}
			


		}]);




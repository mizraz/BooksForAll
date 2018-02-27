//controller for the catalog - the list of ebooks the store offers
angular.module('myApp').controller("catalogController", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			// ctrl.ebook - current ebook selected.
			ctrl.ebook = $rootScope.curEbook;
			this.$onInit = function() {
				$scope.userNickname = "Welcome Back, " + $rootScope.userLogedIn.userNickname + " !" + " Explore our catalog!" ;
			};
}]);




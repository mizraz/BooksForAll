//user purchases controller
angular.module('myApp').controller("userPurchasesController", 
		['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			ctrl.email = $rootScope.userLogedIn.email;
			$scope.nickname = $rootScope.userLogedIn.userNickname;
		}]);


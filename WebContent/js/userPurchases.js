// user purchases controller
angular.module('myApp').controller("userPurchasesController", 
		['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			// need this instead of adding a field to 'ebook' object, because don't want that every user (that have not bought the ebook will know the url)
//			ctrl.contentUrlDic = {};

			ctrl.email = $rootScope.userLogedIn.email;
			$scope.nickname = $rootScope.userLogedIn.userNickname;

		}]);


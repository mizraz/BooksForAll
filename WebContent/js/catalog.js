angular.module('myApp').controller("catalogController", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			// ctrl.ebook - current ebook selected.
			ctrl.ebook = $rootScope.curEbook;

			this.$onInit = function() {
				console.log("in catalog controller $rootScope.userLogedIn.userNickname " + $rootScope.userLogedIn.userNickname + " explore our catalog!");  
				$scope.userNickname = "Welcom Back, " + $rootScope.userLogedIn.userNickname + " !" + " explore our catalog!" ;


			};


		}]);




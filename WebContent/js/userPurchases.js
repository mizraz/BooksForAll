angular.module('myApp').controller("userPurchasesController", 
		['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			// need this instead of adding a field to 'ebook' object, because don't want that every user (that have not bought the ebook will know the url)
//			ctrl.contentUrlDic = {};
			
			console.log($rootScope.nnn);
			ctrl.email = $rootScope.userLogedIn.email;
//			$rootScope.userPurchases = [];


//		    var init = function () {
//				$http.get("http://localhost:8080/BooksForAll/purchases/email/"+ctrl.email) ///name/Alfreds Futterkiste
//				.then(function(response) {
//					$scope.records = response;
//					$scope.result = $scope.records;//this variable will hold the search results
//					
//					console.log($scope.result);
//					console.log('arr length ' + $scope.result.data.length);
//					for (i = 0; i < $scope.result.data.length; ++i) {
//						var ebookIdDict = "ebook" + $scope.result.data[i].bookId;
//						console.log(ebookIdDict);
//						var curEbook = $rootScope.ebooksDict[ebookIdDict];
//						$rootScope.userPurchases.push(curEbook);
//						console.log('curEbook: ' + curEbook);
//
//						console.log("$scope.result.data[i].bookId: " + $scope.result.data[i].bookId);
//
//					}
//
//				});
//		    }
//		    init();
			

			
//			ctrl.ebookContentUrl = 'gutenberg/contents/';
			
		}]);


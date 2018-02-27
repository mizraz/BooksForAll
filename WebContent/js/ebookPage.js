angular.module('myApp').controller("ebookPageController", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {
			var ctrl = this;
			// ctrl.ebook - current ebook selected.
			ctrl.ebook = $rootScope.curEbook;

			this.$onInit = function() {
				$scope.showReviewForm = false;
				$rootScope.isCurEbookReviewed = false;
				if ($rootScope.purchasesDict["ebook"+ ctrl.ebook.bookId] == undefined) {
					$rootScope.isCurEbookBought = false;
				}
				else {
					$rootScope.isCurEbookBought = true;
				}

				// get reviews of ebookId
				$http.get("http://localhost:8080/BooksForAll/reviews/bookId/"+ctrl.ebook.bookId)
				.then(function(response) {
					$scope.records = response;
					$scope.result = $scope.records;//this variable will hold the search results
					// checks whether this user already reviewed this ebook. if he did, hide review form.
					for (var i = 0; i < $scope.result.data.length; ++i) {
						// email of the ith reviewer
						var email = $scope.result.data[i].email;
						if(email == $rootScope.userLogedIn.email) {
							$rootScope.ebooksDict["ebook" + ctrl.ebook.bookId].isReviewd = 1;
							$rootScope.isCurEbookReviewed = true;
						} 
					}
					$scope.showReviewForm = $rootScope.isCurEbookBought && (!$rootScope.isCurEbookReviewed);
				});
			};
		}]);


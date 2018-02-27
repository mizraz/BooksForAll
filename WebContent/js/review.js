//a component for a review of an ebook.
(function (angular) {
	'use strict';
	function reviewController($scope, $rootScope, $http) {
		var ctrl = this;
		this.$onInit = function() {
			if ( $rootScope.curPage == $rootScope.pagesPaths.reviewsListAll && $rootScope.userPrivel) {
				$scope.showApproveButton = 1;
			}
			else {
				$scope.showApproveButton = 0;
			}
		};
		$scope.approveReview = function () {
			$scope.approved = true; 
			var reviewApproval =
			{
					email: ctrl.review.email,
					bookId: ctrl.review.bookId
			};
			//$http is AngularJS way to do ajax-like communications
			$http.post("http://localhost:8080/BooksForAll/reviewApprove", JSON.stringify(reviewApproval)) 
			.then(function(response) {
				$scope.records = response;
				$scope.result = $scope.records;//this variable will hold the search results
			});
		};
	}
	angular.module('myApp').component('review', {
		templateUrl: 'html/review.html',
		controller: reviewController,
		bindings: {
			review: '='
		}
	});
})(window.angular);
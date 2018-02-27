//a component for the form review. holds the ta, button and handles sending, clikcing showing etc.
(function (angular) {
	'use strict';
	function reviewFormController ($scope, $element, $attrs, $http, $rootScope) {
		var ctrl = this;
		ctrl.editMode = false;

		$scope.showForm = true;
		ctrl.userNickname = $rootScope.userNickname;


		this.$onInit = function() {
		};
		// ctrl.submitReview() - user clicked on submit review. send content to server.
		ctrl.submitReview = function() {
			$scope.showForm = false;
			var review =
			{
					email: $rootScope.userLogedIn.email,
					bookId: ctrl.ebookId,
					description: $scope.reviewDescription,
					isApproved: "0",
					userNickname: $rootScope.userNickname,
					userImageUrl: $rootScope.userImageUrl

			};

			// send ajax post request with the new review.
			$http.post("http://localhost:8080/BooksForAll/newReview", JSON.stringify(review)) 
			.then(function(response) {
//				TODO: message to inform user message submited and will be approved soon
				window.alert("Yor review is submitted and will be approved soon");

			});
		};

		// function uses to make the ta to grow as user writes.
		makeGrowable(document.querySelector('.ta-message-container'));
		function makeGrowable(container) {
			var area = container.querySelector('textarea');
			var clone = container.querySelector('span');
			area.addEventListener('input', function (e) {
				clone.textContent = area.value + '\n';
			});
		}
	};

	angular.module('myApp').component('reviewForm', {
		controller: reviewFormController,
		templateUrl: 'html/reviewForm.html',
		bindings: {
			userr: '=', // TODO: delete. very risky deleting this at this point of time.
			ebookId: '='
		}
	});
})(window.angular);
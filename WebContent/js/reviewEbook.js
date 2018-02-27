
var reviewList;
(function (angular) {
	'use strict';

	function reviewEbookController($scope, $element, $attrs, $http, $rootScope) {
		var ctrl = this;
		ctrl.editMode = false;
		ctrl.clickedOnce = false;
		ctrl.isCollapsed = false;
		$scope.reviewsList = [];

		this.$onInit = function() {
			$rootScope.ebooksDict["ebook" + ctrl.ebookId].isReviewd = 0;
			$rootScope.isCurEbookReviewed = 0;
			ctrl.clickedOnce = true;
			$http.get("http://localhost:8080/BooksForAll/reviews/bookId/"+ctrl.ebookId) ///name/Alfreds Futterkiste
			.then(function(response) {
				$scope.records = response;
				$scope.result = $scope.records;//this variable will hold the search results
				console.log($scope.result);
				console.log('arr length ' + $scope.result.data.length);
				for (var i = 0; i < $scope.result.data.length; ++i) {

					$scope.reviewsList.push($scope.result.data[i]);
					
					console.log("$scope.result.data[i].bookId: " + $scope.result.data[i].bookId);

					var profilePicSrc = $scope.result.data[i].userImageUrl;
					var name = $scope.result.data[i].userNickname;
					var email = $scope.result.data[i].email;
					var msgText = $scope.result.data[i].description;
					var date = '';
					var msgIdNumberDel = '';
					var msgId = '';
					var dateTime = '';
					var newMessage = '';

					if(email == $rootScope.userLogedIn.email) {
						$rootScope.ebooksDict["ebook" + ctrl.ebookId].isReviewd = 1;
						$rootScope.isCurEbookReviewed = 1;
					} 

//					console.log(newMessage);
				}

			});

		};



		ctrl.getAllReviewsOfThisEbook = function(){
			ctrl.isCollapsed = !ctrl.isCollapsed;
			if($scope.reviewsList.length == 0 ) {
				
				window.alert("The review list is empty!");
				
			}
		}

	}

	angular.module('myApp').component('reviewEbook', {
		controller: reviewEbookController,
		templateUrl: 'html/reviewEbook.html',
		bindings: {
			userName: '=',
			ebookId: '='
		}
	});


})(window.angular);


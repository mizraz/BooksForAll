var reviewList;
// controller for all un approved reviews.
window.onload = function () {
};

angular.module('myApp').controller("reviewsListAllController", 
		['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {
			var ctrl = this;

			$scope.reviewsList = []; // a list of all pending approval reviews

			// the area where the reviews appear.
			reviewList = document.getElementById('chati'); // TODO: update 'chati' to reviewsList something....

			// get all all reviews not approved yet.
			$http.get("http://localhost:8080/BooksForAll/allReviewsNotApproved") 
			.then(function(response) {
				$scope.records = response;
				$scope.result = $scope.records;//this variable will hold the search results

				console.log($scope.result);



				console.log('arr length ' + $scope.result.data.length);
				for (var review in $scope.result.data) {

					console.log("$scope.result.data[review].bookId: " + $scope.result.data[review].bookId);

					var profilePicSrc = $scope.result.data[review].userImageUrl;
					var nickname = $scope.result.data[review].userNickname;
					var email = $scope.result.data[review].email;
					var msgText = $scope.result.data[review].description;
					var date = '';
					var msgIdNumberDel = '';
					var bookId = $scope.result.data[review].bookId;
					var dateTime = '';
					var newMessage = '';

					$scope.reviewsList.push($scope.result.data[review]);

				}

			});
		}]);


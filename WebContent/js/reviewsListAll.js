var reviewList;
window.onload = function () {
};

angular.module('myApp').controller("reviewsListAllController", 
		['$scope','$rootScope', '$http', function($scope, $rootScope, $http) {
			var ctrl = this;

			ctrl.reviewsPendingList = {}; // a list of all pending approval reviews

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
					drawReview(email, nickname, profilePicSrc, msgText, msgIdNumberDel, bookId, dateTime, newMessage, date);
				}

			});



			// drawReview() - create and draws the review.
			drawReview = function(email, nickname, profilePicSrc, msgText, msgIdNumberDel, msgId, dateTime, newMessage, date) {
				newMessage = document.createElement('li');
				newMessage.classList.add('message');
				newMessage.setAttribute("id", msgId);
				var msgIdNumber = (parseInt(msgId.replace(/[^0-9]*/, '')) + 1) * 2;
				newMessage.setAttribute("tabindex", msgIdNumber);
//				console.log("222222" + reviewList.innerHTML);
				newMessage.innerHTML = " \
					<div class='sidebar-message'> \
					<img alt='' class='img-circle' src=" + profilePicSrc + "> \
					</div> \n <div class='message-body'> \
					<header class='message-header'> \
					<cite class='message-user-name'>" + nickname + "</cite> \
					<time datetime=" + date + " class='message-time-sent'>" + dateTime + "</time> \
					<div hidden class='email' id='review-email'>" + email + "</div> \
					<div hidden class='email' id='review-book-id'>" + msgId + "</div> \
					<div class='container-button-delete-message'> \
					<button tabindex=" + msgIdNumberDel + " aria-label='Delete message id: " + msgId + "' class='button-delete-message' id='approve-review-btn-" + email + "" + msgId + "'> \
					approve \
					</button> \
					<div class='empty-div-del-btn'> </div> \
					</div> \
					<div class='container-button-delete-message'> \
					<button tabindex=" + msgIdNumberDel + " aria-label='Delete message id: " + msgId + "' class='button-delete-message' id='del-msg-btn-" + msgId + "'> \
					x \
					</button> \
					<div class='empty-div-del-btn'> </div> \
					</div> \
					</header> \
					<article class='message-content'> \
					" + msgText + " \
					</article> \
					</div> \
					<!-- end message -->  \
					";
//				console.log(newMessage);
				reviewList.appendChild(newMessage);


				// this function is used to give scope to id. needed in order to make eventListener on button.
				(function () {
					var localMsgId = email;
					var localBookId = msgId;
					console.log("77777" + localMsgId + localBookId);
					(document.getElementById('approve-review-btn-' + localMsgId+localBookId)).addEventListener('click', function () {
						console.log("!21312213 " + localMsgId);

						var curMsgEmail = email;
						var curBookId = localBookId;

						if (true) {
							console.log("approve clicked email: " + curMsgEmail + " bookId: " + curBookId);


							var reviewApproval =
							{
									email: curMsgEmail,
									bookId: curBookId
							};

							//$http is AngularJS way to do ajax-like communications
							$http.post("http://localhost:8080/BooksForAll/reviewApprove", JSON.stringify(reviewApproval)) 
							.then(function(response) {
								$scope.records = response;
								$scope.result = $scope.records;//this variable will hold the search results
							});



						} else {
							console.log("YOU TRIED DEL MESSAGE NOT YOURS! your email vs message email: " + msgId + " " + Babble.email + " " + email);
						}
					});
				}());

			};
		}]);


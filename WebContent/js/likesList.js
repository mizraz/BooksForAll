(function (angular) {
	'use strict';

	function likesListController($scope, $element, $attrs, $http, $rootScope) {
		var ctrl = this;
		ctrl.usersListHtml = ""; // TODO: delete



		this.$onInit = function () {
			ctrl.userPrivel = $rootScope.userPrivel;
			console.log("likes list this.$oninit ");
//			send ajax to get all likes to ctrl.ebookId
			$http.get("http://localhost:8080/BooksForAll/likes/bookId/"+ctrl.ebookId)
			.then(function(response) {
				$scope.records = response;
				$scope.result = $scope.records;//this variable will hold the search results

				console.log($scope.result);
				console.log('number of likers: ' + $scope.result.data.length);


				ctrl.countLikers = $scope.result.data.length;
				// foreach like of ctrl.ebookId, store it in ctrl.userNamesList.
				for (var liker in $scope.result.data) {
					ctrl.userNamesList.push($scope.result.data[liker]);
					ctrl.usersListHtml = ctrl.usersListHtml + '\n' +  $scope.result.data[liker].userNickname; //TODO: delete
				}

				$rootScope.ebooksDict["ebook"+ctrl.ebookId].likesList = $scope.result.data;


//				console.log("likers: " + ctrl.usersListHtml);

			});  

		};


		ctrl.clickedAName = function (userSelectedByAdmin){

			console.log("in: ctrl.goToUserDetailsPage, user: " + userSelectedByAdmin.userName);
			$rootScope.curUserAdminSelected = userSelectedByAdmin;
			$rootScope.curPage = $rootScope.pagesPaths.userDetailsPageForAdmin;
		}

		ctrl.likeListClickedOnce = false;



		ctrl.editMode = false;
		ctrl.userNamesList = [];

		ctrl.openModalDialog = function() {
			console.log("clicked;");
		};


		ctrl.getLikesList = function() {
			$("#myModalLikes").modal();
		};


	}

	angular.module('myApp').component('likesList', {
		controller: likesListController,
		templateUrl: 'html/likesList.html',
		bindings: {
//			usersList: '=',
			ebookId: '=',
			isUserliked: '=',
			isUserPurchased: '=',
			usersListString: '='
		}


	});



})(window.angular);
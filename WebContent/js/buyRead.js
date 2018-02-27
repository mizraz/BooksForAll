var myModalReadBody;

(function(angular) {
	'use strict';

	var myModalReadBody = document.getElementById('myModalReadBody');

	function buyReadController($rootScope, $http, $scope) {
		var ctrl = this;
		$scope.indexOfUserInLikesList = -1;

		this.$onInit = function() {
			ctrl = this;

			$rootScope.curEbookIdd = '123'; // TODO: delete

			// ctrl.curEbook - current ebooked is being explored by user.
			ctrl.curEbook = ctrl.ebook;
			ctrl.userEmail = $rootScope.userLogedIn.email;


			if ($rootScope.purchasesDict["ebook"+ ctrl.curEbook.bookId] == undefined) {
				ctrl.usrBoughtCurBook = false;
				ctrl.isLiked = false;
			} else
			{
				ctrl.usrBoughtCurBook = true;
				console.log("$rootScope.purchasesDict[ebook+ ctrl.curEbook.bookId].isLiked" + $rootScope.purchasesDict["ebook"+ ctrl.curEbook.bookId].isLiked);

				// check if user liked this book. if liked, to show unlike button. otherwise Like button.
				//TODO: think to hide Like if user didn't bought ebook.
				if ($rootScope.ebooksDict["ebook"+ ctrl.curEbook.bookId].isLiked == '1' ||
						$rootScope.ebooksDict["ebook"+ ctrl.curEbook.bookId].isLiked == 1) 
				{
					ctrl.isLiked = true;
				}
				else {
					ctrl.isLiked = false;
				}

			}			



			console.log("ctrl.usrBoughtCurBook: " + ctrl.usrBoughtCurBook);		
			console.log("ctrl.isLiked: " + ctrl.isLiked);



			ctrl.userPrivel = $rootScope.userPrivel;
			console.log("likes list this.$oninit ");
//			send ajax to get all likes to ctrl.ebookId
			$http.get("http://localhost:8080/BooksForAll/likes/bookId/"+ctrl.ebook.bookId)
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
					if($scope.result.data[liker].userNickname == $rootScope.userLogedIn.userNickname)
					{
						$scope.indexOfUserInLikesList = liker;
					}

				}

				$rootScope.ebooksDict["ebook"+ctrl.ebook.bookId].likesList = $scope.result.data;



//				console.log("likers: " + ctrl.usersListHtml);

			});  



			ctrl.clickedAName = function (userSelectedByAdmin){

				console.log("in: ctrl.goToUserDetailsPage, user: " + userSelectedByAdmin.userNickname);
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










		};

		// ctrl.clickedBuy()  - user clicked by. to route to paymentForm page
		ctrl.clickedBuy = function () {

			console.log('clicked buy book: ' + ctrl.ebook.bookId);

			$rootScope.curEbookUserClickedToBuy = ctrl.ebook;
			$rootScope.curPage =  $rootScope.pagesPaths.payPage;

		}


//		ctrl.clickedRead() - user cliked on Read button. open a modal, ask for going to last scoll position.
		ctrl.clickedRead = function clickedRead() {

			console.log("clicked Read. ctrl.ebook.bookId " + ctrl.ebook.bookId);

//			$rootScope.curEbookIdd - current book Id TODO: try to delete this and replace every place with:  ctrl.ebook.bookId 
			$rootScope.curEbookIdd = ctrl.ebook.bookId;


			//TODO: send ajax to update current scroll!!!!!!!!!!!!!!!!!!!!!!!

//			send ajax to get current scroll position.
			$http.get("http://localhost:8080/BooksForAll/scroll/email/"+$rootScope.userLogedIn.email+"/bookId/"+ctrl.ebook.bookId)
			.then(function(response) {
				$scope.records = response;
				$scope.result = $scope.records;//this variable will hold the search results

				console.log($scope.result);
				console.log('scroll arr size: ' + $scope.result.data.length);

				// update currentScroll - update the position the user stopped reading
				$rootScope.ebooksDict["ebook" +ctrl.ebook.bookId].currentScroll = $scope.result.data[0].currentScroll;


				console.log("current scroll of bookId: " +ctrl.ebook.bookId + " is: " + $rootScope.ebooksDict["ebook" +ctrl.ebook.bookId].currentScroll);


				console.log("$rootScope.curEbookIdd " + $rootScope.curEbookIdd);

				var ebookIdDict = "ebook" + $rootScope.curEbookIdd;

				window.curBookIdToSendScroll = $rootScope.curEbookIdd;
				window.curEmailToSendScroll = $rootScope.userLogedIn.email;
				// this conditions mean: user never read this book / is on top of it.
				if (ctrl.curReadEbookScroll = $rootScope.ebooksDict[ebookIdDict].currentScroll == 0) {
					$rootScope.lastPage =  $rootScope.curPage;
					$rootScope.curPage = $rootScope.pagesPaths.ebookContents + $rootScope.curEbookIdd + '.html';
				}
				else { //open modal to ask user if to go to last scroll.
					window.curBookIdToSendScroll = $rootScope.curEbookIdd;
					console.log("ctrl.ebook.bookId " + ctrl.ebook.bookId);
					console.log("window.curBookIdToSendScroll " + window.curBookIdToSendScroll);

					window.curEmailToSendScroll = $rootScope.userLogedIn.email;


					$("#myModalScroll").modal();				
				}


			}); 

		};	


		// ctrl.goToLastScroll() - user cliked: yes / no on the ScrollModal. 
		// if cliked yes - send him to the place he stopped reading. otherwise, to the top of ebook.
		ctrl.goToLastScroll = function (isToGoToLastScroll) {


			// hide the ModalScroll.
			$('#myModalScroll').modal('hide');
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();


			// bookIdClickedToRead - current ebook id user clicked to read
			var bookIdClickedToRead = $rootScope.curEbookIdd.bookId;



			console.log("ctrl.ebook.bookId : " + ctrl.ebook.bookId);
			console.log("$rootScope.curEbookIdd : " + $rootScope.curEbookIdd);



			// ebookIdDict - the key of the ebook in ebooksDict
			var ebookIdDict = "ebook" + $rootScope.curEbookIdd;

			ctrl.curReadEbookScroll = $rootScope.ebooksDict[ebookIdDict].currentScroll;

			// curScroll - user's last scroll.
			var curScroll = ctrl.curReadEbookScroll;
			// route to the ebook contents page
			$rootScope.curPage = $rootScope.pagesPaths.ebookContents + $rootScope.curEbookIdd + '.html';

			console.log("ebookIdDict: " + ebookIdDict);
			console.log("ctrl.curReadEbookScroll: " + ctrl.curReadEbookScroll);
			console.log("ctrl.curReadEbookPath: " + ctrl.curReadEbookPath);



			if (isToGoToLastScroll) {

				console.log("scroll : " + curScroll);


				// needs to wait until navigted to the ebook contents page.
				setTimeout(function() {
					// bodyId - the id of the scrollable element contains ebook content
					var bodyId = document.getElementById('bookContent');
					body.scrollTop = curScroll;


					console.log("body.scrollTop : " + body.scrollTop);
					console.log("yes");					

				}, 1000);

			}
			else {
				console.log("no");	
			}

			// remove Scroll modal
			$('body').removeClass('modal-open');
			$('.modal-backdrop').remove();
		};


		// ctrl.submitLike() user cliked the like / unlike button. flip the value, and send ajax to update server.
		ctrl.submitLike = function() {

			console.log("clicked like;");

			if(ctrl.ebook.isLiked == 1) {
				ctrl.isLiked = false;
				ctrl.ebook.isLiked = 0;
				ctrl.countLikers -= 1;
				ctrl.userNamesList.splice($scope.indexOfUserInLikesList, 1);
				$rootScope.ebooksDict["ebook" + ctrl.ebook.bookId].isLiked = 0;

			} else {
				ctrl.isLiked = true;
				ctrl.ebook.isLiked = 1
				ctrl.countLikers += 1;
				ctrl.userNamesList.push($rootScope.userLogedIn);
				$scope.indexOfUserInLikesList = ctrl.userNamesList.length - 1;
				$rootScope.ebooksDict["ebook" + ctrl.ebook.bookId].isLiked = 1;
			}


			console.log(ctrl.ebook.isLiked);

			var like =
			{
					email: $rootScope.userLogedIn.email,
					bookId: ctrl.ebook.bookId,
					userNickname: $rootScope.userLogedIn.userNickname,
					isLiked: ctrl.ebook.isLiked
			};

			console.log(like.isLiked);

			// 	send an ajax requset to update server a new like / unlike if generated by user.
			$http.post("http://localhost:8080/BooksForAll/newLike", JSON.stringify(like)) 
			.then(function(response) {

				//TODO: DO something to inform user like submited?
				$scope.records = response;
				$scope.result = $scope.records;
			});
		};

	};


	angular.module('myApp').component('buyRead', {
		controller: buyReadController,
		templateUrl: 'html/buyRead.html',
		bindings: {
			ebook: '='
		}
	});



})(window.angular);





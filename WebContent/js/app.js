(function(angular) {
	'use strict';  
	var app = angular.module('myApp', [/*'ngRoute'*/]);  
	app.run(function($rootScope, $http) {
		$rootScope.color = 'blue';
	});


	app.controller( 'MainCtrl', function MainCtrl($scope, $rootScope, $http) {
		this.$onInit = function () {		
			var ctrl = this;			
		
		// $rootScope.pagesPaths - consts, for routing between pages.
		$rootScope.pagesPaths = {
				ebookPage: 'html/ebookPage.html',
				catalog: 'html/catalog.html',
				contactUs: 'html/contactUs.html',
				lastTransactions: 'html/lastTransactions.html',
				payPage: 'html/paymentForm.html',
				profile: 'html/profile.html',
				register: 'html/register.html',
				reviewsListAll: 'html/reviewsListAll.html',
				topDeals: 'html/topDeals.html',
				userDetails: 'html/userDetails.html',
				userPurchases: 'html/userPurchases.html',
				usersList: 'html/usersList.html',
				login: 'login.html',
				ebookContents: 'resources/gutenberg/contents/', //needs to concat a suffix with the ebookId
				userDetailsPageForAdmin: 'html/userDetailsForAdmin.html'	
		}
		
		$rootScope.curEbookUserClickedToBuy = '';
		
		


		
		// $rootScope.ebooksDict - object as a dictionary holds all ebooks the store offers.
		$rootScope.ebooksDict = {};
		// $rootScope.ebooksList - a list holds all the books can offer.
		$rootScope.ebooksList = [];
		// $rootScope.curPage - holds the current page user in. starts in main page: the catalog.
		$rootScope.curPage = $rootScope.pagesPaths.catalog;
		// $rootScope.lastPage - holds the last page user in. used just for reading ebooks. starts in main page: the catalog.
		$rootScope.lastPage = $rootScope.pagesPaths.catalog;
		console.log("$rootScope.pagesPaths.catalog: " + $rootScope.pagesPaths.catalog);
		console.log("$rootScope.curPage: " + $rootScope.curPage);
		 

		//$rootScope.userLogedIn -  details of user logged in
		$rootScope.userLogedIn = {
				email: '',
				userNickname: '',
				userName: '',
				userImageUrl: '',
				phoneNumber: '',
				description: '',
				address: ''
		};

		$("#myModalRegisterLogin").modal();

		this.userKind = 'admin'; 
		this.userPrivel = 1;
		this.userId = 1; //TODO: delete if didnt break anything
		this.userName = "raz"; //TODO: delete if didnt break anything
		$rootScope.curEbook = '1'; // to delete
		$rootScope.usrBoughtCurBook = true; // to delete
		//$rootScope.purchasesList = []; to delete


		// $rootScope.modalCurPath - holds the curren modal user in. uses just for the login / register modal.
		$rootScope.modalCurPath = $rootScope.pagesPaths.login;
		// $rootScope.purchasesDict - a dictionary holds user purchases
		$rootScope.purchasesDict = {};
		// $rootScope.userPurchases - a list holds user purchases
		$rootScope.userPurchases = [];				

		}


		this.isShowEbook = false; //TODO: need this?
		this.isShowCatalog = true; //TODO: need this?





	});
})(window.angular);




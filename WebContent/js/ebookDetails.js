(function (angular) {
	'use strict';

	function ebookDetailsController($scope, $rootScope) {


		var ctrl = this;
		console.log("ebookDetailsController");
		this.$onInit = function() {
			
			
			
			console.log("ebookDetailsController onINIT");

			
			
			ctrl.isCatalogPage = false;

			if($rootScope.curPage == $rootScope.pagesPaths.catalog || $rootScope.curPage == $rootScope.pagesPaths.topDeals) {
				ctrl.isCatalogPage = true;
			}

			ctrl.goToEbookPage = function(){
				
				$rootScope.curEbook = ctrl.ebook;
				$rootScope.isLikeddd = true;
				
				
				console.log("in gotoEbookPage()");
				console.log("ctrl.ebook.bookId: " + ctrl.ebook.bookId);

//				if($rootScope.curPage != $rootScope.pagesPaths.userPurchases) {

					console.log("ctrl.curPage " + ctrl.curPage);
					ctrl.curPage = $rootScope.pagesPaths.ebookPage; // TODO: delete and check if still works
					
					console.log("ctrl.curPage " + ctrl.curPage);
					
					$rootScope.curPage = $rootScope.pagesPaths.ebookPage;
//				}

			};    	

		};


	}


	angular.module('myApp').component('ebookDetails', {
		templateUrl: 'html/ebookDetails.html',
		controller: ebookDetailsController,
		bindings: {
			ebook: '='

		}
	});
})(window.angular);
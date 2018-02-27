// a component holds ebook details. since it is used in serveral places, it was deifned as a component.
(function (angular) {
	'use strict';
	function ebookDetailsController($scope, $rootScope) {
		var ctrl = this;
		this.$onInit = function() {
			ctrl.isCatalogPage = false;
			if($rootScope.curPage == $rootScope.pagesPaths.catalog || $rootScope.curPage == $rootScope.pagesPaths.topDeals) {
				ctrl.isCatalogPage = true;
			}

			ctrl.goToEbookPage = function(){
				$rootScope.curEbook = ctrl.ebook;
				$rootScope.isLikeddd = true;
				ctrl.curPage = $rootScope.pagesPaths.ebookPage; // TODO: delete and check if still works
				$rootScope.curPage = $rootScope.pagesPaths.ebookPage;
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
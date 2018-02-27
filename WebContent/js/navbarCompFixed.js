//a component for the fixed to top navbar used in reading ebooks
(function(angular) {
	'use strict';
	function navbarFixedController($scope, $element, $attrs, $rootScope) {
		var ctrl = this;

		ctrl.routeToPage = function(pageToRouteTo){
			$scope.curPage = 'topDeals/topDeals.html';
			$rootScope.curPage = pageToRouteTo;
			ctrl.curPage = pageToRouteTo;
		};

		$scope.backToLastPageClicked = function () {
			var tmpPage = $rootScope.lastPage;
			$rootScope.lastPage =  $rootScope.curPage;
			$rootScope.curPage = tmpPage;
		};
	};

	angular.module('myApp').component('navbarCompFixed', {

		templateUrl: 'html/navbarCompFixed.html',
		controller: navbarFixedController,
		bindings: {
			userId: '=',
			userPrivel: '=',
			isShowCatalog: '=',
			isShowEbook: '=',
			curPage: '='
		}
	});

})(window.angular);


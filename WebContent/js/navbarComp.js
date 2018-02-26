(function(angular) {
  'use strict';


function navbarController($scope, $element, $attrs, $rootScope) {
var ctrl = this;



	this.$onInit = function () {
		
		$scope.userNickname = '123';//$rootScope.userLogedIn.userNickname;
		console.log("on init navbar nickname: " + $scope.userNickname);
	}

	$scope.userNickname = $rootScope.userLogedIn.userNickname;


ctrl.routeToPage = function(pageToRouteTo){
  $scope.curPage = 'topDeals/topDeals.html';
  $rootScope.curPage = pageToRouteTo;
  ctrl.curPage = pageToRouteTo;
  
};



};


angular.module('myApp').component('navbarComp', {
	
  templateUrl: 'html/navbarComp.html',
  controller: navbarController,
  bindings: {
    userId: '=',
    userPrivel: '=',
    isShowCatalog: '=',
    isShowEbook: '=',
    curPage: '='

  }


});





})(window.angular);

(function(angular) {
  'use strict';


function navbarController($scope, $element, $attrs, $rootScope) {
var ctrl = this;

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

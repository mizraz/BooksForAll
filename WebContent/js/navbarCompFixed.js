(function(angular) {
  'use strict';


function navbarFixedController($scope, $element, $attrs, $rootScope) {
var ctrl = this;

ctrl.routeToPage = function(pageToRouteTo){
	  $scope.curPage = 'topDeals/topDeals.html';
	  $rootScope.curPage = pageToRouteTo;
	  ctrl.curPage = pageToRouteTo;
	  
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

  }//,
  // closeEbookPage: function () {
  //   console.log("hii");
    
  
  //   ctrl.isShowCatalog = true;
    
  //   ctrl.isShowEbook = false;
  //   console.log(" this.isShowEbook " + $scope.isShowEbook);
  
  // }



});





})(window.angular);


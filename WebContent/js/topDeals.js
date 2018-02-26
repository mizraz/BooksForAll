angular.module('myApp').controller("topDealsController", 
['$scope', function($scope, $rootScope) {
    var ctrl = this;

ctrl.fun = function() {
    console.log(23222);
    console.log($rootScope.color);  
    console.log($rootScope.curPage);  
}; 





}]);









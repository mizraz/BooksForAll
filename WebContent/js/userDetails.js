(function (angular) {
	'use strict';

	function userDetailsController($scope, $element, $attrs, $rootScope, $http) {

		var ctrl = this;


		this.$onInit = function () {
			console.log("in userDetails " + ctrl.user);


			$('#confirmDeleteUserModal').on('show.bs.modal', function(e) {

				var bookId = $(e.relatedTarget).data('book-id');
				console.log("bookId: ++ " + bookId);
				console.log("window.curUserToDelete in before modal show: " + window.curUserToDelete.email);
			}); 
		};



	}


	angular.module('myApp').component('userDetails', {
		templateUrl: 'html/userDetails.html',
		controller: userDetailsController,
		bindings: {
			user: '='

		}
	});
})(window.angular);
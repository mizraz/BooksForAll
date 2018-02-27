// a component holding the user details
(function (angular) {
	'use strict';
	function userDetailsController($scope, $element, $attrs, $rootScope, $http) {
		var ctrl = this;
		this.$onInit = function () {
			$('#confirmDeleteUserModal').on('show.bs.modal', function(e) {
				var bookId = $(e.relatedTarget).data('book-id');
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
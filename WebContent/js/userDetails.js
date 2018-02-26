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


		ctrl.confirmDeleteUser = function() {
			console.log("in confirmDeleteUser");
			window.curUserToDelete = ctrl.user;
			console.log("window.curUserToDelete.email: " + window.curUserToDelete.email);
			$("#confirmDeleteUserModal").modal();

		};


		ctrl.deleteUser = function(isToDeleteUser) {
			console.log("window.curUserToDelete.email in deleteUser: " + window.curUserToDelete.email);

			console.log("ctrl.user.email: " + ctrl.user.email);

			if(!window.curUserToDelete.email.includes('admin')) {

				if (isToDeleteUser) {
					var userToDelete =
					{
							email: window.curUserToDelete.email
					};

					$http.post("http://localhost:8080/BooksForAll/deleteUser", JSON.stringify(userToDelete)) 
					.then(function(response) {

						console.log("delete request sent!");
					});    			
				}
			}
			$("#confirmDeleteUserModal").modal('hide');
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
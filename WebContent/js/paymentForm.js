angular.module('myApp').controller("CtrlBuy", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {


			this.$onInit = function () {
				$scope.curEbookIdUserClickedToBuy = $rootScope.curEbookUserClickedToBuy.bookId;
				$scope.curEbookUserClickedToBuyTitle = $rootScope.curEbookUserClickedToBuy.title;

				console.log("$scope.curEbookIdUserClickedToBuy id: " + $scope.curEbookIdUserClickedToBuy);

			}


		
			$scope.errormsg1="";
			$scope.errormsg2="";
			$scope.errormsg3="";
			$scope.errormsg4="";
			$scope.errormsg5="";
			$scope.errormsg6="";
			$('#alert1').hide();
			$('#alert2').hide();
			$('#alert3').hide();
			$('#alert4').hide();
			$('#alert5').hide();
			$('#alert6').hide();
			$scope.BuyNow = function()
			{
				var checkAll=1;
				var letters = /^[A-Z a-z]+$/;//for parameters that have to match only letters
				var numbers = /[0-9]/;//for parameters that have to match only numbers
				var today = new Date();
				var usersInputDate = new Date($scope.date);
				$('#alert1').hide();
				$('#alert2').hide();
				$('#alert3').hide();
				$('#alert4').hide();
				$('#alert5').hide();
				$('#alert6').hide();
				
				if(!($scope.name.match(letters)))
				{
					checkAll=0;
					$scope.errormsg1="Use only letters";
					$('#alert1').show();

				}
				if(!($scope.Lname.match(letters)))
				{
					checkAll=0;
					$scope.errormsg2="Use only letters";
					$('#alert2').show();

				}
				if(($scope.myCard == 'visa') && (($scope.ccn.length<16 ||$scope.ccn.length>16) || $scope.ccn.indexOf("4")!=0 ) || (!($scope.ccn.match(numbers))))
				{
					checkAll=0;
					$scope.errormsg3="The valid number of digits of VISA card is 16 and it starts with 4";
					$('#alert3').show();
				}
				if(($scope.myCard == 'isra') && ($scope.ccn.length>9 || $scope.ccn.length<9) || (!($scope.ccn.match(numbers))))
				{
					checkAll=0;
					$scope.errormsg4="The valid number of digits of ISRACARD card is 9";
					$('#alert4').show();
				}
				if((!($scope.myCard == 'visa'))&&(!($scope.myCard == 'isra')))
					{
					checkAll=0;
					$scope.errormsg4="You must choose credit card";
					$('#alert4').show();
					}
					
				if(($scope.validityMonth <= today.getMonth()+1) && ($scope.validityYear == today.getFullYear()) || ($scope.validityYear < today.getFullYear()))
				{
					checkAll=0;

					$scope.errormsg5="Your credit card seems to be invalid";
					$('#alert5').show();
				}
				if((($scope.cvv.match(numbers))&&($scope.cvv.length <3)) || (!($scope.cvv.match(numbers))))
				{
					checkAll=0;

					$scope.errormsg6="CVV contains 3 digits";
					$('#alert6').show();
				}
				if(checkAll==1)
				{
					// prepare purchase objcect to send to server
					var purchase =
					{
							email: $rootScope.userLogedIn.email,
							bookId: $rootScope.curEbookUserClickedToBuy.bookId,
							price: $rootScope.curEbookUserClickedToBuy.price,

					};

					console.log("in pay with good inputs: email: " + $rootScope.userLogedIn.email +
							" bookId: " + $rootScope.curEbookUserClickedToBuy.bookId +
							" price: " + $rootScope.userLogedIn.price);

					// send ajax post request with the new review.
					$http.post("http://localhost:8080/BooksForAll/newPurchase", JSON.stringify(purchase)) 
					.then(function(response) {
//						TODO: message to inform user message submited and will be approved soon

						$rootScope.ebooksDict["ebook"+$rootScope.curEbookUserClickedToBuy.bookId].isPurchased = 1;
						$rootScope.userPurchases.push($rootScope.ebooksDict["ebook"+$rootScope.curEbookUserClickedToBuy.bookId]);
						$rootScope.purchasesDict["ebook"+$rootScope.curEbookUserClickedToBuy.bookId] = 
							$rootScope.ebooksDict["ebook"+$rootScope.curEbookUserClickedToBuy.bookId];

		    				
		    				//TODO: ksenia alert temporary with message: "bought succesfuly!"
		    				
							$rootScope.curPage = $rootScope.pagesPaths.userPurchases;	
						
						


					});
				}


			}//

		}]);

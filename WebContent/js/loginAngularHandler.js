angular.module('myApp').controller("Ctrlogin", 
		['$scope','$rootScope','$http', function($scope, $rootScope, $http) {	

			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;		
			var ctrl = this;
			$scope.error="Error ";		

			$scope.errormsgg1="";
			$('#alert1').hide();


			$scope.clickedRegister = function()
			{
				$rootScope.modalCurPath = $rootScope.pagesPaths.register;
				console.log("clickedRegister");
			}

			$scope.SubmitFunction = function()
			{
				$('#alert1').hide();
				if($scope.nick.length>2 && $scope.pwd.length>3)
				{
					var data =
					{
							userNickname: $scope.nick,
							pwd: $scope.pwd

					};

					$http.post('http://localhost:8080/BooksForAll/loginServlet', JSON.stringify(data))
					.then(function(response) {

						//TODO: $rootScope.eamil = email_of_user_just_loged_in_from_the_response; 
//						$rootScope.userImageUrl , userName etc... 
						$('.alert').hide();


						var res = new String(response.data);


						if(res.indexOf("Failure")==0)
						{
							console.log("HELLO");
							$scope.error="Info ";
							$scope.errormsg1="The username and password you entered did not match our records. Please double-check and try again.";
							$('#alert1').show();
						}//if failure
						else
						{

							if($scope.nick == 'admin') {
								$rootScope.userPrivel = 1;
							} else {
								$rootScope.userPrivel = 0;
							}


							// update the user's details
							$rootScope.userLogedIn = {
									email: response.data[0].email,
									userNickname: response.data[0].userNickname,
									userName: response.data[0].userName,
									userImageUrl: response.data[0].userImageUrl,
									phoneNumber: response.data[0].phoneNumber,
									description: response.data[0].description,
									country:response.data[0].address.split(",")[0],
		    						city:response.data[0].address.split(",")[1],
		    						street:response.data[0].address.split(",")[2],
		    						hnumb:response.data[0].address.split(",")[3],
		    						zip:response.data[0].address.split(",")[4]
							};


							ctrl.getEbooksAndPurchases();





//							$rootScope.nick=response.data[0].userNickname;
//							console.log(response.data[0].userNickname);
//							localStorage.setItem('nick', response.data[0].userNickname);
//							$rootScope.email=response.data[0].email;
//							localStorage.setItem('email', response.data[0].emai);
//							$rootScope.address=response.data[0].address;
//							localStorage.setItem('address', response.data[0].address);
//							$rootScope.name=response.data[0].userName;
//							localStorage.setItem('userName', response.data[0].userName);
//							$rootScope.password=response.data[0].pwd;
//							localStorage.setItem('pwd', response.data[0].pwd);
//							$rootScope.description=response.data[0].description;
//							localStorage.setItem('description', response.data[0].description);
//							$rootScope.phoneNumber=response.data[0].phoneNumber;
//							localStorage.setItem('phoneNumber', response.data[0].phoneNumber);
//							$rootScope.photo=response.data[0].userImageUrl;
//							localStorage.setItem('userImageUrl', response.data[0].userImageUrl);
//							console.log($rootScope.nick);
							// $Ctrlogin.curPage='index.html';
//							window.location = 'http://localhost:8080/BooksForAll/KREbooks/index.html';





							$("#myModalRegisterLogin").modal('hide');

						} 

					});
				}
				else
				{
					$scope.error="Info ";
					$scope.errormsg="The Username or Password are too short";
					$('.alert').show();
				}
			};

			// ctrl.getEbooksAndPurchases() - sends request for: all ebooks, all ebooks were bought by user. updates relevant DS.
			ctrl.getEbooksAndPurchases = function() {

				console.log("ctrl.getEbooksAndPurchases");

				// get all the ebooks the store offers
				$http.get("http://localhost:8080/BooksForAll/ebooks")
				.then(function(response) {
					$rootScope.records = response;
					$rootScope.result = $rootScope.records;

					for (var ebook in $rootScope.result.data) {
						// default values are 0: user have not bought, like, scrolled this ebook. will be updated wheter he bought it.
						$rootScope.result.data[ebook]["isPurchased"] = 0; 
						$rootScope.result.data[ebook]["isLiked"] = 0; 
						$rootScope.result.data[ebook]["currentScroll"] = 0;
						//push to a list each ebook
						$rootScope.ebooksList.push($rootScope.result.data[ebook]);
						//update a global dictionary hols all the ebooks for random access
						$rootScope.ebooksDict["ebook" + $rootScope.result.data[ebook].bookId] = $rootScope.result.data[ebook];
					}

					// 	get all purchases of the user logged in, by user's email.
					$http.get("http://localhost:8080/BooksForAll/purchases/email/"+$rootScope.userLogedIn.email) 
					.then(function(response) {
						$rootScope.records = response;
						// $rootScope.resultPurchasesTmp - a temp list holding the purchases of user.
						$rootScope.resultPurchasesTmp = $rootScope.records;

						console.log($rootScope.resultPurchasesTmp);
						console.log($rootScope.userLogedIn.email);
						console.log('arr length ' + $rootScope.resultPurchasesTmp.data.length);

						// resultPurchasesTmp - for each purchased ebook, find it in the purchasesDict, and update params. 
						for (purchase in $rootScope.resultPurchasesTmp.data) {
							// ebookIdDict - a var holds the key of the current ebook in ebooksDictionary
							var ebookIdDict = "ebook" + $rootScope.resultPurchasesTmp.data[purchase].bookId;


							var curEbook = $rootScope.ebooksDict[ebookIdDict];
							$rootScope.userPurchases.push(curEbook);
							$rootScope.purchasesDict["ebook"+ $rootScope.records.data[purchase].bookId/*TODO: change it to ebookIdDict*/] = $rootScope.records.data[purchase]; 


							
							
							
							// if the user purchased some ebooks, needs to update in the DS - ebooksDict.
							if ($rootScope.resultPurchasesTmp.data.length > 0) {
							
							console.log("pur.bookId: "+ $rootScope.resultPurchasesTmp.data[purchase].bookId);
							// update if the isPurchased flag - 1 if bought this book, otherwise 0.
							$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[purchase].bookId].isPurchased = 1 ; //TODO: this line is example how to add properties to ebook. need to do this for like etc with real data from ajax.
							// update isLiked - 1 if liked, otherwise 0.
							$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[purchase].bookId].isliked = $rootScope.resultPurchasesTmp.data[purchase].isLiked; //TODO: this line is example how to add properties to ebook. need to do this for like etc with real data from ajax.
							// update currentScroll - update the position the user stopped reading
							$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[purchase].bookId].currentScroll = $rootScope.resultPurchasesTmp.data[purchase].currentScroll;
							
							}
							else {
								console.log("purchases list of: " + $rootScope.userLogedIn.email + " is empty!");
							}
							
							
							
							
							
							

							console.log(ebookIdDict);
							console.log('curEbook: ' + curEbook);
							console.log("$rootScope.resultPurchasesTmp.data[purchase].bookId: " + $rootScope.resultPurchasesTmp.data[purchase].bookId);

						}

//						i = 0;
//						//TODO: take the if, and merge this for and one above it.
//						if ($rootScope.resultPurchasesTmp.data.length > 0) {
//							console.log("$rootScope.resultPurchasesTmp.data: " + $rootScope.resultPurchasesTmp.data[0].bookId);
//							for (var pur in $rootScope.resultPurchasesTmp.data) {
//							
//								
//								
//								
//								console.log("pur.bookId: "+ $rootScope.resultPurchasesTmp.data[pur].bookId);
//								// update if the isPurchased flag - 1 if bought this book, otherwise 0.
//								$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].isPurchased = 1 ; //TODO: this line is example how to add properties to ebook. need to do this for like etc with real data from ajax.
//								// update isLiked - 1 if liked, otherwise 0.
//								$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].isliked = $rootScope.resultPurchasesTmp.data[pur].isLiked; //TODO: this line is example how to add properties to ebook. need to do this for like etc with real data from ajax.
//								// update currentScroll - update the position the user stopped reading
//								$rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].currentScroll = $rootScope.resultPurchasesTmp.data[pur].currentScroll;
//
//								
//								
//								
//								
//								
//								console.log("1: " + $rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].isPurchased);
//								console.log("2: " + $rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].isliked);
//								console.log("3: " + $rootScope.ebooksDict["ebook" + $rootScope.resultPurchasesTmp.data[pur].bookId].currentScroll);
//
//
//
////								$rootScope.ebooksList.push($rootScope.result.data[1]);
//								i++; //to delete this
//							}
//						}
//						else {
//							console.log("purchases list of: " + $rootScope.userLogedIn.email + " is empty!");
//						}

					});


				});

			};


		}]);




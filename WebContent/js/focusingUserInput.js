var letters = /^[A-Z a-z]+$/;//for parameters that have to match only letters
var numbers = /[0-9]/;//for parameters that have to match only numbers
var homePhone1 = /^04([0-9]{7})?$/;//pattern to phone number which starts with 04
var homePhone2 = /^02([0-9]{7})?$/;//pattern to phone number which starts with 02
var homePhone3 = /^03([0-9]{7})?$/;//pattern to phone number which starts with 03
var homePhone4 = /^09([0-9]{7})?$/;//pattern to phone number which starts with 09
var cellPhone = /^05([0-9]{8})?$/;//pattern to phone number which starts with 05
var validEmail =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;//pattern to email
$scope.errormsg1="";
	    		

function focusPassword()
		{
	 	
			var theInput = document.getElementsById("pwd");
			var theOutput = document.getElementById("out");
			var pass = document.forms[0].pass.value;
			//theInput.style.borderWidth = "2px 2px 4px 1px";
			if(theInput.length<4)
			{
				
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Weak Password";
				theOutput.style.color="red";
			}
			else
			{
				if((pass.match(letters))&&(pass.match(numbers)))
				{
					theInput.style.borderColor="green";
					theOutput.innerHTML = "Strong Password";
					theOutput.style.color="green";
				}
				else
				{
					theInput.style.borderColor="blue";
					theOutput.innerHTML = "Password steel not strong, but not weak eather,try to use numbers and latters to make your password stronger";
					theOutput.style.color="blue";
				}
			}
		}


		function focusCountry(){
	
			var theInput = document.getElementsByTagName("input")[5];
			var theOutput = document.getElementById("outc");
			var country = document.forms[0].country.value;
			if(!(country.match(letters)))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((country.length<2))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(country.match(letters)&&(country.length>=2))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusStreet(){
			var theInput = document.getElementsByTagName("input")[7];
			var theOutput = document.getElementById("outs");
			var street = document.forms[0].street.value;
			if(!(street.match(letters)))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((street.length<2))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(street.match(letters)&&(street.length>=2))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focushNumber(){

			var theInput = document.getElementsByTagName("input")[8];
			var theOutput = document.getElementById("outn");
			var hnumb = document.forms[0].hnumb.value;
			if(!(hnumb.match(numbers)))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if((hnumb.length<0))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please enter your block number";
				theOutput.style.color="red";
			}
			if(hnumb.match(numbers)||(hnumb.length>1))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusCity(){
	
			var theInput = document.getElementsByTagName("input")[6];
			var theOutput = document.getElementById("outci");
			var city = document.forms[0].city.value;
			if(!(city.match(letters)))
			{
				
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((city.length<2))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(city.match(letters)&&(city.length>=2))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusZip(){
	
			var theInput = document.getElementsByTagName("input")[9];
			var theOutput = document.getElementById("outz");
			var zip = document.forms[0].zip.value;
			if((!(zip.match(numbers)) ||zip.match(letters)))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if(zip.length!=6)
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to 7 digits";
				theOutput.style.color="red";
			}
			if(zip.length==6)
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
		}

		function focusPhone(){

			var theInput = document.getElementsByTagName("input")[5];
			var theOutput = document.getElementById("outp");
			var phone = document.forms[0].phone.value;
			if((phone.match(letters)) || (!(phone.match(numbers))))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if(phone.indexOf("05")== 0 && (phone.length<9 || phone.length>9))
			{
		    	theInput.style.borderColor="red";
				theOutput.innerHTML = "Use 10 digits for cellphone";
				theOutput.style.color="red";
			}
		    if((phone.indexOf("05")== 0) && phone.length==9)
	    	{
	    	theInput.style.borderColor="blue";
			theOutput.innerHTML = "OK";
			theOutput.style.color="blue";
	    	}
	    
		    else if((phone.indexOf("04")==0 || phone.indexOf("02")==0 || phone.indexOf("03")==0 || phone.indexOf("09")==0) && (phone.length<8 || phone.length>8))
			{
		    	theInput.style.borderColor="red";
				theOutput.innerHTML = "Use 9 digits for phone number";
				theOutput.style.color="red";
			}
		    if((phone.indexOf("04")==0 || phone.indexOf("02")==0 || phone.indexOf("03")==0 || phone.indexOf("09")==0) && phone.length==8)
	    	{
	    	theInput.style.borderColor="blue";
			theOutput.innerHTML = "OK";
			theOutput.style.color="blue";
	    	}


	    

		}

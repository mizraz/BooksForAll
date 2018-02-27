// input validation logic
var letters = /^[A-Z a-z]+$/;//for parameters that have to match only letters
var numbers = /[0-9]/;//for parameters that have to match only numbers
var homePhone1 = /^04([0-9]{7})?$/;//pattern to phone number which starts with 04
var homePhone2 = /^02([0-9]{7})?$/;//pattern to phone number which starts with 02
var homePhone3 = /^03([0-9]{7})?$/;//pattern to phone number which starts with 03
var homePhone4 = /^09([0-9]{7})?$/;//pattern to phone number which starts with 09
var cellPhone = /^05([0-9]{8})?$/;//pattern to phone number which starts with 05
var validEmail =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;//pattern to email

	    		
//functions help user to focus his input choice. 
//Used by several forms! different forms may have different ids in its fields!
//all the forms have the same order of fields
function focusPassword()
		{	 	   
	        var theInput = document.getElementsByTagName("input")[2];//look for second element in input array
			var theOutput = document.getElementById("out");//look for div where the focusing output goes
			var pass = document.forms[0].pass.value;//define value by input name (not id!)
			
			if(pass.length<3)
			{
				//if the password is too short
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Weak Password";
				theOutput.style.color="red";
			}
			
				if(((pass.match(letters))&&(pass.match(numbers)))&&(pass.length>3))
				{
					//if password is strong:matches numbers and letters
					theInput.style.borderColor="green";
					theOutput.innerHTML = "Strong Password";
					theOutput.style.color="green";
				}
				else
					{
					//ok password
					theInput.style.borderColor="blue";
					theOutput.innerHTML = "Not strong enough,try to use digits and letters";
					theOutput.style.color="blue";
					}
	
			
		}


		function focusCountry(){
	
			var theInput = document.getElementsByTagName("input")[6];//look for six element in input array
			var theOutput = document.getElementById("outc");//look for div where the focusing output goes
			var country = document.forms[0].country.value;//define value by input name (not id!)
			if(!(country.match(letters)))
			{   //if country field does not has letters only
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((country.length<3))
			{
		    	//if the country name is too short
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(country.match(letters)&&(country.length>=3))
			{
				//correct
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusStreet(){
			var theInput = document.getElementsByTagName("input")[8];//look for eight element in input array
			var theOutput = document.getElementById("outs");//look for div where the focusing output goes
			var street = document.forms[0].street.value;//define value by input name (not id!)
			if(!(street.match(letters)))
			{   //if street is not letters only
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((street.length<3))
			{   //if street name is too short
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(street.match(letters)&&(street.length>=3))
			{  //correct
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focushNumber(){

			var theInput = document.getElementsByTagName("input")[9];//look for nine element in input array
			var theOutput = document.getElementById("outn");//look for div where the focusing output goes
			var hnumb = document.forms[0].hnumb.value;//define value by input name (not id!)
			if(!(hnumb.match(numbers)))
			{   //number of block(house) is not numbers
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if((hnumb.length==0))
			{   //number of block(house) is empty
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please enter your block number";
				theOutput.style.color="red";
			}
			if(hnumb.match(numbers)||(hnumb.length>1))
			{
				//correct
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusCity(){
	
			var theInput = document.getElementsByTagName("input")[7];//look for seven element in input array
			var theOutput = document.getElementById("outci");//look for div where the focusing output goes
			var city = document.forms[0].city.value;//define value by input name (not id!)
			if(!(city.match(letters)))
			{
				//if city not only letters
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((city.length<3))
			{
		    	//if city name is too short
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(city.match(letters)&&(city.length>=3))
			{
				//correct
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusZip(){
	
			var theInput = document.getElementsByTagName("input")[10];//look for six element in input array
			var theOutput = document.getElementById("outz");//look for div where the focusing output goes
			var zip = document.forms[0].zip.value;//define value by input name (not id!)
			if((!(zip.match(numbers)) ||zip.match(letters)))
			{
				//if zip code is not numbers only
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if(zip.length!=7)
			{
		    	//zip code length is not 7
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to 7 digits";
				theOutput.style.color="red";
			}
			if(zip.length==7)
			{
				//correct
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
		}

		function focusPhone(){

			var theInput = document.getElementsByTagName("input")[5];//look for fifth element in input array
			var theOutput = document.getElementById("outp");//look for div where the focusing output goes
			var phone = document.forms[0].phone.value;//define value by input name (not id!)
			if((phone.match(letters)) || (!(phone.match(numbers))))
			{
				//if phone number is not numbers
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if(phone.indexOf("05")== 0 && (phone.length<10 || phone.length>10))
			{
		    	//if cell phone
		    	theInput.style.borderColor="red";
				theOutput.innerHTML = "Use 10 digits for cellphone";
				theOutput.style.color="red";
			}
		    if((phone.indexOf("05")== 0) && phone.length==10)
	    	{
		    	//correct cellphone
	    	theInput.style.borderColor="blue";
			theOutput.innerHTML = "OK";
			theOutput.style.color="blue";
	    	}
	    
		    else if((phone.indexOf("04")==0 || phone.indexOf("02")==0 || phone.indexOf("03")==0 || phone.indexOf("09")==0) && (phone.length<9 || phone.length>9))
			{
		    	//if stationary phone
		    	theInput.style.borderColor="red";
				theOutput.innerHTML = "Use 9 digits for phone number";
				theOutput.style.color="red";
			}
		    if((phone.indexOf("04")==0 || phone.indexOf("02")==0 || phone.indexOf("03")==0 || phone.indexOf("09")==0) && phone.length==9)
	    	{
		    	//correct stationary phone
	    	theInput.style.borderColor="blue";
			theOutput.innerHTML = "OK";
			theOutput.style.color="blue";
	    	}


	    

		}

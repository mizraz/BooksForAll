function focusPassword()
		{
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[2];
			var theOutput = document.getElementById("out");
			var pass = document.forms[0].pass.value;
			//theInput.style.borderWidth = "2px 2px 4px 1px";
			if(pass.length<4)
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Weak Password";
				theOutput.style.color="red";
			}
			else
			{
				if(((pass.match(chars1))||(pass.match(chars2)))&&(pass.match(chars3)))
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
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[5];
			var theOutput = document.getElementById("outc");
			var country = document.forms[0].country.value;
			if(country.match(chars3))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((country.length<3))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(country.match(chars1)||country.match(chars2)&& !country.match(chars3)&&(country.length>=3))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusStreet(){
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[7];
			var theOutput = document.getElementById("outs");
			var street = document.forms[0].street.value;
			if(street.match(chars3))
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
			if(street.match(chars1)||street.match(chars2)&& !street.match(chars3)&&(street.length>=3))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focushNumber(){
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[8];
			var theOutput = document.getElementById("outn");
			var hnumb = document.forms[0].hnumb.value;
			if(!hnumb.match(chars3))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use numbers";
				theOutput.style.color="red";
			}
		    if((hnumb.length<1))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please enter your block number";
				theOutput.style.color="red";
			}
			if(hnumb.match(chars3)||hnumb.match(chars2)&& (hnumb.length==1))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusCity(){
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[6];
			var theOutput = document.getElementById("outci");
			var city = document.forms[0].city.value;
			if(city.match(chars3))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "Please use letters";
				theOutput.style.color="red";
			}
		    if((city.length<3))
			{
				theInput.style.borderColor="red";
				theOutput.innerHTML = "You have to use at least 3 letters";
				theOutput.style.color="red";
			}
			if(city.match(chars1)||city.match(chars2)&& !city.match(chars3)&&(city.length>=3))
			{
				theInput.style.borderColor="blue";
				theOutput.innerHTML = "Ok";
				theOutput.style.color="blue";
			}
			}

		function focusZip(){
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[9];
			var theOutput = document.getElementById("outz");
			var zip = document.forms[0].zip.value;
			if(!zip.match(chars3) ||zip.match(chars2)||zip.match(chars1))
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
		
			var chars1=/[A-Z]/;
			var chars2=/[a-z]/;
			var chars3=/[0-9]/;
			var theInput = document.getElementsByTagName("input")[5];
			var theOutput = document.getElementById("outp");
			var phone = document.forms[0].phone.value;
			if(!phone.match(chars3))
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

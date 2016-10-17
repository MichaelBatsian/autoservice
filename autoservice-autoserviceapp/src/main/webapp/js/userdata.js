/**
 * 
 */
function create(f){
	if(f.checkAdd.checked) {
    	  	
        f.logindata.disabled = 0;
        f.fullname.disabled = 0;
        f.birthday.disabled = 0;
        f.adress.disabled = 0;
        f.phone .disabled = 0;
        f.gender.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("logindata").required=true;
    	document.getElementById("fullname").required=true;
    	document.getElementById("birthday").required=true;
    	document.getElementById("adress").required=true;
    	document.getElementById("phone").required=true;
    	document.getElementById("gender").required=true;
    }else{
    	f.logindata.disabled = 1;
        f.fullname.disabled = 1;
        f.birthday.disabled = 1;
        f.adress.disabled = 1;
        f.phone .disabled = 1;
        f.gender.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.userdataid.disabled = 0;
    	f.logindata.disabled = 0;
        f.fullname.disabled = 0;
        f.birthday.disabled = 0;
        f.adress.disabled = 0;
        f.phone .disabled = 0;
        f.gender.disabled = 0;
        f.updateBtn.disabled = 0;
        
        
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.userdataid.disabled = 1;
    	f.logindata.disabled = 1;
        f.fullname.disabled = 1;
        f.birthday.disabled = 1;
        f.adress.disabled = 1;
        f.phone .disabled = 1;
        f.gender.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.userdataid.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.userdataid.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}
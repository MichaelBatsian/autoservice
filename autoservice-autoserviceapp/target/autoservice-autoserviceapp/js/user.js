/**
 * Created by Torn on 27.12.2015.
 */

function submit(form){
    document.getElementById(form).submit();
}

function sendParameter(parameter) {
    document.getElementById("adminaction").value=parameter;
}

function create(f){
	if(f.checkAdd.checked) {
    	  	
        f.login.disabled = 0;
        f.password.disabled = 0;
        f.role.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("login").required=true;
    	document.getElementById("password").required=true;
    	document.getElementById("role").required=true;
    }else{
    	f.login.disabled = 1;
        f.password.disabled = 1;
        f.role.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.userid.disabled = 0;
        f.password.disabled = 0;
        f.login.disabled = 0;
        f.role.disabled = 0;
        f.updateBtn.disabled = 0;
        f.blockedstatus.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.userid.disabled = 1;
        f.password.disabled = 1;
        f.login.disabled = 1;
        f.role.disabled = 1;
        f.blockedstatus.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.userid.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.userid.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}



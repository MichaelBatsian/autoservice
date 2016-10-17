/**
 * 
 */
/**
 * 
 */
function create(f){
	if(f.checkAdd.checked) {
    	  	
        f.employeeLogin.disabled = 0;
        f.employeePosition.disabled = 0;
        f.employeeLocation.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("employeeLogin").required=true;
    	document.getElementById("employeePosition").required=true;
    	document.getElementById("employeeLocation").required=true;
    }else{
    	f.employeeLogin.disabled = 1;
        f.employeePosition.disabled = 1;
        f.employeeLocation.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.employeeId.disabled = 0;
        f.employeeLogin.disabled = 0;
        f.employeePosition.disabled = 0;
        f.employeeLocation.disabled = 0;
        f.updateBtn.disabled = 0;
                
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.employeeId.disabled = 1;
        f.employeeLogin.disabled = 1;
        f.employeePosition.disabled = 1;
        f.employeeLocation.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.employeeId.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.employeeId.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}

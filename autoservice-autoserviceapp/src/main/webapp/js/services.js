/**
 * 
 */
function create(f){
	if(f.checkAdd.checked) {
    	  	
        f.manhours.disabled = 0;
        f.service.disabled = 0;
        f.serviceprice.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("manhours").required=true;
    	document.getElementById("service").required=true;
    	document.getElementById("serviceprice").required=true;
    }else{
    	 f.manhours.disabled = 1;
         f.service.disabled = 1;
         f.serviceprice.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.serviceid.disabled = 0;
        f.service.disabled = 0;
        f.manhours.disabled = 0;
        f.serviceprice.disabled = 0;
        f.updateBtn.disabled = 0;
                
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.serviceid.disabled = 1;
        f.service.disabled = 1;
        f.manhours.disabled = 1;
        f.serviceprice.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.serviceid.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.serviceid.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}

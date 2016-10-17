/**
 * 
 */
function create(f){

    if(f.checkAdd.checked) {
    	 f.createBtn.disabled = 0;
        f.positionPS.disabled = 0;
        f.servicePS.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("positionPS").required=true;
    	document.getElementById("servicePS").required=true;
       }else{
    	f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
        
        f.createBtn.disabled = 1;
        f.positionPS.disabled = 1;
        f.servicePS.disabled = 1;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	 f.positionServiceId.disabled = 0;
         f.positionPS.disabled = 0;
         f.servicePS.disabled = 0;
         f.updateBtn.disabled = 0;
         
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
        
    }else{
    	f.positionServiceId.disabled = 1;
        f.positionPS.disabled = 1;
        f.servicePS.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0
   }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.positionServiceId.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
        
    }else{
    	f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
        
        f.btnDelete.disabled = 1;
        f.positionServiceId.disabled = 1;
    }
}
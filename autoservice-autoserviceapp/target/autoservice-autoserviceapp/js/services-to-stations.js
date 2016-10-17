/**
 * 
 */
function create(f){

    if(f.checkAdd.checked) {
    	 f.createBtn.disabled = 0;
        f.stationSTS.disabled = 0;
        f.serviceSTS.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("stationSTS").required=true;
    	document.getElementById("serviceSTS").required=true;
       }else{
    	f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
        
        f.createBtn.disabled = 1;
        f.stationSTS.disabled = 1;
        f.serviceSTS.disabled = 1;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	 f.servicesToStationsId.disabled = 0;
         f.stationSTS.disabled = 0;
         f.serviceSTS.disabled = 0;
         f.updateBtn.disabled = 0;
         f.checkAdd.disabled = 1;
         f.checkDelete.disabled = 1;
        
    }else{
    	f.servicesToStationsId.disabled = 1;
        f.stationSTS.disabled = 1;
        f.serviceSTS.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0
        f.updateBtn.disabled = 1;
   }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.servicesToStationsId.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
        
    }else{
    	f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
        
        f.btnDelete.disabled = 1;
        f.servicesToStationsId.disabled = 1;
    }
}
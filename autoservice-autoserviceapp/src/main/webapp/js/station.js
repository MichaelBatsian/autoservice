/**
 * 
 */
function create(f){
	
		

    if(f.checkAdd.checked) {
    	  	
        f.station.disabled = 0;
        f.location.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("location").required=true;
    	document.getElementById("station").required=true;
    }else{
    	f.station.disabled = 1;
        f.location.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){
    if(f.checkUpdate.checked) {
    	f.stationid.disabled = 0;
        f.station.disabled = 0;
        f.location.disabled = 0;
        f.updateBtn.disabled = 0;
                
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.stationid.disabled = 1;
        f.station.disabled = 1;
        f.location.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){
 
    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.stationid.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.stationid.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}

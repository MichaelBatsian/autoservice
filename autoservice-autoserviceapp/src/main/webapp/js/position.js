/**
 * 
 */
function create(f){

    if(f.checkAdd.checked) {
        f.position.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
    }else{
    	f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
        
    	f.position.disabled = 1;
        f.createBtn.disabled = 1;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.positionId.disabled = 0;
    	f.position.disabled = 0;
        f.updateBtn.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
        
    }else{
    	f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;
        
    	f.positionId.disabled = 1;
    	f.position.disabled = 1;
        f.updateBtn.disabled = 1;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.positionId.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
        
    }else{
    	f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
        
        f.btnDelete.disabled = 1;
        f.positionId.disabled = 1;
    }
}
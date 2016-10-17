/**
 * 
 */
function create(f){

    if(f.checkAdd.checked) {
        f.orderId.disabled = 1;
        f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.orderId.disabled = 0;
    	f.orderStatus.disabled = 0;
        f.updateBtn.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
        
    }else{
    	f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;
        
    	f.orderId.disabled = 1;
    	f.orderStatus.disabled = 1;
        f.updateBtn.disabled = 1;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 1;
        f.roleidroles.disabled = 1;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }
  
}
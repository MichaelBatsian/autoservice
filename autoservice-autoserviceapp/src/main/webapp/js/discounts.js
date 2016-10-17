function create(f){

    if(f.checkAdd.checked) {
        f.discountdiscounts.disabled = 0;
        f.totalsumdiscounts.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
                
        document.getElementById("discountdiscounts").required=true;
        document.getElementById("totalsumdiscounts").required=true;
        
    }else{
    	 f.checkUpdate.disabled = 0;
         f.checkDelete.disabled = 0;
         
    	 f.discountdiscounts.disabled = 1;
         f.totalsumdiscounts.disabled = 1;
         f.createBtn.disabled = 1;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.discountiddiscounts.disabled = 0;
    	f.discountdiscounts.disabled = 0;
        f.totalsumdiscounts.disabled = 0;
        f.updateBtn.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
               
        document.getElementById("discountiddiscounts").required=true;
               
    }else{
    	 f.checkAdd.disabled = 0;
         f.checkDelete.disabled = 0;
         
    	f.discountiddiscounts.disabled = 1;
    	f.discountdiscounts.disabled = 1;
        f.totalsumdiscounts.disabled = 1;
        f.updateBtn.disabled = 1;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.discountiddiscounts.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
        
        document.getElementById("discountiddiscounts").required=true;

        
    }else{
        f.btnDelete.disabled = 1;
        f.discountiddiscounts.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}

/**
 * Created by Torn on 27.12.2015.
 */
function create(f){
	
	if(f.checkAdd.checked){
		f.clientdiscount.disabled = 0;
		f.loginclientdiscount.disabled = 0;
		f.clienttotalsum.disabled = 0;
		f.createBtn.disabled = 0;
		
		f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
		
		document.getElementById("clientdiscount").required=true;
		document.getElementById("loginclientdiscount").required=true;
		document.getElementById("clienttotalsum").required=true;
		
		
	}else{
		f.clientdiscount.disabled = 1;
		f.loginclientdiscount.disabled = 1;
		f.clienttotalsum.disabled = 1;
		f.createBtn.disabled = 1;
		
		f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
		
	}
}

function update(f){
	
	if(f.checkUpdate.checked){
		f.clientdiscountid.disabled=0;
		f.clientdiscount.disabled=0;
		f.loginclientdiscount.disabled=0;
		f.clienttotalsum.disabled=0; 
		f.updateBtn.disabled = 0;
		
		 f.checkAdd.disabled = 1;
	     f.checkDelete.disabled = 1;
		
		document.getElementById("clientdiscountid").required=true;
				
	}else{
		f.clientdiscountid.disabled=1;
		f.clientdiscount.disabled=1;
		f.loginclientdiscount.disabled=1;
		f.clienttotalsum.disabled=1; 
		f.updateBtn.disabled = 1;
		
		f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;
	}	
	
}

function deleteF(f){

    if(f.checkDelete.checked) {
    	f.clientdiscountid.disabled=0;
    	f.btnDelete.disabled = 0;
    	
    	f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    	 
    	document.getElementById("clientdiscountid").required=true;
    }else{
    	f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    	
    	f.clientdiscountid.disabled = 1;
        f.btnDelete.disabled = 1;
    }
}


/**
 * 
 */
function create(f){
	
		

    if(f.checkAdd.checked) {
    	  	
        f.loginmessage.disabled = 0;
        f.message.disabled = 0;
        f.topicmessage.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("loginmessage").required=true;
    	document.getElementById("message").required=true;
    	document.getElementById("topicmessage").required=true;
    }else{
    	f.loginmessage.disabled = 1;
        f.message.disabled = 1;
        f.topicmessage.disabled = 1;
        f.createBtn.disabled = 1;        
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.messageid.disabled = 0;
        f.loginmessage.disabled = 0;
        f.message.disabled = 0;
        f.topicmessage.disabled = 0;
        f.updateBtn.disabled = 0;
                
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.messageid.disabled = 1;
        f.loginmessage.disabled = 1;
        f.message.disabled = 1;
        f.topicmessage.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.messageid.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.messageid.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}



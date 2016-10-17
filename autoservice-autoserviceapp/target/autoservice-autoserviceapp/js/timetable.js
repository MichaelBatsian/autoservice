/**
 * 
 */
/**
 * 
 */
function create(f){
	if(f.checkAdd.checked) {
    	  	
        f.orderIdTimeTable.disabled = 0;
        f.employeeIdTimeTable.disabled = 0;
        f.dateTimeTable.disabled = 0;
        f.timeTimeTable.disabled = 0;
        f.createBtn.disabled = 0;
        
        f.checkUpdate.disabled = 1;
        f.checkDelete.disabled = 1;
        
        document.getElementById("orderIdTimeTable").required=true;
    	document.getElementById("employeeIdTimeTable").required=true;
    	document.getElementById("dateTimeTable").required=true;
    	document.getElementById("timeTimeTable").required=true;
    }else{
    	 f.orderIdTimeTable.disabled = 1;
         f.employeeIdTimeTable.disabled = 1;
         f.dateTimeTable.disabled = 1;
         f.timeTimeTable.disabled = 1;
         f.createBtn.disabled = 1;
        
        f.checkUpdate.disabled = 0;
        f.checkDelete.disabled = 0;
    }
}

function update(f){

    if(f.checkUpdate.checked) {
    	f.timeTableId.disabled = 0;
    	f.employeeIdTimeTable.disabled = 0;
        f.dateTimeTable.disabled = 0;
        f.timeTimeTable.disabled = 0;
        f.orderIdTimeTable.disabled = 0;
        f.updateBtn.disabled = 0;
                
        f.checkAdd.disabled = 1;
        f.checkDelete.disabled = 1;
    }else{
    	f.timeTableId.disabled = 1;
    	f.employeeIdTimeTable.disabled = 1;
        f.dateTimeTable.disabled = 1;
        f.timeTimeTable.disabled = 1;
        f.orderIdTimeTable.disabled = 1;
        f.updateBtn.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkDelete.disabled = 0;

    }
}

function deleteF(f){

    if(f.checkDelete.checked) {
        f.btnDelete.disabled = 0;
        f.timeTableId.disabled = 0;
        
        f.checkAdd.disabled = 1;
        f.checkUpdate.disabled = 1;
    }else{
        f.btnDelete.disabled = 1;
        f.timeTableId.disabled = 1;
        
        f.checkAdd.disabled = 0;
        f.checkUpdate.disabled = 0;
    }
}

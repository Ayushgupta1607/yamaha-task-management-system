// Display alert if available
$(document).ready(function() {
	let message = `${message}`;
	if (message){
		$('#toastmessage').html(message)
		$('#toast').toast('show')
	}
})

//Display alert on click disabled button 
function disabledClick(btn){
	let message;
	if(btn=="edit") message="Completed Task cannot be updated."
		if(btn=="delete") message="Assigned Task cannot be deleted"
	$('#toastmessage').html(message)
	$('#toast').toast('show')
}

var commentHTML=""
var comments = [];
var bool=false;
//Adding Comments to update task page
function addComment(task_id) {
	 boxvalue = document.getElementById('commentInput-'+task_id).value;
	 if(boxvalue)comments.push(boxvalue);  
	 let content="";
	 document.getElementById('commentInput-'+task_id).value=""
     comments.map(comment => {
				content+='<input class="form-control comments" readonly name="comments[]" value="'+comment+ '"/>';
				})
	 $('#commentdiv-'+task_id).html(content);
	 return false;
}

//Reset update task form
function resetUpdateForm(task_id){
	$('#commentdiv-'+task_id).html("");
	comments=[]
	bool=false;
	$('#updateTaskForm-'+task_id).trigger("reset");
}

//Close Update Modal and reset
function closeModal(task_id){
	$('#commentdiv-'+task_id).html("");
	comments=[]
	$('#updateTaskForm-'+task_id).trigger("reset");
	$('#updateTaskModal-'+task_id ).modal('toggle');
	
}
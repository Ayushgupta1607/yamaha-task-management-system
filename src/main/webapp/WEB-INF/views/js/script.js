$(document).ready(function() {
	let message = `${message}`;
	
	if (message){
		
		$('#toastmessage').html(message)
		$('#toast').toast('show')
		setTimeout(15000,()=>{
			$('#toast').toast('hide')
		})
	}
	
//		alert(message);

})
var commentHTML=""
var comments = [];
var bool=false;
function addComment(task_id) {
	
	 boxvalue = document.getElementById('commentInput-'+task_id).value;
	
	 if(boxvalue)comments.push(boxvalue);  
	

	 let content="";
	 document.getElementById('commentInput-'+task_id).value=""
		 comments.map(com => {
			 
				content+='<input class="form-control comments" readonly name="comments[]" value="'+com+ '"/>';
			
			})
			$('#commentdiv-'+task_id).html(content);
	
return false;
}

function resetUpdateForm(task_id){
console.log("reset");
	$('#commentdiv-'+task_id).html("");
	comments=[]
	bool=false;
	$('#updateTaskForm-'+task_id).trigger("reset");

}

function closeModal(task_id){
	$('#commentdiv-'+task_id).html("");
	comments=[]
	$('#updateTaskForm-'+task_id).trigger("reset");
	$('#updateTaskModal-'+task_id ).modal('toggle');
	
}
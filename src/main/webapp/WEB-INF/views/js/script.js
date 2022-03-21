$(document).ready(function() {
	let message = `${message}`;
	console.log(message)
	if (message)
		alert(message);

})

var comments = [];
function addComment() {
	console.log(`${commentJava}`)
	 console.log('test')
	 boxvalue = document.getElementById('commentInput').value;
	 if(boxvalue)comments.push(boxvalue);  
	 `${commentJava.add(boxvalue)}`
	 document.getElementById('commentInput').value=""
		 
	 console.log(comments);
return false;
}
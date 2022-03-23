

<!-- Modal -->
<div class="modal fade" id="deleteTaskModal-${task.taskId}" tabindex="-1" role="dialog"
 data-keyboard="false" data-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Delete Task</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<i class="fa-solid fa-circle-xmark" ></i>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to delete task <b>${task.title }</b></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger closebtn" data-dismiss="modal">Cancel</button>
				<form action="deleteTask" id="deleteTaskForm-${task.taskId }" method="POST">
					<input type="hidden" name="task_id" value=${task.taskId }> 
				<button type="submit" form="deleteTaskForm-${task.taskId }" class="btn btn-primary">Delete
					Task</button>
				</form>
			</div>
		</div>
	</div>
</div>
			


<!-- Modal -->
<div class="modal fade" id="deleteTaskModal-${task.task_id }" tabindex="-1" role="dialog"
 data-keyboard="false" data-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Delete Task</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Are you sure you want to delete task <b>${task.title }</b></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<form action="deleteTask" id="deleteTaskForm-${task.task_id }" method="POST">
					<input type="hidden" name="task_id" value=${task.task_id }> 
				<button type="submit" form="deleteTaskForm-${task.task_id }" class="btn btn-primary">Delete
					Task</button>
				</form>
			</div>
		</div>
	</div>
</div>
			
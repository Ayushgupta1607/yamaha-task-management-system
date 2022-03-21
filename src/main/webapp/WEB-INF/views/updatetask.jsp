

<!-- Modal -->
<div class="modal fade" id="updateTaskModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Create Task</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="updateTask" id="updateTaskForm" method="POST">
					<input type="hidden" name="task_id" value=${task.task_id }>
					<div class="form-group">
						<label for="DescriptionInput">Description</label>
						<textarea name="description" class="form-control"
							id="DescriptionInput" maxlength="500">${task.description }</textarea>
					</div>

					<div class="form-group">
						<div class="custom-control custom-switch">
							<input type="checkbox" class="custom-control-input" name="status"
								value=true id="customSwitches"> <label
								class="custom-control-label" for="customSwitches">${task.status=="TO-DO"?"Start Task":"Complete Task" }</label>
						</div>
					</div>
					<div class="form-group">
						<select name="assigned_to_id" class="custom-select">
						
							<option value="${task.assignedTo.user_id}" selected>${task.assignedTo.username }</option>
							<option value=0>unassign</option>
							<c:forEach items="${users}" var="user">
								<c:if test="${task.assignedTo.user_id!=user.user_id }">
									<option value=${user.user_id }>${user.username}</option>
									</c:if>
							</c:forEach>
						</select>
					</div>
					<c:set var="commentsJava" value="${}" scope="application"></c:set>
					<div class="form-group">
						<label for="commentInput">Comment</label> 
							<textarea class="form-control"
							id="commentInput" maxlength="200"></textarea>
							<input class="btn btn-primary" onclick="return addComment()" value="submit"> 
							
					</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="reset" class="btn btn-primary" form="updateTaskForm">Reset</button>
				<button type="submit" form="updateTaskForm" class="btn btn-primary">Save
					changes</button>
			</div>
		</div>
	</div>
</div>



<!-- Modal -->

<div class="modal fade" id="updateTaskModal-${task.task_id }"
 data-keyboard="false" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Task</h5>
				<button type="button" class="close"
					onClick="closeModal(${task.task_id})"
					form="updateTaskForm-${task.task_id}" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="updateTask" id="updateTaskForm-${task.task_id }"
					method="POST">
					<input type="hidden" name="task_id" value=${task.task_id }>
					<div class="form-group">
						<label for="DescriptionInput">Description</label>
						<textarea name="description" class="form-control"
							id="DescriptionInput" maxlength="500">${task.description }</textarea>
					</div>
					<c:if test="${task.assignedTo==currentUser }">
					<div class="form-group">
										<!-- <label for="#statusInput">Status</label> -->
					
						<div class="custom-control custom-switch">
							<input type="checkbox" class="custom-control-input" name="status"
								value=true id="customSwitches-${task.task_id }"> <label
								class="custom-control-label"
								for="customSwitches-${task.task_id }"> <c:choose>
									<c:when test="${task.status=='TO-DO'}">Start Task</c:when>
									<c:otherwise>Complete Task</c:otherwise>
								</c:choose>
							</label>
						</div>
					</div>
					</c:if>
					<div class="form-group">
					<label for="#AssignToInput">Assign To</label>
						<select name="assigned_to_id" class="custom-select">
	<c:choose>
	<c:when test="${task.assignedTo!=null }">
	<option value="${task.assignedTo.user_id}" selected>${task.assignedTo.username }</option>
							<option value=0>unassign</option>
	</c:when>
						<c:otherwise><option value=0 selected>unassign</option> </c:otherwise>
						<%-- 	<option value="${task.assignedTo.user_id}" selected>${task.assignedTo.username }</option>
							<option value=0>unassign</option> --%>
							</c:choose>
							<c:forEach items="${users}" var="user">
								<c:if test="${(task.assignedTo.user_id)!=(user.user_id) }">
									<option value=${user.user_id }>${user.username}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

					<div class="form-group" id="commentHTML"></div>
					<div class="form-group">
						<label for="commentInput">Comments</label>
					<div id="commentdiv-${task.task_id}"></div>
						<textarea class="form-control" id="commentInput-${task.task_id }"
							maxlength="200"></textarea>
					</div>
						<div class="form-group">
							<input class="addCommentButton btn btn-primary"
								onclick="return addComment(${task.task_id})" value="Add Comment">
						</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" onClick="closeModal(${task.task_id})"
					form="updateTaskForm-${task.task_id}" class="btn btn-danger">Close</button>
				<button type="button" onClick="resetUpdateForm(${task.task_id})"
					class="btn reset" form="updateTaskForm-${task.task_id}">Reset</button>
				<button type="submit" form="updateTaskForm-${task.task_id }"
					class="btn savebtn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>

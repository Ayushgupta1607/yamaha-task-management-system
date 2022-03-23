

<!-- Modal -->

<div class="modal fade" id="updateTaskModal-${task.taskId }"
 data-keyboard="false" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Update Task</h5>
				<button type="button" class="close"
					onClick="closeModal(${task.taskId})"
					form="updateTaskForm-${task.taskId}" aria-label="Close">
					<i class="fa-solid fa-circle-xmark" ></i>
				</button>
			</div>
			<div class="modal-body">
				<form action="updateTask" id="updateTaskForm-${task.taskId }"
					method="POST">
					<div class="form-group" >
					<label>Title</label>
					<input class="form-control " readonly  value=${task.title } />
					</div>
					<input type="hidden" name="taskId" value=${task.taskId }>
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
								value=true id="customSwitches-${task.taskId }"> <label
								class="custom-control-label"
								for="customSwitches-${task.taskId }"> <c:choose>
									<c:when test="${task.status=='TO-DO'}">Start Task</c:when>
									<c:otherwise>Complete Task</c:otherwise>
								</c:choose>
							</label>
						</div>
					</div>
					</c:if>
					<div class="form-group">
					<label for="#AssignToInput">Assign To</label>
						<select name="assignedToId" class="custom-select">
	<c:choose>
	<c:when test="${task.assignedTo!=null }">
	<option value="${task.assignedTo.userId}" selected>${task.assignedTo.username }</option>
							<option value=0>unassign</option>
	</c:when>
						<c:otherwise><option value=0 selected>unassign</option> </c:otherwise>
						<%-- 	<option value="${task.assignedTo.user_id}" selected>${task.assignedTo.username }</option>
							<option value=0>unassign</option> --%>
							</c:choose>
							<c:forEach items="${users}" var="user">
								<c:if test="${(task.assignedTo.userId)!=(user.userId) }">
									<option value=${user.userId }>${user.username}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>

					<div class="form-group" id="commentHTML"></div>
					<div class="form-group">
						<label for="commentInput">Comments</label>
					<div id="commentdiv-${task.taskId}"></div>
						<textarea class="form-control" id="commentInput-${task.taskId }"
							maxlength="200"></textarea>
					</div>
						<div class="form-group">
							<input class="addCommentButton btn btn-primary"
								onclick="return addComment(${task.taskId})" value="Add Comment">
						</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" onClick="closeModal(${task.taskId})"
					form="updateTaskForm-${task.taskId}" class="btn btn-danger closebtn">Cancel</button>
				<button type="button" onClick="resetUpdateForm(${task.taskId})"
					class="btn reset" form="updateTaskForm-${task.taskId}">Reset</button>
				<button type="submit" form="updateTaskForm-${task.taskId }"
					class="btn savebtn btn-primary">Update</button>
			</div>
		</div>
	</div>
</div>

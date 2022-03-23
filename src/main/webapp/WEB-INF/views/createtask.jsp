

<!-- Modal -->
<div class="modal fade" id="createTaskModal" tabindex="-1" role="dialog"
 data-keyboard="false" data-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Create Task</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i class="fa-solid fa-circle-xmark" ></i>
					
				</button>
			</div>
			<div class="modal-body">
				<form action="" id="createTaskForm" method="post">
					<div class="form-group">
						<label for="#TitleInput">Title<span style="color:red">*</span></label> 
						<input type="text" name="title" value='' class="form-control" id="TitleInput" maxlength="50" required>
					</div>
					<div class="form-group">
						<label for="#descriptionInput">Description</label>
						<textarea name="description" value='' class="form-control" id="DescriptionInput" maxlength="500"></textarea>
					</div>
					<div class="form-group">
					<label for="#assignedToInput">Assign To</label>
						<select id="assignedToInput" name="assignedToId" class="custom-select">
							<option selected value="0">unassign</option>
							<c:forEach items="${users}" var="user">
								<option value=${user.userId }>${user.username}</option>
							</c:forEach>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger closebtn" data-dismiss="modal">Cancel</button>
			<button type="reset" class="btn reset" form="createTaskForm">Reset</button>
				<button type="submit" form="createTaskForm" class="btn savebtn btn-primary">Create Task</button>
			</div>
		</div>
	</div>
</div>
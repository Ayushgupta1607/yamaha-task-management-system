
<%@ include file="header.jsp"%>

<br>


<div class="container taskTable">


	<button class="btn createTaskButton btn-primary" data-toggle="modal"
		data-target="#createTaskModal">createTask</button>
	<%@ include file="createtask.jsp"%>
	<br>
	<div class="dropdown filterDropdown">
		<button
			class=" filterDropdownButton btn btn-secondary dropdown-toggle"
			type="button" id="dropdownMenuButton" data-toggle="dropdown"
			aria-haspopup="true" aria-expanded="false">Filter</button>
		<div class="dropdown-menu " aria-labelledby="dropdownMenuButton">
			<a class="dropdown-item" href="?filter=assignedToMe">Assigned To
				Me</a> <a class="dropdown-item" href="?filter=createdByMe">Created
				By Me</a>
		</div>
	</div>
	<br>
	<table class="table">
		<thead>
			<tr>

				<th>Task Title</th>
				<th>Status</th>
				<th>Created By</th>
				<th>Assigned To</th>
				<th>Action</th>

			</tr>
		</thead>

		<c:forEach items="${tasks}" var="task">
			<tr>
				<td>${task.title}</td>
				<td>${task.status}</td>
				<td>${task.createdBy.username}</td>
				<td>${task.assignedTo.username}</td>
				<td>
					<button class="btn" data-toggle="modal"
						data-target="#viewTaskModal">
						<i class="fa-solid fa-eye"> </i>
					</button> <%@ include file="viewtask.jsp"%> <c:set
						var="status" value="${task.status }" /> <c:if
						test="${status!='COMPLETED'}">
						<button class="btn" data-toggle="modal"
							data-target="#updateTaskModal">
							<i class="fa-solid fa-pen-to-square"></i>
						</button>
						<%@ include file="updatetask.jsp"%>
					</c:if>
					<c:if test="${task.assignedTo!=null }">
					<button class="btn" data-toggle="modal"
						data-target="#deleteTaskModal">
						<i class="fa-solid fa-trash-can"></i>
					</button> <%@ include file="deletetask.jsp"%>
</c:if>


				</td>
			</tr>
		</c:forEach>

	</table>

</div>

<%@ include file="footer.jsp"%>
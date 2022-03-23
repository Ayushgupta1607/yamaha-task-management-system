
<%@ include file="header.jsp"%>

<br>

<main >
<div class="container taskTable">

	<h2 style="display:inline">Tasks</h2>
	<button style="margin-left:auto" class="btn createTaskButton btn-primary" data-toggle="modal"
		data-target="#createTaskModal">Create Task</button>
		
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
				<a class="dropdown-item" href="/tms/">Show All</a>
		</div>
	</div>
	<br>
	<table class="table hometable table-striped table-borderless">
		<thead >
			<tr style="border:solid ; border-color:grey ;border-width:0px;border-bottom-width:1.5px">

				<th>Title</th>
				<th>Status</th>
				<th>Created By</th>
				<th>Assigned To</th>
				<th>Action</th>

			</tr>
		</thead>

		<c:forEach items="${tasks}" var="task">

			<tr style="border:solid ; border-color:grey ;border-width:0px;border-bottom-width:1.5px">
				<td>${task.title}</td>
				<c:choose>
				<c:when test="${(task.status=='TO-DO')}"><td style="color:blue; font-weight:bold" class="status ">${task.status}</td></c:when>
				<c:when test="${(task.status=='IN-PROGRESS')}"><td style="color:orange; font-weight:bold" class="status ">${task.status}</td></c:when>
				<c:otherwise><td style="color:green; font-weight:bold" class="status  ">${task.status}</td></c:otherwise>
				</c:choose>
				
				<td>${task.createdBy.username}</td>
	
				<c:choose>
				<c:when test="${(task.assignedTo.username!=null)}"><td>${task.assignedTo.username}</td></c:when>
				<c:otherwise><td style="color:red">unassigned</td></c:otherwise>
				</c:choose>
				<td>
					<button class="btn viewbtn" data-toggle="modal"
							data-target="#viewTaskModal-${task.taskId }">
							<i class="fa-solid fa-eye"> </i>
						</button> <%@ include file="viewtask.jsp"%>
					<c:choose>
					<c:when test="${task.status!='COMPLETED' }">
						<button class="btn updatebtn" data-toggle="modal" 
							data-target="#updateTaskModal-${task.taskId}">
							<i class="fa-solid fa-pen-to-square"></i>
						</button>
						<%@ include file="updatetask.jsp"%>
						</c:when>
						<c:otherwise>
						<button onclick="disabledClick('edit')" style="color:#2125298c" class="btn " data-toggle="modal" 
							data-target="#updateTaskModal-${task.taskId}">
							<i class="fa-solid fa-pen-to-square"></i>
						</button>
						</c:otherwise>
						</c:choose>
						<c:choose>
					<c:when test="${task.assignedTo==null }">
						<button class="btn deletebtn" data-toggle="modal"
							data-target="#deleteTaskModal-${task.taskId }">
							<i class="fa-solid fa-trash-can"></i>
						</button>
						<%@ include file="deletetask.jsp"%>
					</c:when>
					<c:otherwise>
					<button onclick="disabledClick('update')" style="color:#2125298c" class="btn" data-toggle="modal" 
							data-target="#deleteTaskModal-${task.taskId }">
							<i class="fa-solid fa-trash-can"></i>
						</button>
					</c:otherwise>
</c:choose>

				</td>
			</tr>
		
		</c:forEach>
	
	</table>

</div>
</main>

<%@ include file="footer.jsp"%>
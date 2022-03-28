
<%@ include file="header.jsp"%>


<div class="container taskTable" id="taskTable">
<div style="display:flex;flex-direction:row">
	<h2 style="display:inline">TASK LIST</h2>
	<button style="margin-left:auto;margin-bottom:5px" class="btn createTaskButton btn-primary" data-toggle="modal"
		data-target="#createTaskModal">Create Task</button>
		</div>
		
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
	<table class="table hometable table-striped table-borderless" id="hometable">
		<thead >
			<tr style="border:solid ; border-color:grey ;border-width:0px;border-bottom-width:1.5px">
				<th>Task ID</th>
				<th>Title</th>
				<th>Status</th>
				<th>Created By</th>
				<th>Assigned To</th>
				<th style="width:150px;text-align:center">Action</th>

			</tr>
		</thead>

		<c:forEach items="${tasks}" var="task">
			<tr style="border:solid ; border-color:grey ;border-width:0px;border-bottom-width:1.5px">
			<td>Task${task.taskId }</td>
				<td class="homeTitle">${task.title}</td>
				<c:choose>
				<c:when test="${(task.status=='TO-DO')}"><td style="color:blue; font-weight:bold" class="status ">${task.status}</td></c:when>
				<c:when test="${(task.status=='IN-PROGRESS')}"><td style="color:orange; font-weight:bold" class="status ">${task.status}</td></c:when>
				<c:otherwise><td style="color:green; font-weight:bold" class="status  ">${task.status}</td></c:otherwise>
				</c:choose>
				
				<td>${task.createdBy.username.toUpperCase()}</td>
	
				<c:choose>
				<c:when test="${(task.assignedTo.username!=null)}"><td>${task.assignedTo.username.toUpperCase()}</td></c:when>
				<c:otherwise><td style="color:red">Unassigned</td></c:otherwise>
				</c:choose>
				<td style="text-align:center">
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
					<button onclick="disabledClick('delete')" style="color:#2125298c" class="btn" data-toggle="modal" 
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


<%@ include file="footer.jsp"%>
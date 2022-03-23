

<!-- Modal -->
<div class="modal fade" id="viewTaskModal-${task.task_id }" tabindex="-1" role="dialog"
 data-keyboard="false" data-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Task details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					
					<tbody>
						<tr>
							<th scope="row">Title</th>
							<td>${task.title }</td>
						</tr>
						<tr>
							<th scope="row">Description</th>
							<td>${task.description }</td>
						</tr>
						<tr>
							<th scope="row">Status</th>
							<td >${task.status }</td>
						</tr>
						<tr>
							<th scope="row">Started on</th>
							<td>${task.startedDate!=null ? task.startedDate:"-"}</td>
						</tr>
						<tr>
							<th scope="row">Completed on</th>
							<td>${task.completionDate!=null?task.completionDate : "-" }</td>
						</tr>
						<tr>
							<th scope="row">Assigned to</th>
							<td>${(task.assignedTo.username!=null)?task.assignedTo.username:'unassigned'}</td>
						</tr>
						<tr>
							<th scope="row">Created by</th>
							<td>${task.createdBy.username }</td>
						</tr>
						
					
					</tbody>
				</table>
				<c:if test="${task.comments.size()!=0}">
				<h5>Comments</h3>
				<div style="overflow-y: auto;  max-height: 100px">
				<c:forEach items="${task.comments }" var="comment">
							<b>${comment.user.username }</b>
							<p>${comment.message }</p>
						</c:forEach>
						</div>
						</c:if>
			</div>
			
		</div>
	</div>
</div>
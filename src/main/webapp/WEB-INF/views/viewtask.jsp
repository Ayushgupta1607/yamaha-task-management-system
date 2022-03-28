

<!-- Modal -->
<div class="modal fade" id="viewTaskModal-${task.taskId }" tabindex="-1" role="dialog"
 data-keyboard="false" data-backdrop="static"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Task details</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<i class="fa-solid fa-circle-xmark" ></i>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					
					<tbody>
					<tr>
							<th scope="row">Task ID</th>
							<td>Task${task.taskId }</td>
						</tr>
						<tr>
							<th scope="row">Title</th>
							<td class="viewContent" >${task.title }</td>
						</tr>
						<tr>
							<th scope="row">Description</th>
							<td  class="viewContent" >${task.description }</td>
						</tr>
						<tr>
							<th scope="row">Status</th>
							<td >${task.status }</td>
						</tr>
						<tr>
						
							<th scope="row">Started on</th>
							<c:choose>
							<c:when test="${ task.startedDate!=null}">
							<td><fmt:formatDate value="${task.startedDate}" pattern="dd-MM-yyyy " />
</td>
							</c:when>
							<c:otherwise>
							<td>-</td>
							</c:otherwise>
							</c:choose>
							
						</tr>
						<tr>
							<th scope="row">Completed on</th>
							<c:choose>
							<c:when test="${ task.completionDate!=null}">
							<td><fmt:formatDate value="${task.completionDate}" pattern="dd-MM-yyyy " />
							</c:when>
							<c:otherwise>
							<td>-</td>
							</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th scope="row">Assigned to</th>
							<td>${(task.assignedTo.username!=null)?task.assignedTo.username.toUpperCase():'Unassigned'}</td>
						</tr>
						<tr>
							<th scope="row">Created by</th>
							<td>${task.createdBy.username.toUpperCase() }</td>
						</tr>
						
					
					</tbody>
				</table>
				<c:if test="${task.comments.size()!=0}">
				<h5>Comments</h3>
				<div style="overflow-y: auto;  max-height: 100px">
				<c:forEach items="${task.comments }" var="comment">
							<b>${comment.user.username.toUpperCase() }</b>
							<p>${comment.message }</p>
						</c:forEach>
						</div>
						</c:if>
			</div>
			
		</div>
	</div>
</div>
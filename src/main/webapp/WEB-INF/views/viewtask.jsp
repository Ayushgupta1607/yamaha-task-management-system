

<!-- Modal -->
<div class="modal fade" id="viewTaskModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">${ task.title}</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					
					<tbody>
						<tr>
							<th scope="row">title</th>
							<td>${task.title }</td>
						</tr>
						<tr>
							<th scope="row">description</th>
							<td>${task.description }</td>
						</tr>
						<tr>
							<th scope="row">status</th>
							<td>${task.status }</td>
						</tr>
						<tr>
							<th scope="row">started on</th>
							<td>${task.startedDate!=null ? task.startedDate:"-"}</td>
						</tr>
						<tr>
							<th scope="row">completed on</th>
							<td>${task.completionDate!=null?task.completionDate : "-" }</td>
						</tr>
						<tr>
							<th scope="row">assigned to</th>
							<td>${task.assignedTo.username }</td>
						</tr>
						<tr>
							<th scope="row">created by</th>
							<td>${task.createdBy.username }</td>
						</tr>
						<div style="overflow-y: auto; height: auto; max0-height: 200px">
						<c:forEach items="${task.comments }" var="comment">
							<b>${comment.user.username }</b>
							<p>${comment.message }</p>
						</c:forEach>
					</div>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
</div>
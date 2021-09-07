<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">DTO 객체(Command Object)와 폼 연결</div>
	<div class="card-body">
		<form method="post" action="form1">
			<div class="form-group">
				<label for="mid">Member ID</label>
				<input type="text" class="form-control" name="mid">
			</div>
			<div class="form-group">
				<label for="mname">Name</label>
				<input type="text" class="form-control" name="mname">
			</div>
			<div class="form-group">
				<label for="mpassword">Password</label>
				<input type="password" class="form-control" name="mpassword">
			</div>
			<div class="form-group">
				<label for="mnation">Nation</label>
				<input type="text" class="form-control" name="mnation" value="${defaultNation}">
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
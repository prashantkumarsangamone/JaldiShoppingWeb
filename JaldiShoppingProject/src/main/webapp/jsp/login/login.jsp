<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/nav.jsp"></jsp:include>	
<div id="header1"> 
        
        </div>
<div style="padding-top: 60px" >

</div>

<div class="container">

	<div class="row" id="pwd-container">
		<div class="col-md-4">&nbsp;</div>
		<div class="col-md-4">

			<form action="<%=request.getContextPath()%>/admin/authenticate"
				method="post" role="login">
				<div class="message error">
					<c:out value="${error}" />
				</div>
				<div class="message success">
					<c:out value="${success}" />
				</div>
				<input class="form-control input-lg" name="username"
					placeholder="Email Id" required="" type="text" value="" /> <input
					class="form-control input-lg" name="password"
					placeholder="Password" required="" type="password" />
			
			<button class="btn btn-lg btn-primary btn-block" name="go" type="submit">Login</button>
			<a href="<%=request.getContextPath()%>/jsp/login/signup.jsp">Signup</a>
			<%-- <a href="<%=request.getContextPath()%>/usr/forgotPassword">Forgot Password ?</a> --%>
			
			
			</form>
			
		</div>			
		<div class="col-md-4">&nbsp;</div>
	</div>
</div>
<div id="footer">
         <center>Copyright © 2017 SangamOne Connected Services Pvt Ltd . All Rights Reserved.</center> 
		</div>
</body>
</html>
<%-- <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 --%> <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/navigationBar.jsp"></jsp:include>
<style>
#header1{  background-color:#FFFFFF;
          margin-top:-170px;
          
         height: 130px;
}
</style>

        <body>
        <div id="header2"></div>	
<section>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2"></div>
			<div class="col-sm-8 text-left">
				<h1>Reset Your Password</h1>

			</div>
		</div>

		

			</div>
			
			<div class="row content">
			<div class="col-sm-2"></div>
			<div class="col-md-6">

				<form action="<%=request.getContextPath()%>/updatePassword"
					method="post" role="updatePassword">

					<div class="col-md-7">

						<input class="form-control input-lg" name="password"
							placeholder="New Password" required="" type="text" value="" />
							<br>
						<input class="form-control input-lg" name="confirmPassword"
							placeholder="Confirm Password" required="" type="text" value="" />
									<div class="col-md-3"><br>
						<button class="btn btn-lg btn-primary" name="Submit" type="submit">Submit</button>
					</div>
					</div>
					
				</form>
			</div>
			<div class="row content">
			<div class="col-sm-2"></div>
			<div class="col-md-6">

				<div class="message error">
					<c:out value="${error}" />
				</div>
				<div class="message success">
					<c:out value="${success}" />
				</div>

			
			<div class="col-md-3">&nbsp;</div>
		</div>
	</div></div>
</section>
</body>
</html>


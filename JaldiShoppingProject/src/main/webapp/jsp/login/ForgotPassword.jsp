<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" %>
<jsp:include page="../common/header.jsp"></jsp:include>
<head>
<style type="text/css">
#head{ background-color:#FFFFFF;
          margin-top:-105px;
           margin-bottom:30px;
          padding-top:-10px; 
         height: 100px;
       
}</style>
</head>
<body>
<div id="head"></div>	

<section>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2"></div>
			<div class="col-sm-8 text-left">
				<h1>Reset Your Password </h1>

			</div>
		</div>

			
			<div class="row content">
			<div class="col-sm-2"></div>
			<div class="col-md-6">

				<form action="<%=request.getContextPath()%>/usr/forgotPasswordURL"
					method="post" role="forgotPasswordURL">

					<div class="col-md-7">

					
						<input class="form-control input-lg" name="emailId"
							placeholder="Email ID" required="" type="text" value="" />	<br>						
					
					<div class="col-md-5">
						<button class="btn btn-lg btn-primary" name="Submit" type="submit" style="margin-left: -15px;">Submit</button>
					</div>
					<div class="col-md-3">				
					<a href="<%=request.getContextPath()%>/admin/login"><button class="btn btn-lg btn-primary" name="Submit" type="button" style="margin-left: -15px;">Back to Login</button></a>
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
		

			</div>
			</div>
			
			
			<div class="col-md-3">&nbsp;</div>
		</div>
	</div>
</section>
</body>
</html>



<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@ page session="true"%>
<jsp:include page="../common/header.jsp"></jsp:include>
 <jsp:include page="../common/navigationBar.jsp"></jsp:include>
<!-- <style>
#header1{  background-color:#FFFFFF;
          margin-top:-170px;
          
         height: 130px;
}
</style> -->
<div id="header1"> 
        
        </div><br><br>
<link rel="Stylesheet"
	href="<%=request.getContextPath()%>/resources/css/fileinput.min.css">
<script
	src="<%=request.getContextPath()%>/resources/js/fileinput.min.js"></script>
<body>

                                                                                            
    <br><br>
     <center> <div class="Upload"><h3>Change Password</h3></div>
         <br>
        
    
           
	<div class="container design">
		<div class="row">
			<div class="col-md-3">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-md-3">&nbsp;</div>
			<div class="col-md-6">
			
		
				<form id="changePassword" action="<%=request.getContextPath()%>/admin/changePassword"
					method="POST" >
					
					
					<div class="message error"><c:out value="${error}" /></div>
					
					<div class="message success"><c:out value="${success}" /></div>
				
			
					
					  <div class="form-group">
       					 <label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">New Password </label>
       						 <div class="col-md-7 col-sm-12 col-xs-8">
       						   <input type="password" class="form-control" id="inputname" name="password" placeholder=" Enter New Password " required>
    					    </div>
   					 </div>
   					 <div class="col-md-12 col-sm-12 col-xs-12"></div>
   					 <div class="form-group">
       					 <label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">Confirm Password </label>
       						<div class="col-md-7 col-sm-12 col-xs-8">
       						   <input type="password" class="form-control" id="inputname1" name="confirmPassword" placeholder=" Enter Confirm Password" required>
    					    </div>
   					 </div>
   					 
   					 <div class="col-md-12 col-sm-12 col-xs-12"><br></div>
   					  
   					 
   					 <div class="form-group">
   					 <div class="col-md-offset-2 col-md-10 col-sm-12 col-xs-12">	
	            <input type="submit" value="SUBMIT" class="btn btn-primary" id="submit">
	            </div>
	            
					 </div>
					 
					  <div class="col-md-12 col-sm-12 col-xs-12"><br></div>
					 
				</form>
           
            </div>
        </div> 
         </div>
        </center>
        
      </section><br> <br> <br> <br> <br> <br>
 <div id="footer">
         <center>Copyright © 2017 SangamOne Connected Services Pvt Ltd . All Rights Reserved.</center> 
		</div>
</body>
</html>
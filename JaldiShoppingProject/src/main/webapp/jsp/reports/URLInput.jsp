<%-- <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 --%> <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<jsp:include page="../common/header.jsp"></jsp:include>
<link rel='stylesheet prefetch' href='<%=request.getContextPath()%>/resources/css/styles.css'>
	
<div id="header"> 
        
        </div>
<div style="padding-top: 60px" >

</div>


<body>
<div class="container">

	<div class="row" id="pwd-container">
		<div class="col-md-4">&nbsp;</div>
		<div class="col-md-4">
        
     <div class="row">
         <h3><center>Please Enter Your Details</center></h3>
         <br>
         <br>
        <div class="col-lg-12">
          <form class="form-horizontal" action="<%=request.getContextPath()%>/admin/getURLInputDetails" method="POST" >
              
              
              
              
					
				  <div class="form-group">
       			<label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">Item Id</label>
       						 <div class="col-md-7 col-sm-12 col-xs-8">
       		   <input type="number" class="form-control" id="inputname" name="productId" placeholder=" Enter item Id" required>
    					    </div>
              
     <br><br>         
     
     	
				  <div class="form-group">
       			<label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">Category Id</label>
       						 <div class="col-md-7 col-sm-12 col-xs-8">
       		   <input type="number" class="form-control" id="inputname" name="categoryId" placeholder=" Enter category Id" required>
    					    </div>
    					    
    					     <br><br>	
				  <div class="form-group">
       			<label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">Vendor Id</label>
       						 <div class="col-md-7 col-sm-12 col-xs-8">
       		   <input type="number" class="form-control" id="inputname" name="vendorId" placeholder=" Enter vendor Id" required>
    					    </div>
    					     <br><br>
    					    	
				  <div class="form-group">
       			<label for="inputname" class="control-label col-sm-12 col-md-5 col-xs-4">Location Id</label>
       						 <div class="col-md-7 col-sm-12 col-xs-8">
       		   <input type="number" class="form-control" id="inputname" name="locationId" placeholder=" Enter Item Id" required>
    					    </div>
              <br>
              <br>
              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-default">Submit</button>
                  
                  
                  <!-- <button type="reset" class="btn btn-default">Re-Set</button> -->
                </div>
              </div>
          </form>
       </div>
        <div class="col-lg-2">
        </div>
      </div>
      		
		</div>			
		<div class="col-md-4">&nbsp;</div>
	</div>
</div>
<div id="footer">
         <center>Copyright © 2017 SangamOne Connected Services Pvt Ltd . All Rights Reserved.</center> 
		</div>
</body>
</html>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page session="true"%>
<html lang=''>
<head>
<meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styles.css">
   
</head></html>
 
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
/*    .row.content {height: 450px} */

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}


#myNavbar ul { padding: 5px;
}
#myNavbar li {
    display: inline;
    position: relative;
    white-space: nowrap;
}
#myNavbar ul ul {
    display: none;
    position: absolute;
    top: 25px;
    left: 0
    
}
#myNavbar li:hover ul { display: inline }
#myNavbar li:hover ul ul { display: none }
#myNavbar li li {
    float:left;
    clear: left;
}

#myNavbar ul ul li[class]{
width:150px;
background-color:blue;
height:25px;
margin-top:12px;
margin-left:18px;
border-radius:10px;

text-align:center;

}

</style>
</head>
<body>

 <div id='cssmenu'>
	<ul class="nav navbar-nav navbar-right">	
	<sec:authorize access="hasRole('ROLE_QUIZ_ADMIN')">
	
	
<li class='last'><a href="<%=request.getContextPath()%>/admin/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
	<%-- <li class='last'><a href="<%=request.getContextPath()%>/admin/changePassword"><span class="glyphicon glyphicon-lock"></span> Change Password</a></li>
	 --%><li class='last'><a href="<%=request.getContextPath()%>/admin/logout"><span	class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	
	
	
	
		</sec:authorize>
</ul>
</div>


	

		<!-- <li class='active has-sub'><a href="#"></span> Upload</a> -->
		<br>
		<nav class="navbar navbar1 navbar-inverse">
		<div class="container-fluid">
		<div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- <a class="navbar-brand" href="#">ATM Bridge</a> -->
    </div>
    
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		 <ul class="nav navbar-nav">
		<sec:authorize access="hasRole('ROLE_SUPER_ADMIN')">
		
		</li>
		<!-- <li class='active has-sub'><a href="#"></span> Reports</a>	 -->
		<%-- <li class="dropdown-toggle"> <a href='#' class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="glyphicon glyphicon-tasks"></span> Report </a>							
		<ul class="dropdown-menu">
		
		        <li><a href="<%=request.getContextPath() %>/admin/reportByKeyword"></span> Report By Keyword </a></li>                
               <li><a href="<%=request.getContextPath() %>/admin/payBills"></span> report </a></li>                
               
               
                
        </ul>								
		</li> --%>
					
			
		
	<li class='last'><a href="<%=request.getContextPath()%>/admin/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
	<li class='last'><a href="<%=request.getContextPath()%>/admin/logout"><span	class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	
	</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		
		</li>
		<!-- <li class='active has-sub'><a href="#"></span> Reports</a>	 -->
		<%-- <li class="dropdown-toggle"> <a href='#' class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <span class="glyphicon glyphicon-tasks"></span> Report </a>							
		<ul class="dropdown-menu">
		
		        <li><a href="<%=request.getContextPath() %>/admin/reportByKeyword"></span> Report By Keyword </a></li>                
               <li><a href="<%=request.getContextPath() %>/admin/payBills"></span> report </a></li>                
               
               
                
        </ul>								
		</li> --%>
				
	<li class='last'><a href="<%=request.getContextPath()%>/admin/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
	<li class='last'><a href="<%=request.getContextPath()%>/admin/getURLInput"><span class="glyphicon glyphicon-task"></span> URLInput</a></li>
	<li class='last'><a href="<%=request.getContextPath()%>/admin/logout"><span	class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	
	</sec:authorize>
		
</ul>
</div>
	</div>
		</nav>
	

</div>
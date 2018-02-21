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
	margin-bottom: 2;
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

	
		</ul>	</nav>	

</div>
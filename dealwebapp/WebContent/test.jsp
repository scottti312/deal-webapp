<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,java.io.*, com.java.deal.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
}
body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

section {
  display: -webkit-flex;
  display: flex;
  flex-shrink: 1;
}
logo {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  padding-left: 20px;
}
search {
  -webkit-flex: 8;
  -ms-flex: 8;
  flex: 8;
  padding: 50px 0px 0px 0px;
  text-align: center
}
login {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  padding-right: 25px;
  padding-top: 20px;
  text-align: right;
}

@media (max-width: 600px) {
  section {
    -webkit-flex-direction: column;
    flex-direction: column;
  }
}

nav {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  background-color: #95ffe0;
  border-style: solid hidden;
}
box {
  float: left;
  display: block;
  color: black;
  text-align: center;
  padding: 12px 20px;
  text-decoration: none;
  font-size: 17px;
}
divider {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  border-style: hidden hidden solid hidden;
}
header {
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;
  background-color: #DEDEDE;
  border: none;
  font-size: 25px;
  padding: 3px 0px 2px 15px;
}

</style>
</head>

<body style="background-color:white;">

<section>
  <logo>
   <p style="text-align:left;"><img src="Deal!.png" alt="Deal! logo" height=100px width=95px></p>
  </logo>
 <search>
   <form  action="ProductController" method="get" id="form"> 
    <input type="text" id="query" name="searchProductName"  placeholder="Search..." size=60 />
    <input type="submit" value="submit" />
   </form>
 </search>
  <login>
   <p><a href="capstone_login_page.htm">Login</a></p>
   <p><a href="capstone_register_page.htm">Register</a></p>
  </login>
</section>
<section> 
 <nav>
  <box>
   <a class="active" href="capstone_main_page.htm">Home</a>
  </box>
  <box>
   <a href="#sales">Sales</a>
  </box>
  <box>
   <a href="#saved_products">Saved Products</a>
  </box>
 </nav>
</section>
<section><divider><p></p></divider></section>
<section>
<header>Home</header>

</section>
</header>

<div>
<c:forTokens items="${products}" delims=";" var="keyword">
<div style="height:100px">
<c:forTokens items="${keyword}" delims="," var="element" varStatus="status">
<c:if test="${status.count eq 1 }">
<div><td>${element}</td></div>
</c:if>
<c:if test="${status.count ne 1 }">
<td>${element}</td>
</c:if>

</c:forTokens>
</div>
</c:forTokens>
</div>
</body>
</html>
<%-- 
    Document   : header
    Created on : Nov 25, 2018, 7:47:08 AM
    Author     : ewawuye
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Navbar-->
    <header class="app-header"><a class="app-header__logo" href="index.html">AMS</a>
      <!-- Sidebar toggle button--><a class="app-sidebar__toggle" href="#" data-toggle="sidebar" aria-label="Hide Sidebar"></a>
      <!-- User Menu-->
        <ul class="app-nav">
        <li class="dropdown"><a class="app-nav__item" href="#" data-toggle="dropdown" aria-label="Open Profile Menu"><i class=""> <f:form action="${pageContext.request.contextPath}/logout" method="POST">
          <input type="submit" value="Logout"/>
      </f:form></i></a>
            <span style="float: right; color:white">
	<a  class ="text-danger" href="?lang=en">en</a> 
	| 
	<a class ="text-danger" href="?lang=am">am</a>
  </span>
        </li>
        </ul>
    </header>

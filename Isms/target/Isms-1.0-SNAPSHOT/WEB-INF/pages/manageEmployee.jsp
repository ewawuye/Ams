<%-- 
    Document   : adminmaster
    Created on : May 9, 2019, 11:26:19 AM
    Author     : ewawuye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
        <!-- Twitter meta-->
        <meta property="twitter:card" content="summary_large_image">
        <meta property="twitter:site" content="@pratikborsadiya">
        <meta property="twitter:creator" content="@pratikborsadiya">
        <!-- Open Graph Meta-->
        <meta property="og:type" content="website">
        <meta property="og:site_name" content="Vali Admin">
        <meta property="og:title" content="Vali - Free Bootstrap 4 admin theme">
        <meta property="og:url" content="http://pratikborsadiya.in/blog/vali-admin">
        <meta property="og:image" content="http://pratikborsadiya.in/blog/vali-admin/hero-social.png">
        <meta property="og:description" content="Vali is a responsive and free admin theme built with Bootstrap 4, SASS and PUG.js. It's fully customizable and modular.">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Asset management- ${title}</title>
        <!-- Main CSS  -->
        <s:url var ="mainurl" value ="/resource/css/main.css"/>
        <link href="${mainurl}" rel="stylesheet" type="text/css"> 

        <!-- Custom CSS -->
        <s:url var ="customurl" value ="/resource/css/custom"/>
        <link href="${customurl}" rel="stylesheet" type="text/css"/> 

        <!-- font awasome -->
        <s:url var ="fonturl" value ="/resource/css/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link href= "${fonturl}" rel="stylesheet" type="text/css"/> 

        <!-- Font-icon css-->
    </head>
    <body class="app sidebar-mini rtl">


        <!-- Navigation -->
        <%@include file="./shared/headerNav.jsp"%>
        <!-- Side Navigation -->
        <%@include file="./shared/sideNav.jsp"%>


        <main class="app-content">

            <div class="row">
                <div class="col-md-12">



                    <div class="panel panel-default">

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->






                            <ul class="nav navbar-light bg-light nav-tabs">
                                   <s:url var ="employeeurl" value ="/employee/add"/> 
                                <li class =" nav-item "><a  class =" nav-link mr-3" href="${employeeurl}">Add Employee </a>

                                   <s:url var ="empMnageUrl" value ="/empManage"/>

                                <li class="nav-item" ><a class =" nav-link" href="${empMnageUrl}">Manage Employee</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content mt-5">
                                <div class="tab-pane tab-pane fade show active" id="home-pills">




                                    <!-- search user -->
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="tile">
                                                <h4 class="tile-title">Search  Employee and Add Item</h4>
                                         <s:url  var ="asignAssetToEmp" value="/asset/addAssetToEmployee"/>
                                        <div class="tile-body">
                                            ${assigndMessage}
                                            <form action ="${asignAssetToEmp}"  method="POST">
                                                <div class="form-row">

              
 
                                                       
                                                   
                                                    <div class="form-group col-md-3">

                                                        <input  type="text" name ="assetCode" class="form-control"  placeholder="Enter asset code"/>
                                                        
                                                    </div>
                                                    <div class="form-group col-md-3">
                                                         <input name="employeeId" type="text" value="${employeeId}"  class="form-control"/>
                                                    </div>
                              
                                                </div>
                                                
                                                    
                                                        <div class="form-group purple-border">
                                                            <button class=" ml-5 btn btn-primary"  type="submit"><i class=" "></i>Save</button>
                                                        </div>
                                                 </form>
                                               </div>

                                </div>
                                            
                                                
                                                
                                                
                             <%-- asset search start --%>
                                     <div class="tile">
                                        <h4 class="tile-title">Search employee </h4>
                                        <div class="tile-body"> 
                                            <s:url var ="employeeSearchUrl"  value="/employee_search"/>
                                            <form action ="employee_search" method="post">
                                                <div class="form-row">

                                                    <div class="form-group col-md-3">
 
                                                        <input  type="text" name ="searchText" value="${param.searchText}" class="form-control"  placeholder="search"/>
                                                    </div>
                                                 ${emp}
                                                </div>
                                                
                                                    
                                                        <div class="form-group purple-border">
                                                            <button class=" ml-5 btn btn-primary"  type="submit"><i class=" "></i>Search</button>
                                                        </div>
                                               
                                                   
                                            </form>

   
                                  
                                        </div>
                                </div>
                                                    
                                                <div class="tile">
                                                    <div class="tile-body">


                                                        <table class="table table-hover table-bordered" id="sampleTable">
                                                            <thead>
                                                                <tr>

                                                                    <th>First Name</th>
                                                                    <th> Middle Name</th>
                                                                    <th> Last Name</th>
                                                                    <th>  Email</th>
                                                                     <th> age</th>
                                                                  
                                                                     <th> Employee code</th>
                                                                   
                                                                </tr>
                                                            </thead>

                                                            <tbody> 
                                                               
                                                         
                                                              
                                                                <tr>
                                                                    <td>${emps.firstName}</td>
                                                                     <td>${emps.middleName}</td>
                                                                      <td>${emps.lastName}</td>
                                                                      <td>${emps.email}</td>
                                                                      <td>${emps.age}</td>
                                                                      <td>${emps.code}</td>
                                        <s:url var="assignAssetUrl" value="/asset/assignAssetPage" >
                                       <s:param name="employeeId" value="${emps.employeeId}"/>
                                        </s:url>
                                        <s:url var ="assignempassetlist" value="/employee/getAssetWithemp">
                                            <s:param name ="employeeId" value="${emps.employeeId}"/>
                                        </s:url>
                                   <td> 
                                       <a  class ="nav-item" href="${url_edit}">Edit</a> | <a class ="nav-item" href="${assignempassetlist}">show assignment</a>| <a href="${assignAssetUrl}">Assign item</a>
                                   </td>
                                                                </tr>
                                                                     
                                                             
                                                            </tbody>
                                                        </table>

                                                    </div>
                                                </div> 
                                                 

                                                </div>
                                            </div>

                                        </div>

                                    </div>

                                </div>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->

                    </div>
                </div>

            </div>


        </main>



        <s:url var = "jqueryurl" value ="/resource/js/jquery-3.2.1.min.js"/>
        <script src="${jqueryurl}" type = "text/javascript"></script>
        <s:url var="urlpopper" value="/resource/js/popper.min.js" />
        <script src="${urlpopper}"></script>
        <s:url var="urlboot" value="/resource/js/bootstrap.min.js" />
        <script src="${urlboot}"></script>
        <s:url var="urlmain" value="/resource/js/main.js" />
        <script src="${urlmain}"></script>
        <!-- The javascript plugin to display page loading on top-->
        <s:url var="urlpace" value="/resource/js/plugin/pace.min.js" />
        <script src="${urlpace}"></script>
        <s:url var="urljqlib" value="/resource/js/jquery-3.2.1.min.js" />
        <script src="${urljqlib}"></script>
        <!-- Page specific javascripts-->
        <s:url var="urlchart" value="/resource/js/plugin/chart.js" />
        <script type="text/javascript" src="${urlchart}"></script>
        <!-- Data table plugin-->
        <s:url var="urltable" value="/resource/js/plugin/jquery.dataTables.min.js" />
        <s:url var="urlboottable" value="/resource/js/plugin/dataTables.bootstrap.min.js" />
        <s:url var="urlcalendar" value="/resource/js/plugin/fullcalendar.min.js" />
        <script type="text/javascript" src="${urltable}"></script>
        <script type="text/javascript" src="${urlboottable}"></script>
        <script type="text/javascript" src="${urlcalendar}"></script>
        <script type="text/javascript">$('#sampleTable').DataTable();</script>


    </body>
</html>



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
        <s:url var ="customurl" value ="/resource/css/sb-admin-2.css"/>
        <link href="${customurl}" rel="stylesheet" type="text/css">

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
                            <s:url var ="assetregurl" value ="/asset/asset_reg"/>
                            <s:url var ="assetWarrantyurl" value ="/warranty/warranty_page"/>
                            <s:url var ="financeInfoUrl" value ="/financeInfo/financeInfo_page"/>
                            <s:url var ="assetWarrantypageurl" value ="/warranty/assetWarrantyPage"/>
                            <ul class="nav ">
                                <li class =" nav-item "><a  class ="nav-link active" href="${assetregurl}" >Add Asset </a>
                                </li>
                                <li class="nav-item" ><a class ="nav-link" href=" ${assetWarrantyurl}"  >Add Warranty</a>
                                    
                                </li>
                                <li class=" nav-item" > <a class ="nav-link" href="${assetWarrantypageurl}" >Manage Warranty</a>
                                </li>
                                <li class=" nav-item" > <a class ="nav-link" href="${financeInfoUrl}" >Finance  Information</a>
                                </li>
                                      <s:url var ="EmpassetAssignmenturl" value ="/asset/search_empPage"/>
                                <li class=" nav-item mr-auto" > <a class ="nav-link" href="${EmpassetAssignmenturl}" >Employee Assets</a>
                                </li>
                                
                            </ul>






                            <!-- search user -->
                            <div class="row">
                                <div class="col-md-12">
                                 
                                     <%-- asset search start --%>
                                     <div class="tile">
                                        <h4 class="tile-title">Search asset </h4>
                                        <div class="tile-body"> 
                                            <s:url var ="assetwithwarrantySearchUrl"  value="/warranty/sssetWithWarranty"/>
                                            <form action ="${assetwithwarrantySearchUrl}" method ="get">
                                                <div class="form-row">

                                                    <div class="form-group col-md-3">
 
                                                        <input  type="text" name ="assetCode" value="${param.assetCode}" class="form-control"  placeholder="search"/>
                                                    </div>
                                                 
                                                </div>
                                                
                                                    
                                                        <div class="form-group purple-border">
                                                            <button class=" ml-5 btn btn-primary"  type="submit"><i class=" "></i>Search</button>
                                                        </div>
                                               
                                                   
                                            </form>
                                                        <c:if test ="${message != null}">
                                                            <h1 class = "text-success">      ${message}</h1>
                                                            
                                                        </c:if>
   
                                     
                                     
                                  </div>
         <div class="row">
        <div class="col-md-12">
                   <%-- table body--%>
                       <div class="tile">
                <div class="tile-body">
                 
                  
                    <table class="table table-hover table-bordered">
                        <thead>
                    
                            <tr>
                                
                             
                                <th> Asset Code </th>
                        
                                 <th> Warranty Name</th>
                                 <th>Start Date</th>
                                 <th>  End Date</th>
                                 <th> Document</th>

                                     <th> Action</th>
                            </tr>
                        </thead>

                        <tbody>
                                  <c:forEach items ="${assetWarranty.warranty.documents}" var  = "document">
                                <tr>
                                    
                                      <td>${assetWarranty.assetCode}</td>
                                   
                                    <td>${assetWarranty.warranty.warrantyName}</td>
                                    <td>${assetWarranty.warranty.startDate}</td>
                                    <td>${assetWarranty.warranty.endDate}</td>
                                     <td>${document.name}</td>                                          
                                          
                                   
                                  
                               
                                   <s:url var="viewocumentUrl" value="/document/downLoadDocument">
                                       <s:param name="docId" value="${document.documentId}"/>
                                   </s:url>
                                   <td> 
                                       <a href="${viewocumentUrl}">View document</a>
                                   </td>
                                </tr>
                 
</c:forEach>
                                
                                   
                        </tbody>
                    </table>
                  
                </div>
            </div>  <%-- table end --%>
        </div>
    
         </div>                    
                                </div> <%-- asset search end %-->
                              <%-- table body--%>
                       <div class="tile">
                <div class="tile-body">
                 
                  
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                
                                <th>Asset Name</th>
                                <th> Asset Code /Serial number</th>
                                 <th> Brand</th>
                                 <th> Model</th>
                                 <th> Current condition</th>
                                 <th> quality</th>
                                
                            </tr>
                        </thead>

                        <tbody>
                          
                                <tr>
                                     <td>${assetWarranty.assetName}</td>
                                      <td>${assetWarranty.assetCode}</td>
                                    <td>${assetWarranty.brand}</td>
                                    <td>${assetWarranty.qualityCondition}</td>
                                    <td>${assetWarranty.currentCondition}</td>
                                    <td>${assetWarranty.qualityCondition}</td>
                                    <td>${assetWarranty.warranty.warrantyName}</td>
                                    <td>${assetWarranty.warranty.startDate}</td>
                                    <td>${assetWarranty.warranty.endDate}</td>
                                          
                                   
                                  
                                   <s:url var="url_edit" value="/user/edit_catagory">
                                        <s:param name="cid" value="${assetWarranty.assetId}"/>
                                    </s:url> 
                                   
                                    <s:url var="url_del" value="/deleteAsset">
                                        <s:param name="assetId" value="${assetWarranty.assetId}"/>
                                    </s:url> 
                                   <s:url var="adddocumentUrl" value="/document/add-documetpage">
                                       <s:param name="warrantyId" value="${assetWarranty.warranty.warrantyId}"/>
                                   </s:url>
                                   <td> 
                                       <a  class ="nav-item" href="${url_edit}">Edit</a> | <a class ="nav-item" href="${url_del}">Delete</a>| <a href="${adddocumentUrl}">Add warranty document</a>
                                   </td>
                                </tr>
                 

                                   
                        </tbody>
                    </table>
                  
                </div>
            </div>  <%-- table end --%>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->

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





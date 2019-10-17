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
                            <ul class="nav navbar bg-light">
                                 <s:url var ="manageasseturl" value ="/warranty/warranty_page"/>
                                    <s:url var="assetregurl" value ="/asset/asset_reg"/>
                                    <s:url var ="assetFinanceInfourl" value ="/financeInfo/financeInfo_page"/>
                                   <s:url var ="assetAssignmenturl" value ="/asset/assignPage"/>
                                     <s:url var ="AssignedAsseturl" value ="/asset/showAssignedAssets?pageNo=1"/>
                                
                                <li class =" nav-item"><a  class ="nav-link " href="${assetregurl}" >Add Asset</a>
                                </li>
                                <li class="nav-item" ><a class ="nav-link" href="${manageasseturl}"  >Add Warranty </a>
                                </li>
                                <li class=" nav-item" > <a class ="nav-link" href="${assetFinanceInfourl}" >Finance Information</a>
                                </li>
                                <li class=" nav-item " > <a class ="nav-link" href="${assetAssignmenturl}" >Assign Assets</a>
                                </li>
                                <li class=" nav-item " > <a class ="nav-link" href="${AssignedAsseturl}" >Show Assigned</a>
                                </li>
                                       <s:url var ="EmpassetAssignmenturl" value ="/asset/search_empPage"/>
                                <li class=" nav-item mr-auto" > <a class ="nav-link" href="${EmpassetAssignmenturl}" >Employee Assets</a>
                                </li>
                            </ul>

                           
                            

                              

                                    <!-- type of user -->
                                    <div class="row">

                                        <div class=" mt-3 col-md-6 col-lg-3">
                                            <div class="widget-small info coloured-icon"><i class="icon fa fa-star fa-4x"></i>
                                                <div class="info">
                                                    <h4>Assets</h4>
                                                    <p><b>500</b></p>
                                                    <a href="#">
                                                        <div class="panel-footer text-info">
                                                            <span class="pull-left">View Details</span>
                                                            <span class="pull-right"><i class="fa fa-arrow-circle-right "></i></span>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- search user -->
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="tile">
                                                <h4 class="tile-title">Register New Asset</h4>
                                                <div class="tile-body">
                                                    <s:url  var ="assetReg" value ="/asset/asset_registration"/>
                                                    <f:form action ="${assetReg}" modelAttribute="command">
                                                        <div class="form-row">
                                        
                                                            <div class="form-group col-md-3">
                                                                <f:errors path ="assetName" type ="text" class ="text-danger"/>
                                                                <f:input path ="assetName" type="text" class="form-control"  placeholder="Enter asset name"/>
                                                                
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <f:errors path ="assetCode" type ="text" class ="text-danger"/>
                                                                <f:input path ="assetCode" type="text" class="form-control"  placeholder="Enter asset code"/>
                                                             
                                                            </div>
                                                            <div class="form-group col-md-3">
                                                                <f:errors path ="brand" type ="text" class ="text-danger"/>
                                                                <f:input path ="brand" type="text" class="form-control"  placeholder="Enter asset brand"/>
                                                                
                                                            </div>
                                                            <div class="form-group col-md-3">

                                                                <f:input  path ="model" type="text" class="form-control"  placeholder="Enter asset model"/>
                                                            </div>
                                                        </div>
                                                     <div class="form-row">

                                                            <div class="form-group col-md-3">

                                                                <f:input path  ="price" type="text" class="form-control"  placeholder="Enter asset Price"/>
                                                            </div>
                                                          
                                                            <div class="form-group col-md-3">
                                                              <f:select   path ="currentCondition" type ="text" class ="form-control">
                                                                                    <f:option value="in store" label="in store"/>
                                                                                    <f:option value="in use" label="in use"/>
                                                                                    <f:option value="loaned out" label="loaned out"/>
                                                                                   <f:option value="out for repair" label="out for repair"/>


                                                                  </f:select>
                                                            </div>
                                                            <div class="form-group col-md-3">

                                                               <f:select   path ="qualityCondition" type ="text" class ="form-control">

                                                                                    <f:option value="new" label="new"/>
                                                                                    <f:option value="used" label="used"/>


                                                                  </f:select>
                                                            </div> 
                                                            <div class="form-group col-md-3">

                                                               <f:select path ="catagory.catagoryId"   type ="text" class ="form-control">

                                                                   <f:options items="${catagorylist}" itemLabel ="catagoryName" itemValue ="catagoryId"/>
                                                                                    


                                                               </f:select>
                                                            </div>
                                                        </div>
                                                          <div class="form-row">
                                                                <div class="form-group col-md-8 ">
    <div class="form-group purple-border">
  <label for="desription">Asset description</label>
  <f:textarea path ="description" class="form-control"  rows="3"></f:textarea>
</div> 
                                                                        </div> 
                                                                                    
                                                        <div class="form-group col-md-4 text-right">
    <div class="form-group purple-border">
                                                          <button class="mt-5 ml-5 btn btn-primary"  type="submit"><i class="fa fa-plus "></i>Save asset</button>
                                                        </div>
                                                               </div>  
                                                    </f:form>

                                                </div>
                                    

                                            </div>
                                        </div>
                                    </div>

                                 
                                </div>
                         
                           ${assetlist}
              
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


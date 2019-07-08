<%-- 配置 --%>
	<%
		String ip = "192.168.1.8"; 
	%> 
<%-- END --%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	<% 
		out.print(request.getParameter("title")); 
	%>
	</title>
	 <style>
        *{
            margin:0;
            padding:0;
        }
        body,head,html{
            width: 100%;
            height:100%;
        }
    </style>
</head>
	<body>
		<div id="placeholder"></div>
	    <script type="text/javascript" src="http://<%=ip%>:9000/5.3.0-243/web-apps/apps/api/documents/api.js"></script>
	    <script>
	        config = {
	            "document": {
		            "permissions": {
		                "edit": <%out.print(request.getParameter("edit"));%>,
		                "download": false,
		            },
	                "fileType": "xlsx",
	                "title": "<%out.print(request.getParameter("title"));%>",
	                "url": "http://<%=ip%>:8080/www/Excel_sample/<% out.print(request.getParameter("file_name")); %>.xlsx"
	            },
	            "documentType": "spreadsheet",
	            "editorConfig": {
	                "callbackUrl": "http://<%=ip%>:8080/OA/excel_callback?id=<%out.print(request.getParameter("id"));%>",
	                "lang": "zh-CN",
	                "customization":{
	                    "forcesave":true,
	             	}
	            },
	            "width":"100%",
	            "height":"100%",
	        };
	        var docEditor = new DocsAPI.DocEditor("placeholder", config);
	
	        var message;
	        function Socket(url){
	            var xmlhttp;
	            if (window.XMLHttpRequest)
	            {
	                xmlhttp=new XMLHttpRequest();
	            }
	            else
	            {
	                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	            }
	            xmlhttp.onreadystatechange=function()
	            {
	                if (xmlhttp.readyState==4 && xmlhttp.status==200)
	                {
	                    message = xmlhttp.responseText;
	                    console.log(message);
	                }
	            }
	            xmlhttp.open("GET",url,true);
	            xmlhttp.send();
	        }
	
	        function flag(){
	            if(message == 2){
	                Socket("http://<%=ip%>:8080/OA/excel_utlis?con=InitializeFlag&id=<%out.print(request.getParameter("id"));%>");
	                alert("Excel正在同步数据库！");
	            }else if(message == 1){
	                Socket("http://<%=ip%>:8080/OA/excel_utlis?con=Xlsx2SQL&id=<%out.print(request.getParameter("id"));%>");
	                alert("Excel 同步完成！");
	            }else{
	            	Socket("http://<%=ip%>:8080/OA/excel_utlis?con=GetFlag&id=<%out.print(request.getParameter("id"));%>");
	            }
	        }
	        setInterval(flag,300);
	    </script>
	</body>
</html>
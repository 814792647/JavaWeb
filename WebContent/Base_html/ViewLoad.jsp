<%-- 配置 --%>
	<%
		String ip = "192.168.1.8"; 
	%> 
<%-- END --%>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style>
		*{
			margin:0;
		}
		@keyframes mymove
        {
            0% {
                top:-300rem;
            }
            100% {
                top:3rem;
            }
        }
		#loading{
			background-color: #ffbb00;
			height: 5rem;
			width: 85%;
			position: absolute;
            top: 3rem;
            left: 0;
            right: 0;
            margin: auto;
            font-size: 35px;
            color: #fff;
            box-shadow: 0 1px 3px rgba(26, 26, 26,.1);
            box-sizing: border-box;
            border-radius:4px;
            text-align: center;
            line-height: 5rem;
            animation: 0.2s mymove;
		}
	</style>
</head>
<body>
	<div id="loading">
		<b>正在从二次元世界努力寻找你的信息，乖乖的不要刷新!</b>
    </div>
</body>
	
<script>
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
    Socket("http://<%=ip%>:8080/OA/excel_utlis?con=Xlsx2SQL&id=<%out.print(request.getParameter("id"));%>");
	function flag(){
        if(message == 0){
            Socket("http://<%=ip%>:8080/OA/excel_utlis?con=GetFlag&id=<%out.print(request.getParameter("id"));%>");
        }else if(message == 2){
        	Socket("http://<%=ip%>:8080/OA/excel_utlis?con=InitializeFlag&id=<%out.print(request.getParameter("id"));%>");
        	window.location.href="<%out.print(request.getParameter("to"));%>";
        }
    }
    setInterval(flag,300);
</script>
</html>
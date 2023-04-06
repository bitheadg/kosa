<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello </title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
<script type="text/javascript">
$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('international?lang=' + selectedOption);
        }
    });
});
</script>
<body>
    <h2 class="hello-title">Hello <%=request.getContextPath()%></h2>
    <br>
    using thymeleaf
    <h1 th:text="#{greeting}"></h1>
    using JSP
    <h1><spring:message code="greeting" text="default"/></h1>
    
    <span th:text="#{lang.change}"></span>:
	<select id="locales">
	    <option value=""></option>
	    <option value="en" th:text="#{lang.eng}"></option>
	    <option value="出" th:text="#{lang.ch}"></option>
	</select>
    
    
    <a href="http://127.0.0.1:8080<%=request.getContextPath()%>/get">Server Time</a>
    <form action="http://127.0.0.1:8080<%=request.getContextPath()%>/read" method="get">
  	 <input type="submit" value="Send"/>
    </form>
    
</body>
</html>
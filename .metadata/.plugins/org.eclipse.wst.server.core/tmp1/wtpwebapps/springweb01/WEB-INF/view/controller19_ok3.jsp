<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>
<body>
<h1>페이지 3</h1>
<form action="ok4.do" method="post">
이름: ${member.name}<br>
나이: ${member.age}<br>
직장: <input type="radio" name="working" value="true">재직중
      <input type="radio" name="working" value="false" checked>실업자<br>
<button>다음</button>

</form>
</body>
</html>
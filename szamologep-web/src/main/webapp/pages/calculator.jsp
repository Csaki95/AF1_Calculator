<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/header.jsp"/>
    <title>Calculator</title>
</head>
<body>
    <jsp:include page="common/menu.jsp"/>
    <jsp:include page="/Calculator"/>
    <div class="container p-3 border mx-auto">
        <form name="calculator" class="form-group" action="../Calculator" method="post">
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num1" value="${number1}" tabindex="1" readonly>
                <input type="submit" name="select1" value="<" class="btn btn-secondary">
            </div>
            <span class="align-middle">${sign}</span>
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num2" value="${number2}" tabindex="2" readonly>
                <input type="submit" name="select2" value="<" class="btn btn-secondary">
            </div>
            <br>
            <table class="mx-auto">
                <tr>
                    <td><input type="submit" name="7" class="btn btn-primary btn-block" value="7"/> </td>
                    <td><input type="submit" name="8" class="btn btn-primary btn-block" value="8"/> </td>
                    <td><input type="submit" name="9" class="btn btn-primary btn-block" value="9"/> </td>
                    <td><input type="submit" name="sin" class="btn btn-primary btn-block" value="sin"/> </td>
                    <td><input type="submit" name="+" class="btn btn-primary btn-block" value="+"/> </td>
                    <td><input type="submit" name="%" class="btn btn-primary btn-block" value="%"/> </td>
                    <td><input type="submit" name="M+" class="btn btn-primary btn-block" value="M+"/> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="4" class="btn btn-primary btn-block" value="4"/> </td>
                    <td><input type="submit" name="5" class="btn btn-primary btn-block" value="5"/> </td>
                    <td><input type="submit" name="6" class="btn btn-primary btn-block" value="6"/> </td>
                    <td><input type="submit" name="cos" class="btn btn-primary btn-block" value="cos"/> </td>
                    <td><input type="submit" name="-" class="btn btn-primary btn-block" value="-"/> </td>
                    <td><input type="submit" name="square" class="btn btn-primary btn-block" value="x²"/> </td>
                    <td><input type="submit" name="M" class="btn btn-primary btn-block" value="M"/> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="1" class="btn btn-primary btn-block" value="1"/> </td>
                    <td><input type="submit" name="2" class="btn btn-primary btn-block" value="2"/> </td>
                    <td><input type="submit" name="3" class="btn btn-primary btn-block" value="3"/> </td>
                    <td><input type="submit" name="tan" class="btn btn-primary btn-block" value="tan"/> </td>
                    <td><input type="submit" name="*" class="btn btn-primary btn-block" value="*"/> </td>
                    <td><input type="submit" name="squareR" class="btn btn-primary btn-block" value="√x"/> </td>
                    <td><input type="submit" name="m+" class="btn btn-primary btn-block" value="m+"/> </td>
                </tr>
                <tr>
                    <td><input type="submit" name="sign" class="btn btn-primary btn-block" value="+/-"/> </td>
                    <td><input type="submit" name="0" class="btn btn-primary btn-block" value="0"/> </td>
                    <td><input type="submit" name="." class="btn btn-primary btn-block" value="."/> </td>
                    <td><input type="submit" name="del" class="btn btn-success btn-block" value="DEL"/> </td>
                    <td><input type="submit" name="/" class="btn btn-primary btn-block" value="/"/> </td>
                    <td><input type="submit" name="rec" class="btn btn-primary btn-block" value="rec"/> </td>
                    <td><input type="submit" name="m" class="btn btn-primary btn-block" value="m"/> </td>
                </tr>
            </table>
            <div class="row justify-content-center">
                <input type="submit" name="=" class="btn btn-success btn-block" value="="/>
            </div>
        </form>
    </div>
</body>
</html>

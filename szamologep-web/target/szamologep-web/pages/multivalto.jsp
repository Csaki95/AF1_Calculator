<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/header.jsp"/>
    <title>Valto</title>
</head>
<body>
    <jsp:include page="common/menu.jsp"/>
    <jsp:include page="/ValutaServlet"/>
    <jsp:include page="/SzamrendszerServlet"/>
    <jsp:include page="/DegreeServlet"/>
    <jsp:include page="/SulyServlet"/>

    <div class="container">
        <form action="../SzamrendszerServlet" method="post">
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num1" value="${number}" tabindex="1">
                <select class="browser-default custom-select" name="from">
                    <option value="" selected disabled hidden>Miről</option>
                    <option value="hex">hex</option>
                    <option value="dec">dec</option>
                    <option value="bin">bin</option>
                    <option value="oct">oct</option>
                </select>
                <select class="browser-default custom-select" name="to">
                    <option value="" selected disabled hidden>Mire</option>
                    <option value="hex">hex</option>
                    <option value="dec">dec</option>
                    <option value="bin">bin</option>
                    <option value="oct">oct</option>
                </select>
                <button id="submit" type="submit" class="btn btn-primary" tabindex="2">Submit</button>
            </div>
        </form>
    </div>
    <div class="container">
        <form action="../ValutaServlet" method="post">
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num1" value="${money}" tabindex="3">
                <select class="browser-default custom-select" name="from">
                    <option value="toHuf">to Huf</option>
                    <option value="toEur">to Eur</option>
                </select>
                <button type="submit" class="btn btn-primary" tabindex="4">Submit</button>
            </div>
        </form>
    </div>
    <div class="container">
        <form action="../DegreeServlet" method="post">
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num1" value="${degree}" tabindex="5">
                <select class="browser-default custom-select" name="to">
                    <option value="toF">to F</option>
                    <option value="toC">to C°</option>
                </select>
                <button type="submit" class="btn btn-primary" tabindex="6">Submit</button>
            </div>
        </form>
    </div>
    <div class="container">
        <form action="../SulyServlet" method="post">
            <div class="form-inline">
                <input type="text" class="row form-control p-2 mx-auto" name="num1" value="${weight}" tabindex="7">
                <select class="browser-default custom-select" name="from">
                    <option value="mg">mg</option>
                    <option value="g">g</option>
                    <option value="dkg">dkg</option>
                    <option value="kg">kg</option>
                    <option value="t">t</option>
                </select>
                <select class="browser-default custom-select" name="to">
                    <option value="mg">mg</option>
                    <option value="g">g</option>
                    <option value="dkg">dkg</option>
                    <option value="kg">kg</option>
                    <option value="t">t</option>
                </select>
                <button type="submit" class="btn btn-primary" tabindex="8">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>

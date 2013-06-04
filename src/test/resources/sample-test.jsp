<%-- 
The MIT License (MIT)

Copyright (c) 2013 Six Dimensions

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
--%>
<%@page session="false" contentType="text/html; charset=utf-8"%>
<%@page import="com.sixdimensions.wcm.cq.sampleintegration.HelloResourceService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<sling:defineObjects/>
<%
HelloResourceService helloSvc = sling.getService(HelloResourceService.class);
pageContext.setAttribute("helloTxt",helloSvc.sayHello(resource));
%>
<!-- Some Testing here -->
<c:choose>
    <c:when test="${helloTxt == 'Hello sample-test!'}">
        All Tests Succeeded
    </c:when>
    <c:otherwise>
        Test Failures
    </c:otherwise>
</c:choose>
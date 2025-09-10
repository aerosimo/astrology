<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ This piece of work is to enhance astrology project functionality.        ~
  ~                                                                          ~
  ~ Author:    eomisore                                                      ~
  ~ File:      index.jsp                                                     ~
  ~ Created:   29/03/2025, 23:34                                             ~
  ~ Modified:  30/03/2025, 23:56                                             ~
  ~                                                                          ~
  ~ Copyright (c)  2025.  Aerosimo Ltd                                       ~
  ~                                                                          ~
  ~ Permission is hereby granted, free of charge, to any person obtaining a
  ~ copy of this software and associated documentation files (the "Software"),
  ~ to deal in the Software without restriction, including without limitation
  ~ the rights to use, copy, modify, merge, publish, distribute, sublicense,
  ~ and/or sell copies of the Software, and to permit persons to whom the
  ~ Software is furnished to do so, subject to the following conditions:     ~
  ~                                                                          ~
  ~ The above copyright notice and this permission notice shall be included
  ~ in all copies or substantial portions of the Software.
  ~
  ~ THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
  ~ EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
  ~ OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
  ~ NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
  ~ HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
  ~ WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
  ~ FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
  ~ OR OTHER DEALINGS IN THE SOFTWARE.
  ~                                                                          ~
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="Elijah Omisore" name="author">
    <meta content="Aerosimo 1.0.0" name="generator">
    <meta content="Aerosimo" name="application-name">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="Aerosimo IT Consultancy" name="description">
    <meta content="Aerosimo" name="apple-mobile-web-app-title">
    <meta content="Oracle, Java, Tomcat, Maven, Jenkins, Bitbucket, Github" name="keywords">
    <meta content="width=device-width, initial-scale=1, user-scalable=no" name="viewport" />
    <!-- Title -->
    <title>Astrology System (AS) | Aerosimo Ltd</title>
    <!-- Favicon-->
    <link href="assets/img/favicon.ico" rel="shortcut icon"/>
    <link href="assets/img/favicon.ico" rel="icon" type="image/x-icon">
    <link href="assets/img/favicon-32x32.png" rel="icon" sizes="32x32" type="image/png">
    <link href="assets/img/favicon-16x16.png" rel="icon" sizes="16x16" type="image/png">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon" sizes="180x180">
    <link href="assets/img/android-chrome-192x192.png" rel="android-chrome" sizes="192x192">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #0072c6;
        }

        p {
            font-size: 16px;
            line-height: 1.5;
        }

        a {
            text-decoration: none;
            color: #0072c6;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Ominet Astrology System (AS)</h1>
    <p>Astrology is a range of divinatory practices, recognized as pseudo-scientific since the 18th century,
        that propose that information about human affairs and terrestrial events may be discerned by studying the
        apparent positions of celestial objects..</p>
    <c:set var="baseUrl"
           value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
    <p>
        To access the web services, please click here: <a href="${baseUrl}/ws/astrology">here</a>
    </p>
    <p>
        The current timestamp is:
        <%=new java.util.Date()%>
    </p>
</body>
</html>
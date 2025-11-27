<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  ~ This piece of work is to enhance astrology project functionality.        ~
  ~                                                                          ~
  ~ Author:    eomisore                                                      ~
  ~ File:      index.jsp                                                     ~
  ~ Created:   09/10/2025, 16:32                                             ~
  ~ Modified:  09/10/2025, 16:32                                             ~
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
<html lang="en">
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
    <title>Astrology | Aerosimo Ltd</title>
    <!-- Favicon-->
    <link href="assets/img/favicon.ico" rel="shortcut icon"/>
    <link href="assets/img/favicon.ico" rel="icon" type="image/x-icon">
    <link href="assets/img/favicon-32x32.png" rel="icon" sizes="32x32" type="image/png">
    <link href="assets/img/favicon-16x16.png" rel="icon" sizes="16x16" type="image/png">
    <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon" sizes="180x180">
    <link href="assets/img/android-chrome-192x192.png" rel="android-chrome" sizes="192x192">
    <style>
        body {
            font-family: 'Outfit', Arial, sans-serif;
            background: #f6f7fb;
            margin: 0;
            padding: 0;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
        }
        h1 {
            color: #4d3b7a;
            font-size: 1.8rem;
            text-align: center;
            margin-bottom: 10px;
        }
        h2 {
            color: #555;
            border-bottom: 2px solid #eee;
            padding-bottom: 5px;
            margin-top: 25px;
        }
        p {
            line-height: 1.6;
        }
        a {
            text-decoration: none;
            color: #4d3b7a;
            font-weight: 600;
        }
        a:hover {
            text-decoration: underline;
        }
        .endpoint-box {
            background: #f9f9ff;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            margin-top: 10px;
        }
        .endpoint-title {
            font-weight: 600;
            color: #333;
        }
        .endpoint-url {
            display: block;
            margin-top: 5px;
            word-wrap: break-word;
            color: #0072c6;
        }
        .note {
            font-size: 0.9rem;
            color: #777;
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome to Ominet Astrology System (AS)</h1>
    <p>
        The <b>Astrology System</b> provides access to both <b>SOAP</b> and <b>REST</b> endpoints
        for celestial insights and horoscope retrieval. These services allow external applications
        to integrate daily zodiac updates and astrological forecasts from Aerosimo’s Ominet platform.
    </p>

    <c:set var="baseUrl"
           value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />

    <!-- REST Endpoint Section -->
    <h2>🌙 REST API Service</h2>
    <div class="endpoint-box">
        <p class="endpoint-title">Daily Horoscope Update Endpoint</p>
        <a class="endpoint-url" href="${baseUrl}/api/astrology/horoscope" target="_blank">
            ${baseUrl}/api/astrology/horoscope
        </a>
        <p class="text-muted">Method: <b>POST</b> — Initiates daily horoscope update via Vercel API.</p>
        <p class="text-muted">Example (cURL):</p>
        <pre style="background:#eee;padding:10px;border-radius:8px;overflow-x:auto;">
curl -X POST ${baseUrl}/api/astrology/horoscope \
     -H "Content-Type: application/json"
        </pre>
    </div>

    <!-- Footer -->
    <div class="note">
        © <script>document.write(new Date().getFullYear());</script> Aerosimo Ltd.
        All rights reserved.
    </div>

    <div class="note">
        Server Time: <%= new java.util.Date() %>
    </div>
</div>
</body>
</html>
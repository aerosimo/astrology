![MIT License.!](/img/MIT.png "MIT")

##### MIT License Copyright (c) 2025 Aerosimo

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.

	The characters, names, events, articles, templates, or information provided by 
	Aerosimo Ltd are fictional and for reference only. While we strive to keep the 
	information up to date and correct, we make no representations or warranties of 
	any kind, express or implied, about the completeness, accuracy, reliability, 
	suitability, or availability with respect to the information, articles, templates, 
	or related graphics contained in this document or any part of the project. 
	Any reliance you place on such information is therefore strictly at your own risk.

---

# Postal System (PS)

![Project Cover.!](/img/cover.jpg "Postal System (PS)")
# Postal System (PS)
> *Postal System (PS) is a versatile and robust email service designed to simplify and enhance your email communication.*

---
This project is a **Postal System (PS)** built using **core Java**. and is a Java Web Application designed to send email notifications to recipients via the provided inputs. It exposes **SOAP** web services.

## Project Structure

This `README.md` gives an overview of the project structure, dependencies, and instructions on how to build and deploy the application.

## Features

1. **Postmaster**: The core logic that prepares and send email based on user input.
2. **SOAP Web Service**: Exposes a `sendEmail` method.
3. **JSP Web Interface**: A user-friendly web interface.

## Getting Started

![Project Codes & Tasks.!](/img/code.jpg "Project Codes and Task")

---

### Prerequisites

>- **Apache TomEE 10**: Make sure TomEE is installed and running.
>- **Java 23**: Ensure you have Java 23 installed as TomEE 10 targets Jakarta EE 10.
>- **Maven**: The project uses Maven for dependency management with any IDE of choice.

### Dependencies

The required dependencies are defined in `pom.xml`. Below are the key dependencies:

- **Jakarta EE 10 API**: Provides JAX-WS support.
- **JAX-WS**: For SOAP web service implementation.

### Building and Running the Application

1. **Clone the repository**:

    ```bash
    git clone https://github.com/aerosimo/postalsystem.git
    cd postalsystem
    ```

2. **Build the project using Maven**:

    ```bash
    mvn clean install
    ```

3. **Deploy the WAR file**:

   After building the project, deploy the generated `WAR` file from the `target/` directory into the `webapps/` folder of your TomEE installation.

4. **Start TomEE**:

   Start TomEE and access the application:

    - SOAP Service: WSDL at `http://localhost:8080/postalsystem/ws/postalsystem?wsdl`
    - Web Interface: `http://localhost:8080/postalsystem/index.jsp`

## Detailed Explanation of Components

### 1. **Connect**

Located in `com/aerosimo/ominet/core/Connect.java`, this is a simple java class that acquires the Session for Postal System based on the settings available on the application server without exposing the mail credentials in the code.

### 2. **Postmaster** (Core Logic)

Located in `com/aerosimo/ominet/models/Postmaster.java`, this is a simple java class that implements the logic of what to send and if send with attachments or not.

### 3. **SOAP Web Service** (JAX-WS)

The SOAP web service is implemented in `com/aerosimo/ominet/api/PostalSystemSOAPService.java`. It provides the following methods:
- `sendMail(String emailAddress, String emailSubject, String emailMessage, String emailFiles)`: Send email to the provided address.

Example SOAP Request for `sendMail`:
```xml
<?xml version='1.0' encoding='UTF-8'?>
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope" xmlns:mail="https://aerosimo.com/api/ws/postmaster">
   <soap:Header/>
   <soap:Body>
      <mail:sendEmail>
         <emailAddress>elijah@aerosimo.com</emailAddress>
         <emailSubject>Test Email from PostMan</emailSubject>
         <emailMessage>Hello I am a test email message from Postman Application</emailMessage>
         <emailFiles />
      </mail:sendEmail>
   </soap:Body>
</soap:Envelope>
```

---

![Aerosimo Logo.!](/img/logo.png "Aerosimo")
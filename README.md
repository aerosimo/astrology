![MIT License](/src/main/webapp/assets/img/MIT.png "MIT")

<details>
  <summary>License</summary>

**MIT License © 2025 Aerosimo**

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
</details>

---

![Project Cover](/src/main/webapp/assets/img/cover.webp "Astrology")
# Astrology
> *Astrology is a range of divinatory practices, recognized as pseudo-scientific since the 18th century,
that propose that information about human affairs and terrestrial events may be discerned by studying the
apparent positions of celestial objects.*

---

## Project Structure

This `README.md` gives an overview of the project structure and instructions on how to build and deploy the application.

## Getting Started

![Project Codes & Tasks](/src/main/webapp/assets/img/code.jpg "Project Codes and Task")

---

### Prerequisites

>- **Apache TomEE 10**: is the application server used during development, but any Jakarta EE 10-compliant server should work.
>- **Java 23**: Ensure you have Java 23 installed as TomEE 10 targets Jakarta EE 10.
>- **Maven**: The project uses Maven for dependency management with any IDE of choice.

### Dependencies

The required dependencies are defined in `pom.xml`. Below are the key dependencies:

- **Jakarta EE 10 API**: Provides JAX-WS support.
- **JAX-RS**: For REST web service implementation.

### Building and Running the Application

## Quickstart

1. **Clone the repository**:

    ```bash
    git clone https://github.com/aerosimo/astrology.git
    cd astrology
    ```

2. **Build the project using Maven**:

    ```bash
    mvn clean install
    ```

3. **Deploy the WAR file**:

   After building the project, deploy the generated `WAR` file from the `target/` directory into the `webapps/` folder of your preferred Jakarta EE 10-compatible server.

4. **Start your preferred Jakarta EE 10-compatible server**:

   Start server and access the application:

    - REST Service: http://localhost:8080/astrology/api/horoscope
    - Web Interface: `http://localhost:8080/astrology/index.jsp`

![Project Cover](/src/main/webapp/assets/img/coding.jpg "Coding")

## Detailed Explanation of Components

###  **REST Web Service** (JAX-RS)

The REST web service is implemented in `com.aerosimo.ominet.api.AstrologyREST.java`.

Example horoscope REST Request:
```curl
POST http://localhost:8081/astrology/api/horoscope

```
Example dailyHoroscope REST Response:
```json
    {
      "status": "success",
      "message": "Daily horoscope update initiated."
    }
```

```
## Contributing

We welcome feedback and contributions. Please open an issue or submit a pull request.

```

![Aerosimo Logo](/src/main/webapp/assets/img/logo.png "Aerosimo")
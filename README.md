<div align="center">
    <a href=""><h1 align="center">gradecheck</h1></a>

My first ever 'practical' software @ 7 years ago, built with [JavaFX](https://openjfx.io/), [JSoup](https://jsoup.org/), and compiled and built with [Eclipse](https://www.eclipse.org/).

</div>


![Alt text](login.png?raw=true "Login")</br>

## An Interal Student Tooling System
GradeCheck is an advanced Java-based desktop application crafted to empower students with real-time access to their academic grades. Featuring a robust and responsive user interface developed with JavaFX, this application stands out with its unique integration of sophisticated web scraping techniques. These techniques enable the retrieval of grade information directly from educational portals.

What sets GradeCheck apart is its capability to function as a comprehensive real-time monitoring system. This system not only allows students to track their course activities as they occur but also intelligently searches for any missing coursework, providing timely alerts and updates. Furthermore, GradeCheck includes a feature to automatically compute the GPA for students, considering the grades obtained each semester. This functionality offers students a dynamic and constantly updated view of their academic standing.

With its blend of real-time data access, intelligent coursework tracking, and automated GPA computation, GradeCheck is an essential tool for students striving to stay informed and ahead in their academic journey.

<h2>Features</h2>

**User-Friendly Interface**: Built with JavaFX, the application offers a clean and intuitive user interface, making it easy for students to navigate and use.

**Secure Login:** Users can securely log in with their credentials. The application handles username and password data with utmost care, ensuring privacy and security.

**Grade Scraping**: GradeCheck employs sophisticated web scraping to extract grades from educational portals. This feature is realized through Java's native HTTP capabilities, allowing the application to send requests and receive responses akin to a web browser.

**Reverse Engineered API Endpoint**: The application taps into a centralized API endpoint, which was reverse-engineered for accurate and efficient grade retrieval

<br/>

## Running Locally

### Prerequisites
Java JDK 8 or later
JavaFX SDK
Any IDE that supports Java (IntelliJ IDEA, Eclipse, etc.)

### Installation and Build
```sh-session
git clone https://github.com/caslabs/gradecheck
cd gradecheck
```

2. Open the project in your IDE.
3. Configure JavaFX SDK in your project settings.
4. Run the application from the main class.


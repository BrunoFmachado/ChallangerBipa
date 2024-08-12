<h1>Challenger Bipa</h1>

<h2>Overview</h2>
<p>Challenger Bipa is an Android application that provides information about the top Lightning Network nodes based on their connectivity and capacity. The app fetches data from an external API, displays the nodes with relevant details, and allows users to interact with the data, such as copying the public key of a node.</p>

<h2>Features</h2>
<ul>
    <li>Displays a list of top Lightning Network nodes.</li>
    <li>Converts node capacity from satoshis to Bitcoin.</li>
    <li>Formats Unix timestamps to human-readable date and time.</li>
    <li>Supports localization of city and country names (pt-BR or en).</li>
    <li>Provides an interface for copying node public keys to the clipboard.</li>
    <li>Handles loading states and error messages gracefully.</li>
</ul>

<h2>Build Tools & Versions Used</h2>
<ul>
    <li><strong>Kotlin:</strong> 1.8.0</li>
    <li><strong>Android SDK:</strong> 33</li>
    <li><strong>Gradle:</strong> 7.0.4</li>
    <li><strong>Dagger Hilt:</strong> 2.38.1</li>
    <li><strong>Retrofit:</strong> 2.9.0</li>
    <li><strong>Gson:</strong> 2.8.7</li>
    <li><strong>AndroidX:</strong> Core: 1.7.0, AppCompat: 1.4.1, Lifecycle: 2.4.1</li>
</ul>

<h2>Steps to Run the App</h2>
<ol>
    <li><strong>Clone the Repository:</strong><br>
        <code>git clone https://github.com/yourusername/challengerbipa.git</code>
    </li>
    <li><strong>Open the Project in Android Studio:</strong><br>
        Use the latest version of Android Studio and sync the project with Gradle files.
    </li>
    <li><strong>Build and Run:</strong><br>
        Build the project and run it on an emulator or physical device with Android SDK 33 or later.
    </li>
    <li><strong>Run Tests:</strong><br>
        Unit tests can be executed through Android Studio’s testing tools.
    </li>
</ol>

<h2><strong>What areas of the app did you focus on?</strong></h2>
<p>The primary focus was on <strong>API Integration</strong>, <strong>UI Implementation</strong>, and <strong>Data Transformation</strong>. The goal was to ensure accurate data fetching from the API, proper display and formatting of node details, and a user-friendly interface that supports interaction with the data.</p>

<h2><strong>What was the reason for your focus? What problems were you trying to solve?</strong></h2>
<p>The focus was on creating a smooth user experience by ensuring that data fetched from the API was accurately displayed, formatted, and easy to interact with. Handling loading and error states was essential to provide feedback to the user, thus preventing a negative user experience.</p>

<h2><strong>How long did you spend on this project?</strong></h2>
<p>Approximately <strong>5 hours</strong> were spent on this project. This time was allocated to setting up the project, integrating the API, implementing the UI, and writing tests.</p>

<h2><strong>Did you make any trade-offs for this project? What would you have done differently with more time?</strong></h2>
<p>With limited time, the focus was on core functionalities. Given more time, I would have:</p>
<ul>
    <li>Enhanced the UI with additional animations and feedback.</li>
    <li>Improved error handling with more specific messages.</li>
    <li>Added more comprehensive test coverage.</li>
</ul>

<h2><strong>What do you think is the weakest part of your project?</strong></h2>
<p>The weakest part of the project is the <strong>limited test coverage</strong>. While critical paths are covered, edge cases and UI tests could be further developed.</p>

<h2><strong>Is there any other information you’d like us to know?</strong></h2>
<p>This project is a proof of concept aimed at showcasing the ability to integrate external APIs and handle common Android development challenges. The architecture was kept simple to focus on functionality, but it is designed to be easily extensible for future enhancements.</p>

</body>
</html>

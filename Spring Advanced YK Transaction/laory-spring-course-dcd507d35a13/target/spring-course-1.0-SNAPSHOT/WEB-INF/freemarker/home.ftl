<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>EPAM SpringMVC</title>
    </head>
    <body>
        <h1>Select:</h1>
            <p><a href="${rc.contextPath}/users">Show users</a></p>
            <p><a href="${rc.contextPath}/users/pdf">Download users as a PDF document</a></p>
            <p><a href="${rc.contextPath}/tickets/book">Book tickets</a></p>
            <p><a href="${rc.contextPath}/getBookedTickets">Show booked tickets (for BOOKING_MANAGER role only)</a></p>
            <p><a href="${rc.contextPath}/multipleUpload">Upload Users and Events with JSON files</a></p>
            <p><a href="${rc.contextPath}/registerUser">Register new user (for ADMIN role only)</a></p>
            <p><a href="${rc.contextPath}/events">Show events</a></p>
    </body>
</html>

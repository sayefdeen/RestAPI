<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <title>Main Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table.css">

</head>
<body>
<div class="sidebar-container">
    <div class="sidebar-logo">
        Welcome ${user.getName().split("@")[0]}
    </div>
    <ul class="sidebar-navigation">
        <li class="header">Navigation</li>
        <li>
            <a href="${pageContext.request.contextPath}/courses.do">
                All Courses
            </a>
        </li>
        <li>
            <a href="#">Log out
            </a>
        </li>
    </ul>
</div>

<div class="content-container">

    <div class="container-fluid">

        <div class="jumbotron">
            <h1>Home Page</h1>
            <p>Welcome ${user.getName().split("@")[0]}, This is the home page of our application</p>
            <p>You can select All Courses to see your marks in these courses</p>
            <p>It will be shown below</p>
            <p>Click on the course ID to get its statistics</p>

        </div>
        <table>
            <thead>
            <tr>
                <th>Course Id</th>
                <th>Course Name</th>
                <th>Course Result</th>
                <th>Course Section</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allCourses}" var="course">
                <tr>
                    <td data-column="Course Id"> <a href="${pageContext.request.contextPath}/details.do?id=${course.getId()}">${course.getId()}</a> </td>
                    <td data-column="Course Name">${course.getName()}</td>
                    <td data-column="Course Result">${course.getResult()}</td>
                    <td data-column="Course Section">${course.getSection()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <table>
            <thead>
            <tr>
                <th>Course Id</th>
                <th>Course Name</th>
                <th>Course Result</th>
                <th>Course Section</th>
                <th>Total Mark Sum</th>
                <th>Average</th>
                <th>Max</th>
                <th>Min</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-column="Course Id">${courseDetails.getId()} </td>
                    <td data-column="Course Name">${courseDetails.getName()}</td>
                    <td data-column="Course Result">${courseDetails.getResult()}</td>
                    <td data-column="Course Section">${courseDetails.getSection()}</td>
                    <td data-column="Total Mark Sum">${courseDetails.getSum()}</td>
                    <td data-column="Average">${courseDetails.getAvg()}</td>
                    <td data-column="Max">${courseDetails.getMax()}</td>
                    <td data-column="Min">${courseDetails.getMin()}</td>

                </tr>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>

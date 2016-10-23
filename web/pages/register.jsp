<%-- 
    Document   : register
    Created on : Oct 23, 2016, 9:52:20 PM
    Author     : pingpongsz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Test Page</title>
    </head>
    <body>
        <h1>Register Test Page</h1>
        <form action="/WhereIsIt/Register" method="POST"><br>
            Image: <input type="file" /><br>
            Email: <input type="mail" placeholder="Email" name="email" required/><br>
            Password: <input type="password"  name="password" required/><br>
            Confirm Password: <input type="password"  name="cfpassword" required/><br>
            Fist Name: <input type="text"  name="fname"required/><br>
            Last Name: <input type="text" name="lname"required/><br>
            Gender: <br><input type="radio" name="gender" value="male"required/>Male<br>
                    <input type="radio" name="gender" value="female"required/>Female<br>
                    <input type="radio" name="gender" value="donotenter"required/>Do not Enter<br>
            Citizen ID: <input type="number"  name="citizenid" maxlength="13"required/><br>
            Phone number: <input type="number"  name="tel" maxlength="10"required/><br>
            Faculty: <input type="text"  name="faculty"required/><br>
            Address: <input type="text"  name="address"required/><br>
            <input type="submit" value="Submit"/>
        </form>
        
        <h1>${message}</h1>
    </body>
</html>

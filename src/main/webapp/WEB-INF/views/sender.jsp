<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">

</head>
<body>
<script src="js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/search.js"></script>
<h1>&nbsp;&nbsp;&nbsp;Message Sender</h1>
<hr/>

<div class="container">
    <form name="sendForm" id="sendForm">
        <div class="row">
            <h3 class="orange">User Details:</h3>
            <br/>
            <div class="col-md-3">
                <bold>First Name:</bold>
                <input type="text" name="fname" id="fname" placeholder="First Name" />
            </div>

            <div class="col-md-4">
                <bold> Middle Name:&nbsp;&nbsp;&nbsp; </bold>
                <input type="text" name="mname" id="mname" placeholder="Middle Name" />
            </div>

            <div class="col-md-3">
                <bold> Last Name: </bold>
                <input type="text" name="lname" id="lname" placeholder="Last Name" />
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-3">
                <bold>Gender:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</bold>
                <select id="gender" name="gender">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
            </div>

            <div class="col-md-4">
                <bold> Phone Number: </bold>
                <input type="text" name="phone" id="phone" placeholder="Phone Number" />
            </div>

            <div class="col-md-3">
                <bold> Company: &nbsp;</bold>
                <input type="text" name="company" id="company" placeholder="Company" />
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-6">
                <bold>Address:</bold>
                <input type="textarea"  style="width: 500px; height: 100px;" name="address" id="address" />
            </div>
        </div>

        <br/>
        <div class="row">
            <div class="col-md-2">
                <input type="button" id="sendButton" class="btn btn-primary" name="sendButton" value="Send Details" onclick="sendData();"/>
            </div>
            <div class="col-md-2">
                <div id="sendResults">

                </div>
            </div>
        </div>
    </form>

    <div class="row">
        <div id="sendResults">
        </div>
    </div>

</div>
</body>
</html>

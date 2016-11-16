<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link href="css/bootstrap.min.css" rel="stylesheet">--%>

</head>
<body>
<script src="js/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<!-- <script src="js/bootstrap.min.js"></script> -->
<script src="js/search.js"></script>
<h1>Query Interface!!!</h1>

<div class="container">
    <form name="searchForm" id="searchForm">
        <div class="row">
            <div class="col-md-3">
                <bold>Key Name:</bold>
                <input type="text" name="key" id="key" placeholder="Search value" />
            </div>

            <div class="col-md-3">
            <bold> Operator: </bold>
                <select id="operator" name="operator">
                    <option value="equals">Equals</option>
                    <option value="contains">Contains</option>
                    <option value="less"><</option>
                    <option value="greater">></option>
                </select>
            </div>

            <div class="col-md-3">
                <bold> Value to search: </bold>
                <input type="text" name="searchString" id="searchString" placeholder="Search value" />
            </div>
        </div>
        <div class="row">
            <input type="button" id="searchButton" name="searchButton" value="Search" onclick="search();"/>
        </div>
    </form>
</div>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<script src="js/jquery-3.1.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/search.js"></script>
<h1>Query Interface!!!</h1>
<hr/>

<div class="container">
    <form name="searchForm" id="searchForm">
        <div class="row">
            <h3>Search:</h3>
            <div class="col-md-3">
                <bold>Key Name:</bold>
                <input type="text" name="key" id="key" placeholder="Key" />
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
        <br/>
        <div class="row">
            <input type="button" id="searchButton" class="btn btn-primary" name="searchButton" value="Search" onclick="search();"/>
        </div>
    </form>

    <div class="row">
        <h3>Search Results: </h3>
        <div id="searchresults">
        </div>
    </div>

    <br/>

    <form name="insertForm" id="insertForm">
        <h3>Put:</h3>
        <div class="row">
            <div class="col-md-3">
                <bold>Key:</bold>
                <input type="text" name="key" id="key" placeholder="Key" />
            </div>

            <div class="col-md-3">
                <bold> Value: </bold>
                <input type="text" name="value" id="value" placeholder="Value" />
            </div>
        </div>
        <br/>
        <div class="row">
            <input type="button" id="putButton" class="btn btn-primary" name="putButton" value="Insert Record" onclick="put();"/>
        </div>
    </form>

    <div id="putResults">
    </div>

    <br/>

    <form name="deleteForm" id="deleteForm">
        <h3>Delete:</h3>
        <div class="row">
            <div class="col-md-3">
                <bold>Key:</bold>
                <input type="text" name="key" id="key" placeholder="Key to delete" />
            </div>
        </div>
        <br/>
        <div class="row">
            <input type="button" id="removeButton" class="btn btn-primary" name="removeButton" value="Delete Record" onclick="remove();"/>
        </div>
    </form>

</div>
</body>
</html>

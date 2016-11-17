function fetch_mongo_records(){
    console.log("called");
    $.ajax({
        type: "GET",
        url: "/fetchData",
        async: false
    }).done(function(response){
        var len = response.RESPONSE.length;
        $('#msgcounts').html(len);
        var htmlStr = "";
        for (var i = 0; i < len ; i++) {
            htmlStr = htmlStr + "<div class='row'> <div class='col-md-1'>"+(i+1)+"</div> <div class='col-md-11'>"+JSON.stringify(response.RESPONSE[i])+"</div> </div>";
        }
        $('#searchresults').html(htmlStr);
        setTimeout(function(){fetch_mongo_records();}, 1000);
    });
}

$( document ).ready(function() {
    fetch_mongo_records();
});
function fetch_mongo_records(){
    console.log("called");
    $.ajax({
        type: "GET",
        url: "/fethcData",
        async: false
    }).done(function(response){
        console.log(response);
        var len = response.RESPONSE.length;
        $('#msgcounts').html(len);
        console.log(len);
        var htmlStr = "";
        for (var i = 0; i < len ; i++) {
            htmlStr = htmlStr + "<div class='row'> <div class='col-md-1'>"+(i+1)+"</div> <div class='col-md-11'>"+JSON.stringify(response.RESPONSE[i])+"</div> </div>";
            console.log(response.RESPONSE[i]);
        }
        $('#searchresults').html(htmlStr);
        setTimeout(function(){fetch_mongo_records();}, 10000);
    });
}

$( document ).ready(function() {
    fetch_mongo_records();
});
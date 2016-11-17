
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function search() {
    var request = {
        "key":$('#searchKey').val(),
        "operator": $('#operator').val(),
        "searchString": $('#searchString').val()
    }

    $.ajax({
            method: "POST",
            url: "/search",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(request)
        })
        .done(function( response ) {
            var len = response.RESPONSE.length;
            $('#msgcounts').html(len);
            var htmlStr = "";
            for (var i = 0; i < len ; i++) {
                htmlStr = htmlStr + "<div class='row'> <div class='col-md-1'>"+(i+1)+"</div> <div class='col-md-11'>"+JSON.stringify(response.RESPONSE[i])+"</div> </div>";
            }
            if(len === 0){
                htmlStr = "<h5 class='green'>No Result Found</h5>";
            }
            $('#searchresults').html(htmlStr);

            //alert( "Data Saved: " + response );
        });
}

function put() {
    var request = {
        "key":$('#putKey').val(),
        "value": $('#putValue').val()
    }

    $.ajax({
            method: "POST",
            url: "/put",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(request)
        })
        .done(function( response ) {
            alert( "Data Saved: " + response );
        });
}

function remove() {

    var request = {
        "key":$('#deleteKey').val()
    }

    $.ajax({
            method: "POST",
            url: "/delete",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(request)
        })
        .done(function( response ) {
            alert( "Data Saved: " + response );
        });
}


function sendData() {
    var request = $("#sendForm").serializeObject();
    console.log(request);
    $.ajax({
            method: "POST",
            url: "/sendData",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(request)
        })
        .done(function( response ) {
            $('#sendResults').html("<h5 class='green'>Sent</h5>");
            $("#sendForm")[0].reset();
        });
}

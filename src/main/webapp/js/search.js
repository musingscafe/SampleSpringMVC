
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
    alert($('#searchString').val());

    var request = {
        "key":$('#key').val(),
        "operator": $('#operator').val(),
        "searchString": $('#searchString').val()
    }

    $.ajax({
            method: "POST",
            url: "/putInMongo",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(request)
        })
        .done(function( response ) {
            var len = response.insertedObjects.length;
            var htmlStr = "";
            for (var i = 0; i < len ; i++) {
                htmlStr = htmlStr + "<div class='row'> <div class='col-md-1'>"+(i+1)+"</div> <div class='col-md-11'>"+JSON.stringify(response.insertedObjects[i])+"</div> </div>";
                console.log(response.insertedObjects[i]);
            }
            $('#searchresults').html(htmlStr);
            //alert( "Data Saved: " + response );
        });
}

function put() {
    var request = {
        "key":$('#key').val(),
        "value": $('#value').val()
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
        "key":$('#key').val()
    }

    $.ajax({
            method: "POST",
            url: "/remove",
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

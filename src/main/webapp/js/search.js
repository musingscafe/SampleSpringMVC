//alert('2')
//$('#searchButton').click(function(event) {
//    alert('1');
//});

function search(){
    alert($('#searchString').val());

    var request = {
        "key":$('#key').val(),
        "operator": $('#operator').val(),
        "searchString": $('#searchString').val()
    }
}

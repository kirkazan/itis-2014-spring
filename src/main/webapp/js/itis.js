$(function() {
    $(".chosen-select").chosen()
});

$('#patient_select').autocomplete({
    source: function( request, response ) {
        $.ajax({
            url: "/patient?s="+request.term,
            dataType: "json",
            beforeSend: function(){$('ul.chzn-results').empty();},
            success: function( data ) {
                response( $.map( data, function( item ) {
                    $('ul.chzn-results').append('<li class="active-result">' + item.name + '</li>');
                }));
            }
        });
    }
});
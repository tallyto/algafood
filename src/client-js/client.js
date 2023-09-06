function consultarRestaurante(){
    $.ajax({
        url: "http://localhost:3001/restaurantes",
        type: "GET",
        success: function(response){
            $("#conteudo").text(response)
        }
    })
}

$("#botao").click(consultarRestaurante)

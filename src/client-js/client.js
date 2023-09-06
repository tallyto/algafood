function consultarRestaurante(){
    $.ajax({
        url: "http://localhost:3001/restaurantes",
        type: "GET",
        success: function(response){
            $("#conteudo").text(JSON.stringify(response))
        }
    })
}

function fecharRestaurante(){
    console.log("Chamou")
    $.ajax({
        url: "http://localhost:3001/restaurantes/1/fechamento",
        type: "PUT",
        success: function(response){
            alert("Restaurante foi fechado!")
        }
    })
}

$("#botao").click(consultarRestaurante)

$("#fecharRestaurante").click(fecharRestaurante)

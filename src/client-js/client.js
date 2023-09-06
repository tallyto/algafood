function consultarRestaurante() {
    $.ajax({
        url: "http://localhost:3001/restaurantes",
        type: "GET",
        success: function (response) {
            preencherTabelaRestaurantes(response)
        }
    })
}

function preencherTabelaRestaurantes(restaurantes) {
    $("#tabela-restaurante tbody tr").remove();

    $.each(restaurantes, function (i, restaurante) {
        const linha = $("<tr>");

        linha.append(
            $("<td>").text(restaurante.id),
            $("<td>").text(restaurante.nome),
            $("<td>").text("R$" + restaurante.taxaFrete.toFixed(2)), // Exibe a taxa de frete formatada
            $("<td>").text(restaurante.cozinha.nome)
        );

        linha.appendTo("#tabela-restaurante tbody");
    });
}

function fecharRestaurante() {
    $.ajax({
        url: "http://localhost:3001/restaurantes/1/fechamento",
        type: "PUT",
        success: function (response) {
            alert("Restaurante foi fechado!")
        }
    })
}

$("#botao").click(consultarRestaurante)

$("#fecharRestaurante").click(fecharRestaurante)


function consultar() {
    $.ajax({
        url: "http://localhost:3001/formas-pagamento",
        type: "GET",
        success: function (response) {
            preencherTabela(response)
        }
    })
}

function preencherTabela(formasPagamento) {
    $("#tabela tbody tr").remove()

    $.each(formasPagamento, function (i, formaPagamento) {
        const linha = $("<tr>");

        linha.append(
            $("<td>").text(formaPagamento.id),
            $("<td>").text(formaPagamento.descricao)
        )

        linha.appendTo("#tabela")
    })
}

$("#btn-consultar").click(consultar)

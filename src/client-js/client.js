// Define a base URL para as requisições
const baseUrl = "http://localhost:3001";

function consultarRestaurante() {
    $.ajax({
        url: `${baseUrl}/restaurantes`,
        type: "GET",
        success: function (response) {
            preencherTabelaRestaurantes(response);
        },
        error: function (error) {
            console.error("Erro ao consultar restaurantes:", error);
        },
    });
}

function fecharRestaurante() {
    $.ajax({
        url: `${baseUrl}/restaurantes/1/fechamento`,
        type: "PUT",
        success: function (response) {
            alert("Restaurante foi fechado!");
        },
        error: function (error) {
            console.error("Erro ao fechar restaurante:", error);
        },
    });
}

function consultarFormaPagamento() {
    $.ajax({
        url: `${baseUrl}/formas-pagamento`,
        type: "GET",
        success: function (response) {
            preencherTabela(response);
        },
        error: function (error) {
            console.error("Erro ao consultar formas de pagamento:", error);

        },
    });
}

function cadastrarFormaPagamento() {
    const descricao = $("#campo-descricao").val();
    const formaPagamento = {descricao};

    $.ajax({
        url: `${baseUrl}/formas-pagamento`,
        type: "POST",
        data: JSON.stringify(formaPagamento),
        contentType: "application/json",
        success: function (response) {
            alert("Forma de pagamento cadastrada com sucesso")
            $("#campo-descricao").val("");
            consultarFormaPagamento();
        },
        error: function (error) {
            console.error("Erro ao cadastrar forma de pagamento:", error);
            if (error.status === 400) {
                const problem = JSON.parse(error.responseText);
                alert(problem.userMessage);
            } else {
                alert("Erro ao cadastrar forma de pagamento");
            }
        },
    });
}

function preencherTabelaRestaurantes(restaurantes) {
    const tbody = $("#tabela-restaurante tbody");
    tbody.empty();

    restaurantes.forEach((restaurante) => {
        const linha = $("<tr>");

        linha.append(
            $("<td>").text(restaurante.id),
            $("<td>").text(restaurante.nome),
            $("<td>").text("R$" + restaurante.taxaFrete.toFixed(2)),
            $("<td>").text(restaurante.cozinha.nome)
        );

        linha.appendTo(tbody);
    });
}

function preencherTabela(formasPagamento) {
    const tbody = $("#tabela tbody");
    tbody.empty();

    formasPagamento.forEach((formaPagamento) => {
        const linha = $("<tr>");

        linha.append(
            $("<td>").text(formaPagamento.id),
            $("<td>").text(formaPagamento.descricao)
        );

        linha.appendTo(tbody);
    });
}

// Associa os eventos aos botões
$("#botao").click(consultarRestaurante);
$("#fecharRestaurante").click(fecharRestaurante);
$("#btn-consultar").click(consultarFormaPagamento);
$("#btn-cadastrar").click(cadastrarFormaPagamento);

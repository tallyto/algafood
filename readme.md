[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=tallyto_algafood&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=tallyto_algafood&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=tallyto_algafood&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=tallyto_algafood&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=tallyto_algafood&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=tallyto_algafood)

# README - Aplicação Spring

Este é o README da nossa aplicação Spring, que abrange diversos tópicos relacionados ao desenvolvimento de APIs usando o Spring. Neste guia, você encontrará informações sobre como executar a aplicação e uma breve visão geral das principais funcionalidades.

## Visão Geral

Nossa aplicação Spring é um projeto completo que demonstra as melhores práticas para o desenvolvimento de APIs com o Spring Framework. Ela cobre desde conceitos básicos até funcionalidades avançadas.

## Executando a Aplicação

### Pré-requisitos

- Java JDK instalado (versão 17.0.6 ou superior)
- Apache Maven instalado
- Banco de dados configurado (por exemplo, MySQL)

### Passos

1. Clone este repositório:

   ```bash
   git clone https://github.com/tallyto/algafood
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd algafood
   ```

3. Configure o arquivo `application.properties` com as informações do seu banco de dados.

4. Compile o projeto com o Maven:

   ```bash
   mvn clean install
   ```

5. Execute a aplicação Spring:

   ```bash
   mvn spring-boot:run
   ```

6. Acesse a aplicação em [http://localhost:3001](http://localhost:3001).

## Funcionalidades Principais

Aqui está um resumo das funcionalidades abordadas em nossa aplicação Spring:

- **Injeção de Dependências:** Conceito fundamental do Spring.
- **JPA e Hibernate:** Mapeamento de objetos Java para bancos de dados.
- **REST com Spring:** Criação de serviços RESTful.
- **Spring Data JPA:** Simplificação de repositórios de dados.
- **Tratamento de Erros:** Modelagem de respostas de erro consistentes.
- **Validações com Bean Validation:** Validação de dados de entrada.
- **Testes de Integração:** Garantia de qualidade da API.
- **Boas Práticas:** Recomendações ao projetar APIs com Spring.
- **Modelagem Avançada:** Implementação de recursos complexos.
- **Documentação da API:** Usando OpenAPI, Swagger UI e SpringFox.

Agora, você pode explorar e testar as funcionalidades da aplicação de acordo com seus interesses.

---

**Observação:** Consulte a documentação específica de cada funcionalidade para obter informações detalhadas sobre seu uso.

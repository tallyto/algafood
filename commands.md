# Gerar o jar da aplicacao

```shell
mvn package
```

# Executar o programa passando uma variavel de ambiente

```shell
java -jar .\target\algafood-0.0.1-SNAPSHOT.jar --server.port=3000
```

# Definir uma variavel de ambiente no sistema

### Unix based

```shell
export SERVER_PORT=4001
```

### Windows based

```shell 
SET SERVER_PORT=4001
```

# Mostrar vaviavel no windows
```shell
echo %SERVER_PORT%
```

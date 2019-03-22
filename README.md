DOCUMENTAÇÃO DO PROJETO
===========================================


## **ESTRUTURA DO BANCO**
 
Foram criados dois bancos local sendo um para a aplicação e outro para rodar os testes de integração/endpoints, com as seguintes URIs de conexão. Além disso, foi disponibilizado no arquivo `application.properties` uma url de conexão em um banco de dados as service online postgresql, caso não desejam criar uma database `xy-inc` local na porta 5433 (ou de sua preferência).  

**Banco principal:** `jdbc:postgresql://localhost:5433/xy-inc`

**Banco de teste:** `jdbc:h2:mem:test`

**Posgresql as Service:** `jdbc:postgresql://ec2-184-73-153-64.compute-1.amazonaws.com:5432/dc6j2oppjdv26m`

Assim, a seguinte modelagem da entidade foi projetada:

    name: String required,
    x: Integer required,
    y: Integer required,

<br/>

## **DESCRIÇÃO DA API**

A porta 8080 está exposta, e a partir dela é possível acessar a API de resources:

- **GET**
    - **Listagem:** Para consultar todos os recursos cadastrados no banco, basta acessar http://localhost:8080/poi 
    
    - **Encontrar POI's proximos:** A partir de http://localhost:8008/poi/near é possível listar todos os pois proximos a um determinado ponto levando em consideração uma thresold de distância, para isso envie como parâmetro na url os seguintes dados: x, y e d (distância máxima desejada para filtragem). 

    Por exemplo: `http://localhost:8008/poi/near?x=20&y=10&d=10`

    Resposta:

    ```json
        [
            {
                "id": 1,
                "x": 27,
                "y": 12,
                "name": "Lanchonete"
            },
            {
                "id": 3,
                "x": 15,
                "y": 12,
                "name": "Joalheria"
            },
            {
                "id": 5,
                "x": 23,
                "y": 6,
                "name": "Supermercado"
            },
            {
                "id": 6,
                "x": 12,
                "y": 8,
                "name": "Pub"
            }
        ]
    ```

- **POST**
    - **Criação de novo POI:** A partir de http://localhost:8080/poi é possível criar um novo POI, como payload debe ser enviado um objeto JSON, como no exemplo:
            
    ```json
        {
            "x": 22,
            "y": 17,
            "name": "Estádio de futebol"
        }
    ```

## **EXECUTANDO O PROJETO**

@TODO

----

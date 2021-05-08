# ControleDeContas
Este repositório foi desenvolvido para simular um controle de contas à pagar.

## Requisitos para rodar o projeto:
- `Maven`
  - [Link para instalar MAVEN](https://maven.apache.org/download.cgi)
- `Docker`
  - [Link para instalar DOCKER](https://docs.docker.com/docker-for-windows/install/)

## Executando o projeto:
- Para executar o projeto pela primeira vez, entre na pasta raiz deste projeto(onde esta localizado os arquivos `pom.xml` e `docker-compose.yml`);
- Abra o terminal(ou cmd) neste diretório;
- Execute o comando `bash iniciaAmbienteDoZero.sh`;
  - Caso deseje encerrar a execução do comando acima, na próxima vez, é só executar o comando `bash iniciaAmbiente.sh`;

## Acessando a aplicação:
- No projeto foram criados dois containers:
  - `containerBancoDeDados`:
    - Este é o container em que está o banco de dados da aplicação(`MySQL`);
    - A porta `3308` foi disponibilizada para ser acessada. Ou seja, você pode realizar consulta ao banco de dados para verificar os dados persistidos;
    - Credenciais:
      - host: localhost
      - port: 3308
      - username: root
      - password: root
      - database: conta
  - `containerApp`:
    - Este é o container em que está a aplicação em si, desenvolvida com `Java`;
    - A porta `8080` foi disponibilizada para ser acessada. Ou seja, você pode acessar os recursos que a aplicação disponibiliza;
    - Acesso:
      - http://localhost:8080/swagger-ui.html:

## Como a aplicação funciona:
- A aplicação tem dois recursos:
  - `listaTodasAsContas`:
    - Como o nome diz, este recurso lista todas as contas que estão na base de dados;
    - Essa consulta nos traz os seguintes dados:
      - Nome da conta;
      - Valor original da conta;
      - Valor corrigido caso tenha multa(este valor irá vir com o valor 0 caso não tenha multa);
      - Quantidade de dias de atraso no pagamento da conta;
      - Data de pagamento da conta;
  - `incluiConta`: 
    - Como o nome diz, este recurso adiciona uma conta na base de dados;
    - Para adicionar a conta, os seguintes dados devem ser informados:
      - Nome da conta;
      - Valor da conta;
      - Data de vencimento da conta;
      - Data de pagamento da conta;
- Regra de negócio:
  - Caso a conta que foi incluída esteja atrasada, contém a seguinte regra:
    - Até 3 dias de atraso: 2% de multa sobre o valor da conta E juros de 0.1% por dia de atraso, baseado no valor da conta já com a multa;
    - Até 5 dias de atraso: 3% de multa sobre o valor da conta E juros de 0.2% por dia de atraso, baseado no valor da conta já com a multa;
    - Mais de 5 dias de atraso: 5% de multa sobre o valor da conta E juros de 0.3% por dia de atraso, baseado no valor da conta já com a multa;
  - Foi criada na aplicação, uma tabela de controle, para verificar o cálculo realizado(quando há cobrança de multa e juros):
    - Essa tabela se chama `regra_de_multa_por_conta`. E contém os seguintes dados:
      - Quantidade de dias de atraso;
      - Valor original da conta;
      - Porcentagem da multa;
      - Valor da conta com a multa;
      - Porcentagem de juros;
      - Valor de juros por dia;
      - Valor total da conta corrigida;
      - Id da conta;

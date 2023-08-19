# 🗞️ Documentação Back End.

## ✒️ Introdução.
Este é um projeto web desenvolvido com java com base no framework springboot e utiliza o gerenciador de bancos H2Database. Para gerenciamento das transações e persistência em banco foram utilizadas as interfaces da JPA com hibernate. Trata-se de um projeto com a finalidade de servir através de API restfull filmes e produtores premiados.

## Sistema Operacional.
- Recomendado 8GB ram

## Versão do Java.
- jdk11+

## Configurar o java e preparar o ambiente.
- Para configurar o java no windows: Abra as as variáveis de ambiente e configure o par chave valor `JAVA_HOME`:`<local de instalação do java>`.
- Por exemplo:
  
  <p></p>
  
  |chave    |  valor                        |
  |---------|-------------------------------|
  |JAVA_HOE |  C:\Program Files\Java\jdk-11 |

- Agora adicione o executável java ao PATH (ainda nas variáveis de ambiente).
- Por exemplo:
  
    <p></p>
  
  |chave    |  valor                        |
  |---------|-------------------------------|
  |PATH |  C:\Program Files\Java\jdk-11\bin |

### 1. Executando o projeto.
ℹ️ Existem dois modos pelos quais o projeto poderá ser iniciado.

### Modo 1.1: A partir de uma IDE, Ex: IntelliJ / eclipse
- Importe o projeto para a IDE de sua escolha.

  &nbsp; Obs.: O maven acompanha o projeto.
  
- Execute o comando:

`<insira aqui o path onde esta localizado o projeto>\ApiTextoIT\apiTexoBack\maven\bin\mvn -X clean package -DskipTests=true`.

### 1.1.1 Ainda na IDE execute o arquivo:

run `ApiTexoApplication.java`.

### 1.1.2 Realize requisições http com metodo 'get'.
- Abra o browser de sua preferência e digite a url: `http://<localhost || ipv4>:8080/`
- As requisições para a API são atendidas no path:
  ### `/minAndMaxByDateMovie`.

### 1.1.3 Aponte o arquivo csv e altere o domínio de entrada de dados.
🗒️ O projeto acompanha um folder chamado docs, nele se encontra o arquivo default disponibilizado para o teste. Este arquivo poderá ser substituído com outro arquivo csv de ⚠️ **mesmo nome**.
   
🗒️ `Em ../docs/.. Substitua o arquivo csv` 

📓 Para realizar os testes de integração foi considerado um cenário específico a partir dos dados de entrada (arquivo csv fornecido). Foi aplicada uma lógica que possa atender diferentes dados de entrada, mas pertencentes ao mesmo escopo de natureza e domínio. Espera-se que a lógica se mantenha resiliente e consistente quando outro arquivo csv for fornecido ou substituído para servir como dados de entrada. Logo, para que fosse possível realizar os testes de integração, foi considerado este arquivo como modelo de dados de entrada. Sendo assim, as fases de teste poderão ser afetadas caso este arquivo seja substituído e uma nova etapa de testes seja realizada. 

#
### Modo 1.2: A partir do .jar
Neste projeto foi fornecido um compilado .jar para ser executado `apiTexo-0.0.1-SNAPSHOT` (projeto compilado)
- Certifique-se de que o JAVA_HOME foi apontado nas variáveis de ambiente.
      <p></p>
  &nbsp;&nbsp;&nbsp;&nbsp; execute: `java -jar apiTexo-0.0.1-SNAPSHOT.jar <forneça o path absoluto do arquivo csv >(opcional)`.
     <p></p>
  &nbsp;&nbsp;&nbsp;&nbsp; 📓 Se o path do csv não foi fornecido, será utiizado o csv presente em ../docs/..
  
#
## 3. Execução dos testes unitários.
### 🗒️ Os testes unitário são executados utilizando-se do Junit e RestAssured para integração.
- Na IDE de sua preferência com o projeto importado, execute os seguintes passos:
- 1 Execute a aplicação, run `ApiTexoApplication.java`, e aguarde a inicialização completa.
- 2 Execute a classe de teste, run 'ApiTexoApplicationTest.java', e os testes serão executados.

#
## ☣️ Problemas conhecidos e possíveis soluções:

### IDE IntelliJ build o projeto sobreecrevendo o build maven realizado manualmente
- Problema: *org.springframework.beans.factory.BeanDefinitionStoreException: Failed to parse configuration class [com.br.texo.apitexo.ApiTexoApplication]; nested exception is java.io.FileNotFoundException: class path resource [config.properties] cannot be opened because it does not exist*
  
- Solução:
1. Clique com o botão direito do mouse sobre o projeto
2. Selecione project settings
3. Selecione Modules
4. Desmarque para deletar a pasta target
5. Exclua a pasta **target** e execute o maven novamente
 
### Execução dos testes falham ao executá-los na IDE IntelliJ
- Solução: 
1. Clique com o botão direito do mouse sobre o projeto
2. Selecione project settings
3. Selecione Libraries
4. Exclua: Maven: org.apache.groovy:groovy-xml:4.0.112 e Maven: org.apache.groovy:groovy:4.0.112


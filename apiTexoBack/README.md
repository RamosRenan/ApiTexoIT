# Documentação Back End

# Sistema Operacional
- Recomendado 8GB ram

# Versão do Java
- jdk11+

# Configurar o java
- Para configurar o java no windows: Abra as as variaveis de ambiente e configure o par chave valor `JAVA_HOME`:`<local de instalação do java>`
- Ex.:
  
  <p></p>
  
  |chave    |  valor                        |
  |---------|-------------------------------|
  |JAVA_HOE |  C:\Program Files\Java\jdk-11 |

- Agora adicione o executavel java ao PATH (ainda nas váriaveis de ambiente)
- Ex.:
  
    <p></p>
  
  |chave    |  valor                        |
  |---------|-------------------------------|
  |PATH |  C:\Program Files\Java\jdk-11\bin |

# 1. Executando o projeto 
Existem dois modos que o projeto poderá ser iniciado

## Modo 1: A partir de uma IDE. Ex: IntelliJ/eclipse
- Para executar o projeto a partir de uma IDE basta importar o projeto para a IDE de sua escolha. O maven vai acompanhado do projeto.
- Execute o comando: `<path onde esta localizado o projeto>\ApiTextoIT\apiTexoBack\maven\bin\mvn -X clean package -DskipTests=true`

## 1.1 Configuração do arquivo csv
O projeto acompanha um folder chamado docs, nele se encontra o arquivo default disponibilzado para o teste. Este arquivo poderá ser alterado com outro arquivo csv com o ⚠️ **mesmo nome**
    <p></p>
🗒️ `Em ../docs/.. Substitua o arquivo csv`  <p></p>
📓 Para realizar os testes de integração considerando um cenário específico com dados de entrada (arquivo csv fornecido) aplicando uma lógica que possa atender dados de entrada difentes, mas pertecente ao mesmo escopo de natureza e domínio, espera-se que a lógica se mantenha resiliente e consistente para dados de entrada diferentes dos considerados. Arquivo csv fornecido. Logo para que seja possível realizar os testes de integração foi considerado este arquivo como modelo de dados de entrada. Para tanto embora que se espere a manutenção do funcionamento da aplicação as fases de teste poderão ser afetadas uma vez substituído este arquivo.

## 1.2 Agora e ainda na IDE basta executar o arquivo `ApiTexoApplication.java`
   
## Modo 2: A partir do .jar
Neste projeto foi fornecido um compilado .jar para ser executado `apiTexo-0.0.1-SNAPSHOT`


como executar o jar



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

# Executando o projeto 
Existem dois modos que o projeto poderá ser iniciado
- Modo 1: A partir de uma IDE. Ex: IntelliJ/eclipse
- Para executar o projeto a partir de uma IDE basta importar o projeto para a IDE de sua escolha. O maven vai acompanhado do projeto.
- Execute o comando: `<path onde esta localizado o projeto>\ApiTextoIT\apiTexoBack\maven\bin\mvn -X clean package -DskipTests=true`

## Configuração do arquivo csv
O projeto acompanha um folder chamado docs, nele se encontra o arquivo default disponibilzado para o teste. E arquivo poderá ser altera com outro arquivo csv com o *mesmo nome*

## Executar o maven no projeto


como executar o jar



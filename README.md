# tech.challenge.energy.consumption

---
## Este projeto foi feito com base no Tech Challenge da Fase 2 do curso de Arquitetura e Desenvolvimento em Java da FIAP


---
Sessões:
- [Ideia por trás do desafio](#ideia-por-trás-do-desafio)
- [Relatório Técnico](#relatório-técnico)
- [Serviços Disponíveis](#serviços-disponíveis)


---
## Ideia por trás do desafio

O desafio basicamente pede para que sejam criados três serviços, os respectivos: Pessoa, Eletrodoméstico e Endereço.
A partir disso foi desenvolvida uma API contendo essas três entidades, criando relações entre si a partir da entidade Pessoa.
O Fluxo de cadastro idealizado é descrito na imagem a seguir:
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/5a2de5e6-0080-45c0-af41-e52fe5047ff8)
Cada entidade tem seu próprio CRUD, e cada uma tem uma funcionalidade específica e própria, como ilustrado no fluxo abaixo:
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/c3fa2140-178c-41f1-abda-e685cd43732d)




---
## Relatório Técnico

Se na primeira fase pontuei o **Lombok** e o **Mapstruct** como bibliotecas/ferramentas essenciais para a agilidade no desenvolvimento desta aplicação, o grande destade da fase 2 do Tech Challenge se mostrou, para mim, ser o uso de **Migrations** com o **Flyway**.
No ínicio sua configuração veio com algumas difículdades, porém se mostrou muito útil durante o desenvolvimento e essencial para a autonomia da aplicação em seu cenário ideal, isto é, garantir que a mesma seja capaz e responsável de subir seu ambiente de
forma automática, sem a necessidade de configuração manual.
<br>
<br>
Um ponto de grande dificuldade nessa segunda fase do desafio foi definir a lógica e relacionamento entre todas as três entidades, acredito que passei por pelo menos 5 versões distintas até chegar na versão final presente nesta entrega. Após muitos desenhos, fluxos,
testes e erros, a ideia final foi que a entidade Pessoa seria única e exclusivamente responsável por gerenciar a criação e visualização de Parentes. Já a entidade Eletrodoméstico ficou com a tarefa de calcular a média do consumo mensal de cada produto cadastrado.
Por fim, a entidade Endereço ficou responsabilizada por cuidar de toda gestão de Residentes, adição de novos residentes, remoção e visualização.
<br>
<br>
Enquanto a entidade Pessoa se vê independente das outras, um Endereço precisa de uma pessoa para ser criado, mas a mesma pode ser dissociada dele, através do serviço **removeResidente**. Enquanto isso, um Eletrodoméstico obrigatóriamente precisa ter uma 
pessoa como dona.
<br>
<br>
Para testar a API, decidi utilizar o Insomnia ao invés do Postman apenas pelo costume, sinto que é uma ferramenta mais leve e simples de configurar. A coleção de testes utilizados na aplicação se encontra presente neste repositório, no arquivo **insomnia-collection.json**
<br>
<br>
Para rodar a aplicação localmente, foi criado um docker-compose, que se encontra neste mesmo repositório
Ao iniciar a aplicação, acesse o link http://localhost:8080/swagger-ui/index.html#/ para visualizar o Swagger, contendo a documentação dos endpoints desenvolvidos
Nota: Todos os endpoints "findAll" possuem suporte para filtro de qualquer um dos campos da entidade requerida, mas se optar por testá-lo sem filtros, é só chamá-lo sem passar nenhum parametro!


---
## Serviços Disponíveis

### Entidade Pessoa

![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/053f33e7-3ed2-4d9f-a280-fd1b07983665)


### Entidade Eletrodoméstico
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/417ba18e-28d2-42ed-b3a3-64722a146225)


### Entidade Endereco
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/7c12a77c-0f5f-42f4-a641-104bc36d255e)




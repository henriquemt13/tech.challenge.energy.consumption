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
Para testar a API, decidi utilizar o Insomnia ao invés do Postman apenas pelo costume, sinto que é uma ferramenta mais leve e simples de configurar.


---
## Serviços Disponíveis

### Entidade Pessoa
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/11cb1a5c-7d7b-4647-9462-3cb28d1b9cb1)

### Entidade Eletrodoméstico
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/7d204ade-eb56-475a-a6b9-0bb35303989e)

### Entidade Endereco
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/04f7065e-b72a-441d-b717-e3b2374c8db4)

### Extra: Cenário de Erro
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/7aaab44e-f52e-456b-a7f0-2ea94dc4b433)



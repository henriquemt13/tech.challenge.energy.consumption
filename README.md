# tech.challenge.energy.consumption

---
## Este projeto foi feito com base no Tech Challenge da fase 1 do curso de Arquitetura e Desenvolvimento em Java da FIAP

---
Sessões:
- [Ideia por trás do desafio](#ideia-por-trás-do-desafio)
- [Relatório Técnico](#relatório-técnico)
- [Serviços Disponíveis](#serviços-disponíveis)


---
## Ideia por trás do desafio
O desafio basicamente pede para que sejam criados três serviços de cadastro, sendo eles Cadastro de Pessoa, de Eletrodoméstico e Endereço.
A partir disso foi desenvolvida uma API contendo essas três respectivas entidades, criando uma relação entre elas a partir da entidade Pessoa.
O Fluxo de cadastro idealizado é descrito na imagem a seguir:
![image](https://github.com/henriquemt13/tech.challenge.energy.consumption.api/assets/47531611/5a2de5e6-0080-45c0-af41-e52fe5047ff8)


---
## Relatório Técnico
Dentre as tecnologias utilizadas, pode se comentar o framework **Spring** do java, com sua gama de bibliotecas prontas, o **Lombok** em específico se provou uma biblioteca muito útil, redunzindo grande parte do código a apenas poucas e simples anotações. Uma outra biblioteca utilizada foi o **Mapstruct**, acredito que valha a pena mencioná-lo pelo desafio de utilizar uma ferramenta diferente da lecionada em aula, o que por fim se provou surpeendentemente bom, a biblioteca é simples de se usar e configurar, mantém um código limpo (na minha opinião até mais que o **JMapper**) e cumpre sua função de mapeamento de classes. Por fim, utilizei a anotação **@ControllerAdvice** para o tratamento de exeções. 

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



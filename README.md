# API de Gerenciamento de Tarefas
Este foi o meu primeiro projeto utilizando Spring Boot: uma API REST para gerenciar tarefas, com CRUD, categorização, validação de dados e um Job agendado que marca tarefas como atrasadas automaticamente.

Fiz esse projeto pra praticar arquitetura em camadas na prática (não só na teoria) e entender melhor como o Spring Boot organiza responsabilidades entre controller, service e repository.

## Tecnologias
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Scheduler (usado para verificação automática de tarefas atrasadas)
- Bean Validation
- Postman (usei pra testar todos os endpoints manualmente)

## Funcionalidades
- CRUD completo de tarefas (criar, listar, buscar por id, atualizar, excluir)
- Marcar tarefa como concluída
- Categorização por prioridade (enum: `BAIXA`, `MEDIA`, `ALTA`) e por categoria (texto livre)
- Busca por título, prioridade e categoria
- Job agendado que verifica tarefas com prazo vencido e marca como atrasadas — esse foi meu primeiro contato com `@Scheduled` no Spring
- Reset automático do status de atraso quando a tarefa é concluída ou o prazo é atualizado para uma data futura
- Endpoint de resumo com estatísticas (total, concluídas, pendentes, atrasadas)
- Validação de dados de entrada com mensagens de erro claras
- Tratamento global de exceções com `@ControllerAdvice`

## Estrutura do projeto
```
src/main/java/com/leticia/api_tarefas/
├── controller/       # Endpoints REST
├── service/          # Regras de negócio
├── repository/       # Acesso a dados (Spring Data JPA)
├── model/            # Entidades e enums
├── scheduler/        # Job agendado de verificação de atrasos
└── exception/        # Tratamento global de exceções
```

## Endpoints principais
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/api/tarefas` | Cria uma nova tarefa |
| GET | `/api/tarefas` | Lista todas as tarefas |
| GET | `/api/tarefas/{id}` | Busca uma tarefa pelo id |
| PUT | `/api/tarefas/{id}` | Atualiza uma tarefa |
| PATCH | `/api/tarefas/{id}/concluir` | Marca uma tarefa como concluída |
| DELETE | `/api/tarefas/{id}` | Remove uma tarefa |
| GET | `/api/tarefas/resumo` | Retorna estatísticas (total, concluídas, pendentes, atrasadas) |

Tem mais rotas de busca (por título, prioridade e categoria) — é possível ver todas no `TarefaController.java`.

## Como rodar localmente
Pré-requisitos: Java JDK, MySQL rodando na máquina, e o Maven (o projeto já inclui o wrapper `mvnw`, então não precisa instalar separado).

1. Clone o repositório:
```bash
git clone https://github.com/leticiavalenttino/task-management-api.git
cd task-management-api
```

2. Crie o banco de dados no MySQL e configure a conexão em `src/main/resources/application.properties` (use o `application.properties.example` como base):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute:
```bash
./mvnw spring-boot:run
```

4. A API fica disponível em `http://localhost:8080/api/tarefas`

## Testando
Testei todos os endpoints manualmente com o Postman: criação e validação com dados inválidos, o Job agendado marcando tarefas atrasadas, e o reset desse status ao concluir ou atualizar o prazo.

## Próximos passos
Ainda quero adicionar testes automatizados com JUnit e talvez documentar os endpoints com Swagger. Autenticação com JWT é outra coisa que pretendo estudar e aplicar em uma próxima versão.
Além disso, penso em usar este projeto como base de um pequeno site que controla o estoque de uma clínica, automatizando pedidos quando necessário e possível.

## Sobre
Projeto feito estudando Java e Spring Boot por conta própria, durante o período de férias da faculdade — ainda não tive esse conteúdo nas disciplinas do curso. Foi minha primeira vez estruturando um projeto Spring Boot do zero, com validação, tratamento de erros e agendamento de tarefas.
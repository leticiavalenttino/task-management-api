
# API de Gerenciamento de Tarefas (Task Management API)

API REST desenvolvida em Java com Spring Boot para gerenciamento de tarefas, com suporte a categorização, priorização, verificação automática de atrasos e geração de resumos estatísticos.

## Tecnologias

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Spring Scheduler** (verificação automática de tarefas atrasadas)
- **Bean Validation** (validação de dados de entrada)
- **Postman** (testes manuais dos endpoints)

## Funcionalidades

- CRUD completo de tarefas (criar, listar, buscar por id, atualizar, excluir)
- Marcação de tarefas como concluídas
- Categorização por **prioridade** (enum: `BAIXA`, `MEDIA`, `ALTA`) e **categoria** (texto livre)
- Busca de tarefas por título, prioridade e categoria
- Verificação automática de tarefas atrasadas via job agendado (`@Scheduled`)
- Reset automático do status de atraso ao concluir uma tarefa ou atualizar seu prazo
- Endpoint de resumo com estatísticas (total, concluídas, pendentes, atrasadas)
- Validação de dados de entrada com mensagens de erro claras
- Tratamento global de exceções (`@ControllerAdvice`)

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

A separação em camadas (Controller → Service → Repository) segue a arquitetura padrão de aplicações Spring Boot, facilitando manutenção e testes.

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

> Confira o `TarefaController.java` para a lista completa de rotas de busca (por título, prioridade e categoria).

## Como rodar o projeto localmente

### Pré-requisitos
- Java JDK instalado
- MySQL instalado e rodando
- Maven (o projeto já inclui o wrapper `mvnw`)

### Passos

1. Clone o repositório:
```bash
git clone <url-do-seu-repositorio>
cd api-tarefas
```

2. Configure o banco de dados no arquivo `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

4. A API estará disponível em:
```
http://localhost:8080/api/tarefas
```

## Testando com Postman

Todos os endpoints foram testados manualmente com Postman, incluindo:
- Criação e validação de tarefas com dados inválidos
- Verificação do job agendado marcando tarefas como atrasadas
- Reset do status de atraso ao concluir tarefas ou atualizar prazos

*(Adicione aqui prints do Postman mostrando requisições e respostas)*

## Próximos passos

- Adicionar testes automatizados (JUnit)
- Documentação com Swagger/OpenAPI
- Autenticação e autorização (JWT)

## Autora

Desenvolvido por Letícia Valentino como projeto de portfólio, aplicando conceitos de arquitetura em camadas, boas práticas REST e agendamento de tarefas com Spring Boot.

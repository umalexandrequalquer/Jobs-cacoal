# Documentação da API Jobs-cacoal

## Introdução
A API Jobs-cacoal fornece endpoints para gerenciar empresas e suas vagas de emprego. Construída com Spring Boot, ela permite listar, criar, atualizar e deletar empresas, bem como gerenciar as vagas associadas a cada empresa. Esta documentação detalha todos os endpoints disponíveis, incluindo métodos HTTP, caminhos, parâmetros, respostas e exemplos.

**Base URL**: `/api/empresas`

---

## Autenticação
A API não requer autenticação.  
(Estado de implementação)

---

## Endpoints

### 1. Listar Empresas
- **Método**: `GET`
- **URL**: `/api/empresas`
- **Descrição**: Retorna uma lista de todas as empresas cadastradas no sistema.
- **Parâmetros**: Nenhum
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
        - Corpo: Lista de objetos `EmpresaDTO`
          ```json
          [
            {
              "name": "Empresa Exemplo",
              "cnpj": "12.345.678/0001-99",
              "email": "contato@empresa.com",
              "phoneNumber": "(11) 99999-9999",
              "address": "Rua Exemplo, 123",
              "vagas": [
                {
                  "titulo": "Desenvolvedor",
                  "descricao": "Vaga para desenvolvedor",
                  "valor": 5000.00
                }
              ]
            }
          ]
          ```  
    - **Erro**:
        - Código de Status: `500 Internal Server Error` (em caso de falha no servidor)

---

### 2. Criar Empresa
- **Método**: `POST`
- **URL**: `/api/empresas`
- **Descrição**: Cria uma nova empresa com os dados fornecidos.
- **Parâmetros**:
    - Corpo da requisição: Objeto `EmpresaCreateDTO`
      ```json
      {
        "name": "Nova Empresa",
        "cnpj": "98.765.432/0001-00",
        "email": "nova@empresa.com",
        "phoneNumber": "(11) 88888-8888",
        "address": "Avenida Nova, 456"
      }
      ```  
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
        - Corpo: Objeto `EmpresaEntity` criado (inclui o `id` gerado)
    - **Erro**:
        - Código de Status: `400 Bad Request` (se os dados forem inválidos, como CNPJ ou email duplicados)

---

### 3. Atualizar Empresa
- **Método**: `PUT`
- **URL**: `/api/empresas/{id}`
- **Descrição**: Atualiza os dados de uma empresa existente identificada pelo `id`.
- **Parâmetros**:
    - `{id}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
    - Corpo da requisição: Objeto `EmpresaUpdateDTO` com os campos a serem atualizados
      ```json
      {
        "name": "Empresa Atualizada",
        "email": "atualizado@empresa.com"
      }
      ```  
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
        - Corpo: Objeto `EmpresaEntity` atualizado
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa não for encontrada)
        - Código de Status: `400 Bad Request` (se os dados forem inválidos)

---

### 4. Buscar Empresa por ID
- **Método**: `GET`
- **URL**: `/api/empresas/{id}`
- **Descrição**: Retorna os detalhes de uma empresa específica com base no seu `id`.
- **Parâmetros**:
    - `{id}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
        - Corpo: Objeto `EmpresaDTOWithID`
          ```json
          {
            "id": "550e8400-e29b-41d4-a716-446655440000",
            "name": "Empresa Exemplo",
            "cnpj": "12.345.678/0001-99",
            "email": "contato@empresa.com",
            "phoneNumber": "(11) 99999-9999",
            "address": "Rua Exemplo, 123",
            "vagas": [
              {
                "titulo": "Desenvolvedor",
                "descricao": "Vaga para desenvolvedor",
                "valor": 5000.00
              }
            ]
          }
          ```  
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa não for encontrada)

---

### 5. Deletar Empresa
- **Método**: `DELETE`
- **URL**: `/api/empresas/{id}`
- **Descrição**: Remove uma empresa do sistema com base no seu `id`.
- **Parâmetros**:
    - `{id}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `204 No Content`
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa não for encontrada)

---

### 6. Criar Vaga para uma Empresa
- **Método**: `POST`
- **URL**: `/api/empresas/{id}/vagas`
- **Descrição**: Cria uma nova vaga associada a uma empresa específica identificada pelo `id`.
- **Parâmetros**:
    - `{id}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
    - Corpo da requisição: Objeto `VagasDTO`
      ```json
      {
        "titulo": "Nova Vaga",
        "descricao": "Descrição da nova vaga",
        "valor": 6000.00
      }
      ```  
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa não for encontrada)
        - Código de Status: `400 Bad Request` (se os dados da vaga forem inválidos, como valor menor que R$ 1.518,00)

---

### 7. Atualizar Vaga
- **Método**: `PUT`
- **URL**: `/api/empresas/{idEmpresa}/vagas/{idVaga}`
- **Descrição**: Atualiza os dados de uma vaga específica associada a uma empresa.
- **Parâmetros**:
    - `{idEmpresa}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
    - `{idVaga}`: UUID da vaga (exemplo: `123e4567-e89b-12d3-a456-426614174000`)
    - Corpo da requisição: Objeto `VagasEntity` com os novos dados
      ```json
      {
        "titulo": "Vaga Atualizada",
        "descricao": "Nova descrição",
        "valor": 7000.00
      }
      ```  
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `200 OK`
        - Corpo: Objeto `VagasEntity` atualizado
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa ou vaga não for encontrada)
        - Código de Status: `400 Bad Request` (se os dados forem inválidos)

---

### 8. Deletar Vaga
- **Método**: `DELETE`
- **URL**: `/api/empresas/{idEmpresa}/vagas/{idVaga}`
- **Descrição**: Remove uma vaga específica associada a uma empresa.
- **Parâmetros**:
    - `{idEmpresa}`: UUID da empresa (exemplo: `550e8400-e29b-41d4-a716-446655440000`)
    - `{idVaga}`: UUID da vaga (exemplo: `123e4567-e89b-12d3-a456-426614174000`)
- **Resposta**:
    - **Sucesso**:
        - Código de Status: `204 No Content`
    - **Erro**:
        - Código de Status: `404 Not Found` (se a empresa ou vaga não for encontrada)

---

## Modelos de Dados

### EmpresaCreateDTO
- `name` (string): Nome da empresa (obrigatório).
- `cnpj` (string): CNPJ da empresa (obrigatório, deve ser válido).
- `email` (string): Email da empresa (obrigatório, formato de email válido).
- `phoneNumber` (string): Número de telefone (opcional).
- `address` (string): Endereço (opcional).

### EmpresaUpdateDTO
- Campos opcionais para atualização: `name`, `cnpj`, `email`, `phoneNumber`, `address`.

### EmpresaDTO
- `name` (string): Nome da empresa.
- `cnpj` (string): CNPJ da empresa.
- `email` (string): Email da empresa.
- `phoneNumber` (string): Número de telefone.
- `address` (string): Endereço.
- `vagas` (lista de `VagasDTO`): Lista de vagas associadas à empresa.

### EmpresaDTOWithID
- `id` (UUID): Identificador único da empresa.
- `name` (string): Nome da empresa.
- `cnpj` (string): CNPJ da empresa.
- `email` (string): Email da empresa.
- `phoneNumber` (string): Número de telefone.
- `address` (string): Endereço.
- `vagas` (lista de `VagasDTO`): Lista de vagas associadas à empresa.

### VagasDTO
- `titulo` (string): Título da vaga (obrigatório).
- `descricao` (string): Descrição da vaga (obrigatório).
- `valor` (float): Valor da vaga (obrigatório, mínimo R$ 1.518,00).

---

## Tratamento de Erros
A API utiliza códigos de status HTTP para indicar o resultado das operações:
- `200 OK`: Operação realizada com sucesso.
- `204 No Content`: Recurso deletado com sucesso.
- `400 Bad Request`: Dados inválidos na requisição (ex.: campos obrigatórios ausentes ou formato incorreto).
- `404 Not Found`: Recurso (empresa ou vaga) não encontrado.
- `500 Internal Server Error`: Erro interno no servidor.

---

Esta documentação cobre todos os endpoints da API Jobs-cacoal com base no código fornecido. Ela pode ser utilizada como referência para integração e testes da API. Caso haja necessidade de mais detalhes (como exemplos adicionais ou regras de validação específicas), entre em contato com o desenvolvedor.# jobs-cacoal


<table>
<tr>
<td>
<a href= "https://www.rockwellautomation.com/pt-br.html"><img src="./docs/img/logo-rockwell.png" alt="Rockwell Automation" border="0" width="70%"></a>
</td>
<td><a href= "https://www.inteli.edu.br/"><img src="./docs/img/logo-inteli.png" alt="Inteli - Instituto de Tecnologia e Liderança" border="0" width="30%"></a>
</td>
</tr>
</table>

# Projeto: *One beer to you*

# Grupo: *1beer2*

# Integrantes:

* [Fabio Piemonte](https://www.linkedin.com/in/fabio-piemonte-823a65211/) - <Fabio.Lopes@sou.inteli.edu.br>
* [Guilherme Lima](https://www.linkedin.com/in/guilherme-novaes-lima/) - <Guilherme.Lima@sou.inteli.edu.br>
* [Henrique Godoy](https://www.linkedin.com/in/henrique-godoy-879138252/) - <Henrique.Godoy@sou.inteli.edu.br>
* [Marcos Teixeira](https://www.linkedin.com/in/marcos-teixeira-37676a24a/) - <Marcos.Teixeira@sou.inteli.edu.br>
* [Mariana Görresen](https://www.linkedin.com/in/mariana-gorresen/) - <mariana.gorresen@sou.inteli.edu.br>
* [Tony Jonas](https://www.linkedin.com/in/tonyjonas/) - <tony.sousa@sou.inteli.edu.br>

# 📝 Descrição

O problema em questão diz respeito à identificação e otimização de rotas dentro de uma fábrica de cerveja com base em um diagrama P&ID (Diagrama de Processo e Instrumentação). O objetivo central é criar e otimizar cenários de rotas entre pontos de origem e destino, considerando diversas limitações, como válvulas, direção das tubulações e tipos de tanques. Isso tem como finalidade primordial aprimorar a eficiência na gestão dos processos industriais.

A solução desenvolvida para esse desafio envolve a criação de cenários de rotas através da aplicação do algoritmo A-Star, utilizando um grafo que representa detalhadamente o P&ID da fábrica. A função principal é minimizar a distância percorrida pelo transporte da cerveja. O algoritmo leva em consideração as distâncias entre os componentes do P&ID, respeitando as restrições impostas pelas válvulas e a direção das tubulações. A implementação do algoritmo foi incorporada ao código do projeto.

Além disso, a solução oferece uma interface de software simplificada e intuitiva, permitindo a visualização das rotas geradas, com destaque para os pontos de partida, chegada e todos os componentes ao longo do trajeto. Foram elaboradas User Stories que atendem às necessidades das personas envolvidas, como engenheiros e técnicos. A solução foi projetada com escalabilidade em mente, sendo capaz de lidar eficazmente com grandes fábricas que possuam uma grande quantidade de tanques e conexões.

# ⚙️ Configuração de desenvolvimento

Para desenvolver em cima do projeto. Será necessário fazer a instalação dos programas abaixo:

- [Git](https://git-scm.com/);
- [Apache Maven](https://maven.apache.org/);
- [OpenJDK 17](https://openjdk.org/);
- [Node Package Manager (NPM)](https://nodejs.org/);

Para clonar, copie e rode o código abaixo no diretório desejado: 

```sh
git clone --depth 1 https://github.com/2023M5T5-Inteli/grupo3.git
```

Antes de rodar o backend, será necessário conectar um banco de dados neo4j com a aplicação. Para isso, altere as credenciais presentes no arquivo [.env](/codigo/backend/onebeertwo/src/main/resources/.env) de acordo com o padrão abaixo:

```env
AURA_URI=[URI do seu banco de dados]
AURA_USER=[Usuário do seu banco de dados]
AURA_PASSWORD=[Senha do usuário do seu banco de dados]

```

Para rodar o backend, copie e rode o código abaixo na pasta raiz do repositório:

### 🐧 Linux / 🍎 Mac

```sh
cd codigo/backend/onebeertwo && mvn spring-boot:run
```

### 🪟  Windows

```ps1
cd codigo\backend\onebeertwo ; mvn spring-boot:run
```

Para rodar o frontend, copie e rode o código abaixo na pasta raiz do repositório:

### 🐧 Linux / 🍎 Mac

```sh
cd codigo/frontend && npm i && npm start
```

### 🪟  Windows

```ps1
cd codigo\frontend ; npm i ; npm start
```

Deixe ambos os serviços rodando para conseguir utilizar e modificar a aplicação.

Use o editor de texto[ ](https://www.vim.org/)mais adequado para o seu projeto para obter melhores resultados.

# Documentação

Os arquivos da documentação deste projeto estão na pasta [/docs](/docs).

# Artigo

Os arquivos do artigo estão na pasta [/artigo](/artigo). Um arquivo gerado no formato PDF deverá ser anexado a cada *release* do projeto.

# Tags

- [x] [SPRINT 1](https://github.com/2023M5T5-Inteli/grupo3/releases/tag/Sprint_1)
  - Entendimento do contexto do problema: modelagem e representação
  - Entendimento de Negócio
  - Entendimento da Experiência do Usuário
- [x] [SPRINT 2](https://github.com/2023M5T5-Inteli/grupo3/releases/tag/Sprint_2)
  - Entendimento do contexto do problema
  - Gerenciamento do Grafo
  - Artigo - versão inicial
  - Repositório do Código
- [x] [SPRINT 3](https://github.com/2023M5T5-Inteli/grupo3/releases/tag/Sprint-3)
  - Back-end da aplicação
  - Front-end da aplicação
  - Repositório de código da aplicação
  - Artigo - Motivação, metodologia e revisão bibliográfica
- [x] [SPRINT 4](https://github.com/2023M5T5-Inteli/grupo3/releases/tag/Sprint_4)
  - Complexidade e corretude do algoritmo
  - Aplicação integrada
  - Repositório de código da aplicação
  - Artigo - Resultados e conclusões
- [x] [SPRINT 5](https://github.com/2023M5T5-Inteli/grupo3/releases/tag/Sprint_5)
  - Refinamento da aplicação
  - Artigo completo
  - Apresentação Final
  - Refinamento e validação dos artefatos de negócio

## 📋 Licença/License

<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1"><p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL">Turing Labs</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName">Inteli, Fábio Piemonte Lopes, Guilherme Novaes Lima, Henrique Rodrigues de Godoy, Marcos Vinicyus Rosa Teixeira, Mariana Brasil Görressen, Tony Jonas dos Santos Sousa</a> is licensed under <a href="https://creativecommons.org/licenses/by/4.0/?ref=chooser-v1" rel="license noopener noreferrer" style="display:inline-block;">Attribution 4.0 International</a>.</p>

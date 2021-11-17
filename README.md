<p align="center"> 💻 Atualizado em 17 de Novembro de 2021 💻</p>

<h1 align="center"> :chess_pawn: Chess Game :chess_pawn: </h1>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/gabrielcoelhox/chess-game-java">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/gabrielcoelhox/chess-game-java">

  <a href="https://github.com/gabrielcoelhox/projeto-base-conhecimento/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/gabrielcoelhox/chess-game-java">
  </a>
</p>

<p align="center">
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-instalacao">Instalação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#%EF%B8%8F-demonstração">Demonstração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licença">Licença</a>
</p>

## 💻 Projeto

Este projeto foi desenvolvido com base no curso *__Java COMPLETO Programação Orientada a Objetos__* do professor [Nélio Alves](https://github.com/acenelio). Esta aplicação consiste em um jogo de Xadrez, que pode ser executado através do terminal. O jogo possui tratamento de erros, programação defensiva, jogadas especiais do xadrez e previsão de movimento das peças.

## :joystick: O Jogo

Peças: Pawn (Peão), Knight (Cavalo), Rook (Torre), Bishop (Bispo), Queen (Rainha) e King (Rei).
|-|

A mecânica do jogo é baseada em linhas (1, 2, 3, 4, 5, 6, 7, 8) e colunas (a, b, c, d, e, f, g, h)

 ![IMG](github/tabuleiro.png)
 
- Para **escolher** a peça desejada, é necessário selecionar _primeiro_ a **coluna** e logo em seguida selecionar a **linha**, exemplo: **d2**
- **Captured pieces:** Armazena as peças capturadas.
- **Turn:** Exibe o turno atual do jogo.
- **Waiting player:** Exibe qual é o jogador a jogar a próxima peça.
- **Source:** Peça no qual o jogador irá jogar.
- **Target:** Local no qual o jogador irá mover a peça.

## 🖼️ Demonstração 

 ![GIF](github/animacao.gif)

## Movimentos Especiais

<b>ROQUE</b>

O Roque é uma jogada especial que envolve a movimentação de duas peças em um único lance, o rei e uma das torres. O objetivo da jogada é proteger o rei, tirando-o do centro.

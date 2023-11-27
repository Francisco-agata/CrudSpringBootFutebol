<h1 align="center"> Crud sobre Futebol utilizando spring boot e Postman</h1>
<h2>Lista de Partidas de Futebol</h2>
Objetivo: desenvolver uma API para salvar partidas de futebol.
#Operações básicas:
- cadastrar uma partida, contendo no mínimo o nome dos clubes, o resultado da partida, a data e a hora da partida e o nome do estádio; :white_check_mark:
 atualizar os dados de uma partida;  :white_check_mark:
- remover uma partida do cadastro; :white_check_mark:
 Buscas
partidas que terminaram em uma goleada (3 ou mais gols de diferença para um dos clubes); :white_check_mark:
partidas que terminaram sem gols para nenhum dos clubes;:white_check_mark:
todas as partidas de um clube específico, podendo filtrar as partidas onde este clube atuou como mandante ou como visitante;:white_check_mark:
todas as partidas de um estádio específico; :white_check_mark:
  Validações:   A aplicação não deverá permitir o cadastro ou a atualização...
de uma partida antes das 8h ou após às 22h; :white_check_mark:
de mais de uma partida em um mesmo estádio no mesmo dia;:white_check_mark:
de uma partida sem conter o nome dos clubes, a data e a hora da partida e o nome do estádio;:white_check_mark:
de uma partida sem conter o resultado, ou com valores negativos no resultado :white_check_mark:

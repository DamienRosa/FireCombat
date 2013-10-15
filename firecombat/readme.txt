Ambiente:
 - Wind
 - Forest Density
 - Tamanho: 100x100 

 - Temperature
 - Water Fluxe
 - Distância do jacto ?????

 Fogo:
 - Vai ter uma densidade. Centro de massa do fogo mais denso que o exterior




Agent:
 - FireMan
  - Chef -> Aprendizagem com o comportamento do Ambiente (4 agentes, chefDensity, chefQuadrant, chefFlanks e chefFront)
    - Terá de ter uma especie de memoria para guardar a informação da posição dos agentSoldier para os poder encaminhar para os locais certos
  - Soldier (velocidade do bombeiro) : visão p.e. 12 quadriculas à frente (ver fig. 9, relatório pyroFighter)

Action of Combat:
 - Pobilidade de desenhar um quadrado à volta do fogo e determinar qual das linhas está mais a favor para ser combatido o fogo, essa linha pode ser determinada se o vento estiver a favor dos bombeiros.
 - Implementar uma base se bombeiros e sempre que o fogo estiver extinto, estes retornariam à base.
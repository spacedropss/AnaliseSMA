'''
Generalized behavior for random walking, one grid cell at a time.
'''

import random

from mesa import Agent


class Walker(Agent):



    grid = None
    x = None
    y = None
    moore = False

    def __init__(self, unique_id, pos, model, moore=False):
        '''
        grid: O objeto MultiGrid no qual o agente reside.
         x: a coordenada x atual do agente
         y: a coordenada y atual do agente
         moore: Se Verdadeiro, pode se mover em todas as 8 direções.
                 Caso contrário, apenas para cima, baixo, esquerda, direita.
        '''
        super().__init__(unique_id, model)
        self.pos = pos
        self.moore = moore

    def random_move(self):
        '''
        Pise uma célula em qualquer direção permitida.
        '''
        # Pick the next cell from the adjacent cells.
        next_moves = self.model.grid.get_neighborhood(self.pos, self.moore, True)
        next_move = random.choice(next_moves)
        # Now move:
        self.model.grid.move_agent(self, next_move)



    def pig_move(self):
        '''
        Pise uma célula em qualquer direção permitida.
        '''
        # Pick the next cell from the adjacent cells.
        next_moves = self.model.grid.get_neighborhood(self.pos, self.moore, True)
        next_move = random.choice(next_moves)
        # Now move:
        self.model.grid.move_agent(self, next_move)

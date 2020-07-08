import random

from mesa import Agent

from examples.pegueoporco.wolf_sheep.random_walk import Walker


class Pig(Walker):



    def __init__(self, unique_id, pos, model, moore):
        super().__init__(unique_id, pos, model, moore=moore)

    def step(self):
        '''
        A model step. Move, then eat grass and reproduce.
        '''
        self.random_move()


class Hunter(Walker):



    def __init__(self, unique_id, pos, model, moore):
        super().__init__(unique_id, pos, model, moore=moore)

    def step(self):
        self.random_move()
        x, y = self.pos
        this_cell = self.model.grid.get_cell_list_contents([self.pos])
        sheep = [obj for obj in this_cell if isinstance(obj, Pig)]

        if len(sheep) > 0:
            sheep_to_eat = random.choice(sheep)

            # Kill the sheep
            self.model.grid._remove_agent(self.pos, sheep_to_eat)
            self.model.schedule.remove(sheep_to_eat)

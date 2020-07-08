'''
Wolf-Sheep Predation Model
================================

Replication of the model found in NetLogo:
    Wilensky, U. (1997). NetLogo Wolf Sheep Predation model.
    http://ccl.northwestern.edu/netlogo/models/WolfSheepPredation.
    Center for Connected Learning and Computer-Based Modeling,
    Northwestern University, Evanston, IL.
'''

import random

from mesa import Model
from mesa.space import MultiGrid
from mesa.datacollection import DataCollector

from examples.pegueoporco.wolf_sheep.agents import Pig, Hunter
from examples.pegueoporco.wolf_sheep.schedule import RandomActivationByBreed


class WolfSheep(Model):
    '''
    Wolf-Sheep Predation Model
    '''

    initial_pigs = 100
    initial_hunters = 50
    height_grid = 5
    width_grid = 5
    limit_steps = 0

    verbose = False  # Print-monitoring

    description = 'A model for simulating wolf and sheep (predator-prey) ecosystem modelling.'

    def __init__(self, initial_pigs=100, initial_hunters=50, height_grid=5, width_grid=5, limit_steps =0):

        super().__init__()
        # Set parameters
        self.height = height_grid
        self.width = width_grid
        self.initial_pigs = initial_pigs
        self.initial_hunters = initial_hunters
        self.limit_steps = limit_steps
        self.schedule = RandomActivationByBreed(self)
        #Torus permite sair de um lado e entrar do outro
        self.grid = MultiGrid(self.height, self.width, torus=False)
        self.datacollector = DataCollector(
            {"Wolves": lambda m: m.schedule.get_breed_count(Hunter),
             "Sheep": lambda m: m.schedule.get_breed_count(Pig)})

        # Create sheep:
        for i in range(self.initial_pigs):
            x = random.randrange(self.width_grid)
            y = random.randrange(self.height_grid)

            sheep = Pig(self.next_id(), (x, y), self, False)
            self.grid.place_agent(sheep, (x, y))
            self.schedule.add(sheep)

        # Create wolves
        for i in range(self.initial_hunters):
            x = random.randrange(self.width_grid)
            y = random.randrange(self.height_grid)
            wolf = Hunter(self.next_id(), (x, y), self, False)
            self.grid.place_agent(wolf, (x, y))
            self.schedule.add(wolf)

        self.running = True
        self.datacollector.collect(self)

    def step(self):
        self.schedule.step()
        # collect data
        self.datacollector.collect(self)
        if self.verbose:
            print([self.schedule.time,
                   self.schedule.get_breed_count(Hunter),
                   self.schedule.get_breed_count(Pig)])

    def run_model(self, step_count=limit_steps):

        if self.verbose:
            print('Initial number hunter: ',
                  self.schedule.get_breed_count(Hunter))
            print('Initial number pig: ',
                  self.schedule.get_breed_count(Pig))

        for i in range(step_count):
            self.step()

        if self.verbose:
            print('')
            print('Final number hunter: ',
                  self.schedule.get_breed_count(Hunter))
            print('Final number pig: ',
                  self.schedule.get_breed_count(Pig))

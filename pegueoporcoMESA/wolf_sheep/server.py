from mesa.visualization.ModularVisualization import ModularServer
from mesa.visualization.modules import CanvasGrid, ChartModule
from mesa.visualization.UserParam import UserSettableParameter

from examples.pegueoporco.wolf_sheep.agents import Hunter, Pig
from examples.pegueoporco.wolf_sheep.model import WolfSheep


def wolf_sheep_portrayal(agent):
    if agent is None:
        return

    portrayal = {}

    if type(agent) is Pig:
        portrayal["Shape"] = "wolf_sheep/resources/sheep.png"
        #https://icons8.com/web-app/433/sheep
        portrayal["scale"] = 0.9
        portrayal["Layer"] = 1

    elif type(agent) is Hunter:
        portrayal["Shape"] = "wolf_sheep/resources/wolf.png"
        #https://icons8.com/web-app/36821/German-Shepherd
        portrayal["scale"] = 0.9
        portrayal["Layer"] = 2
        #portrayal["text"] = round(agent.energy, 1)
        portrayal["text_color"] = "White"

    '''
    elif type(agent) is GrassPatch:
        if agent.fully_grown:
            portrayal["Color"] = ["#00FF00", "#00CC00", "#009900"]
        else:
            portrayal["Color"] = ["#84e184", "#adebad", "#d6f5d6"]
        portrayal["Shape"] = "rect"
        portrayal["Filled"] = "true"
        portrayal["Layer"] = 0
        portrayal["w"] = 1
        portrayal["h"] = 1
    '''
    return portrayal

#define tamanho da matriz
height_grid = 5
width_grid = 5
chart_element = ChartModule([{"Label": "Wolves", "Color": "#AA0000"},
                             {"Label": "Sheep", "Color": "#666666"}])

model_params = {"initial_pigs": UserSettableParameter('slider', 'População de Porcos', 1, 1, 100),
                "initial_hunters": UserSettableParameter('slider', 'População de Caçadores', 2, 1, 100),
                "limit_steps": UserSettableParameter('slider', 'Limite de Movimentos', 3, 3, 300),
                "height_grid": 5,
                "width_grid": 5
                }
canvas_element = CanvasGrid(wolf_sheep_portrayal, height_grid, width_grid, 500, 500)

server = ModularServer(WolfSheep, [canvas_element, chart_element], "Pegue o Porco", model_params)
server.port = 8521

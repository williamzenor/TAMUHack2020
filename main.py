'''
Project: TAMUhack 2020 Project
Function: Web App Creator that takes in Australian weather and wildfire data and sends it to a mobile app
Team Members: Danielle, William, Ahsan, & Peter
Dates Worked On: 1/25 - 1/26
'''
###################
##### Imports #####
import csv
from flask import Flask, request, jsonify

app = Flask(__name__)


################################
##### Function Definitions #####
def location_check(lon, lat):  # Checks location to make sure that the user is in Australia
    loc_check = True
    if (lon < 110.0) or (lon > 155.0):
        loc_check = False
    elif (lat < 10.0) or (lat > 45.0):
        loc_check = False
    return loc_check


@app.route('/location/<lon>/<lat>')  # <> Enclosed values indicate parameters to be specified
def send_risk_data(lon, lat):
    """
    Purpose: To intake longitude and latitude to use as location data
    Parameters: Takes in Longitude and Latitude
    :return: str
    """
    lon, lat = float(lon), float(lat)
    coords = [lon * (10.0 ** (-5)), lat * (10.0 ** (-5))]
    print(coords);
    if location_check(coords[0], coords[1]) == False:
        output = ("Outside Australia", "No need to worry")
    else:
        output = (str(coords[0]), str(coords[1]))
    return (output[0] + ", " + output[1])


################################
##### Web App Routing Info #####
# running web app in local machine
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)  # Runs on port 5001 (Pycharm occupies port 5000, so creates auto error)

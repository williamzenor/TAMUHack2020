'''
Project: TAMUhack 2020 Project
Function: Web App Creator that takes in Australian weather and wildfire data and sends it to a mobile app
Team Members: Danielle, William, Ahsan, & Peter
Dates Worked On: 1/25 - 1/26
'''
###################
##### Imports #####
import requests, csv
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
def csv_locB_reader(csvfile='Fire Data.csv'):
    b_locations_list = []
    count = 0
    with open(csvfile, 'r') as locdata:
        data_reader = csv.reader(locdata, delimiter=',')
        for location in data_reader:
            for index in location:
                count += 1
                if count == 2:
                    b_locations_list.append(index)
                elif count >= 8:
                    count = 0
    return(b_locations_list)
def api_call(lon, lat):
    lat, lon = float(lat), float(lon)
    apicall = "http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&APPID=f734a75153cb0ee0aa91f5bdb4a231d7"% (lat, lon)
    print(apicall)
    response_val = requests.get(apicall)
    return response_val


@app.route('/location/<lon>/<lat>')  # <> Enclosed values indicate parameters to be specified
def send_risk_data(lon, lat):
    """
    Purpose: To intake longitude and latitude to use as location data
    Parameters: Takes in Longitude and Latitude
    :return: str
    """
    lon, lat = float(lon), float(lat)
    coords = [lon * (10.0 ** (-5)), lat * (10.0 ** (-5))]
    response = api_call(coords[0], coords[1])
    if location_check(coords[0], coords[1]) == False:
        output = ("Outside Australia", "No need to worry")
    output = response
    return response.content


################################
##### Web App Routing Info #####
# running web app in local machine
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)  # Runs on port 5006 (Pycharm occupies port 5000, so creates auto error)
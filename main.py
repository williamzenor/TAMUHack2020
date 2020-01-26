from flask import Flask, request, jsonify
app = Flask(__name__)

def location_check(lon, lat):
    loc_check = True
    if (lon < 110) or (lon > 155):
        loc_check = False
    if (lat < 10) or (lat > 45):
        loc_check = False
    return loc_check

@app.route('/location/<lon>/<lat>') # <> Enclosed values indicate parameters to be specified
def send_risk_data(lon, lat):
    """
    Purpose: To intake longitude and latitude to use as location data
    Parameters: Takes in Longitude and Latitude
    :return: str
    """
    lon = float(lon)
    lat = float(lat)
    loc_check = location_check(lon, lat)
    if loc_check == True:
        output = "Inside Australia"
    elif loc_check == False:
        output = "Outside Australia"
    return output

# running web app in local machine
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001) #Runs on port 5001 (Pycharm occupies port 5000, so creates auto error)
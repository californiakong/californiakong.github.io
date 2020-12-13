#!/usr/bin/env python

import time
import json
import datetime
from grovepi import *
import grovepi
from time import sleep
from math import isnan

# Connect Green LED light to port D5
green_led = 5
# Connect BLue LED light to port D6
blue_led = 6
# Connect Red LED light to port D7
red_led = 7

dht_sensor_port = 4 # Connect the DHt sensor to port 4
dht_sensor_type = 0 # Use 0 for the blue-colored sensor and 1 for the white-colored sensor

# Initializes global variables
threshold = 50
isDaytime = 0
day_temp_stats = []
night_temp_stats = []
day_hum_stats = []
night_hum_stats = []
json_data = []

grovepi.pinMode(green_led,"OUTPUT")
grovepi.pinMode(blue_led,"OUTPUT")
grovepi.pinMode(red_led,"OUTPUT")

# Function to calculate weather stats
def calc_stats(stats, cycle):
    # Initializes local variables
    filename = ""
    stats_data = []
    total = 0
    avg = 0
    hi = -180
    lo = 180
    
    for value in stats:
        total = total + value
        if value > hi:
            hi = value
        if value < lo:
            lo = value
    avg = total / len(stats)
    avg = round(avg, 1)
    # Creates JSON obejct in memory for stats output
    stats_data.append([cycle, "Average:", avg, cycle, "Low:", lo, cycle, "High:", hi])
    stats_object = json.dumps(stats_data, indent = len(stats_data))
    filename = cycle + ".json"
    
    # Writes stats to json file
    with open(filename, "w") as outfile:
        outfile.write(stats_object)
            
def light_control():
    # Controls lights as output of temperature and humdity data
    if (temp < 0):
        grovepi.digitalWrite(red_led, 0)
        grovepi.digitalWrite(green_led, 1)
        grovepi.digitalWrite(blue_led, 1)
        print("Arctic Temperatures")
            
    if (temp > 0 and temp < 32):
        grovepi.digitalWrite(red_led, 0)
        grovepi.digitalWrite(green_led, 0)
        grovepi.digitalWrite(blue_led, 1)
        print("Below Freezing")
                
            
    if (temp > 32 and temp < 60):
        grovepi.digitalWrite(red_led, 1)
        grovepi.digitalWrite(green_led, 0)
        grovepi.digitalWrite(blue_led, 1)
        print("On the cooler side")
                
            
    if (temp > 60 and temp < 85 and hum < 80):
        grovepi.digitalWrite(green_led, 1)
        grovepi.digitalWrite(blue_led, 0)
        grovepi.digitalWrite(red_led, 0)
        print("Nice and Comfortable Weather")

    if (temp > 85 and temp < 95 and hum < 80):
        grovepi.digitalWrite(blue_led, 0)
        grovepi.digitalWrite(green_led, 1)
        grovepi.digitalWrite(red_led, 1)
        print("Comfortably hot")

    if (temp > 95 and temp < 105):
        grovepi.digitalWrite(red_led, 1)
        grovepi.digitalWrite(green_led, 0)
        grovepi.digitalWrite(blue_led, 0)
        print("Currently Experiencing Heatwave")
                
    if (temp > 105):
        grovepi.digitalWrite(red_led, 1)
        grovepi.digitalWrite(green_led, 0)
        grovepi.digitalWrite(blue_led, 0)
        print("Maximum Heat!")

    if (hum > 80):
        print("Humidity Level: Rainforest")

while True:
    try:
        # Initializes time and date
        now = datetime.datetime.now()
        print("\nCurrent Time", now)
        
        # Sets whether daytime or nightime
        if now.hour >= 5 and now.hour <= 18:
            print("Daytime")
            isDaytime = 1
        else:
            print("Nighttime")
            isDaytime = 0
        
        # If it is daytime execute day temperature station code.
        if (isDaytime == 1):
            # If night stats exist then run calculation func
            if (len(night_temp_stats) > 0):
                print("Nightime Stats Loop Ran")
                calc_stats(night_temp_stats, "Night Temp")
                calc_stats(night_hum_stats, "Night Humidity")
                night_temp_stats = []
                night_hum_stats = []
            
            # Get the temperature and Humidity from the DHT sensor
            [ temp,hum ] = dht(dht_sensor_port,dht_sensor_type)
            # Converts temperature reading from celsius to fahrenheit
            temp = (temp * 9/5) + 32
            
            # Check if we have nans. If so, then raise a type error exception
            if isnan(temp) is True or isnan(hum) is True:
                raise TypeError('nan error')
            
            # Creates dictionary for storing JSON output data. Updated temperature and humidity information will be appended to list.
            day_temp_stats.append(temp)
            day_hum_stats.append(hum)
            json_data.append(["Temperature:", temp, "Humidity:", hum])
            print("Temperature:", temp, "Humidity:", hum)

            # Creates JSON obejct in memory consisting of JSON output data
            json_object = json.dumps(json_data, indent = len(json_data))
            # Writes JSON data to file. File consists of current temperature and humidity readings.
            with open("data.json", "w") as outfile:
                outfile.write(json_object)

            light_control()
                
        # Night routine
        elif (isDaytime == 0):
            # If day stats exist then run calculation func
            if (len(day_temp_stats) > 0):
                print("Daytime stats loop ran")
                calc_stats(day_temp_stats, "Day Temp")
                calc_stats(day_hum_stats, "Day Humidity")
                day_temp_stats = []
                day_hum_stats = []
                
            # Get the temperature and Humidity from the DHT sensor
            [ temp,hum ] = dht(dht_sensor_port,dht_sensor_type)
            # Converts temperature reading from celsius to fahrenheit
            temp = (temp * 9/5) + 32
            
            # Check if we have nans. If so, then raise a type error exception
            if isnan(temp) is True or isnan(hum) is True:
                raise TypeError('nan error')
            
            # Creates dictionary for storing JSON output data. Updated temperature and humidity information will be appended to list.
            night_temp_stats.append(temp)
            night_hum_stats.append(hum)
            json_data.append(["Temperature:", temp, "Humidity:", hum])
            print("Temperature:", temp, "Humidity:", hum)

            # Creates JSON obejct in memory consisting of JSON output data
            json_object = json.dumps(json_data, indent = len(json_data))
            # Writes JSON data to file. File consists of current temperature and humidity readings.
            with open("data.json", "w") as outfile:
                outfile.write(json_object)

            light_control()
        
        else:
            grovepi.digitalWrite(green_led, 0)
            grovepi.digitalWrite(blue_led, 0)
            grovepi.digitalWrite(red_led, 0)
                
    except IOError:
        print ("Error")

    # Wait 15 minutes before checking temperature again
    sleep(900.0)
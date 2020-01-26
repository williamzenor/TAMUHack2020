from bs4 import BeautifulSoup
import requests
import html5lib
import csv
import time

def timer(n):
    time.sleep(900)


URL = "https://www.rfs.nsw.gov.au/fire-information/fires-near-me"
r = requests.get(URL)
#print(r.content)

soup = BeautifulSoup(r.content, 'html5lib')
print(soup.prettify())


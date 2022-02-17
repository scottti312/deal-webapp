from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import json
import sys
import requests
import re

options = Options()
options.add_argument("--headless")

PATH = "python/chromedriver.exe"
driver = webdriver.Chrome(PATH, options=options)

def search(URL):
    driver.get(URL)
    
    target_json = driver.find_element(By.XPATH, "//script[@type='application/ld+json']").get_attribute("innerHTML")
    json_contents = json.loads(target_json)
    img = driver.find_element(By.XPATH, "//div[@class='slide--active']//img")

    print(json_contents["@graph"][0]["name"])
    print("$" + json_contents['@graph'][0]["offers"]["price"])
    print(img.get_attribute("src"))

# def find(query):
#     URL = 'https://www.target.com/s?searchTerm=' + query
#     driver.get(URL)
#     productlink = driver.find_element(By.XPATH, "//a[@class='Link__StyledLink-sc-4b9qcv-0 stylesStyledTitleLink-sc-h3r0um-1 iBIqkb eQFZgH h-display-block h-text-bold h-text-bs']")

#     return productlink

    
# url = sys.argv[1]
# url = "https://www.target.com/p/no7-advanced-retinol-1-5-complex-night-concentrate-1-fl-oz/-/A-79646620"
# search('https://www.target.com/p/dell-7480-laptop-core-i5-6300u-2-4ghz-16gb-512gb-m-2-sata-14in-fhd-windows-10-pro-64bit-webcam-manufacturer-refurbished/-/A-82599395#lnk=sametab')
# print(find('dell+laptop'))
# driver.quit()

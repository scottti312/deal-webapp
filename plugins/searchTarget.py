from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import json
import sys
import re

def search(URL):
    options = Options()
    options.add_argument("--headless")

    PATH = "C:\Program Files (x86)\chromedriver.exe"
    driver = webdriver.Chrome(PATH, options=options)
    driver.get(URL)

    target_json = driver.find_element_by_xpath("//script[@type='application/ld+json']").get_attribute("innerHTML")
    json_contents = json.loads(target_json)

    print(json_contents["@graph"][0]["name"])
    print("$" + json_contents['@graph'][0]["offers"]["price"])

# url = sys.argv[1]
url = "https://www.target.com/p/dell-latitude-3189-pentium-n4200-1-1ghz-4gb-128gb-ssd-11-6in-hd-touch-screen-window-10-pro-64bit-webcam-manufacturer-refurbished/-/A-83685152#lnk=sametab";
search(url)



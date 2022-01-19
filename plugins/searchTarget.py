from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
import json
import sys

options = Options()
options.add_argument("--headless")

PATH = "C:\Program Files (x86)\chromedriver.exe"
driver = webdriver.Chrome(PATH, options=options)

def search(URL):
    driver.get(URL)
    
    target_json = driver.find_element(By.XPATH, "//script[@type='application/ld+json']").get_attribute("innerHTML")
    json_contents = json.loads(target_json)
    img = driver.find_element(By.XPATH, "//div[@class='slide--active']//img")

    print(json_contents["@graph"][0]["name"])
    print("$" + json_contents['@graph'][0]["offers"]["price"])
    print(img.get_attribute("src"))

    
url = sys.argv[1]
# url = "https://www.target.com/p/no7-advanced-retinol-1-5-complex-night-concentrate-1-fl-oz/-/A-79646620"
search(url)
driver.quit()

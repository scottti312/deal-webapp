from selenium import webdriver
from selenium.webdriver.chrome.options import Options
import json


def search(URL):
    options = Options()
    options.add_argument("--headless")

    PATH = "C:\Program Files (x86)\chromedriver.exe"
    driver = webdriver.Chrome(PATH, options=options)
    driver.get(URL)

    target_json = driver.find_element_by_xpath("//script[@type='application/ld+json']").get_attribute("innerHTML")
    json_contents = json.loads(target_json)

    print("Title:", json_contents["@graph"][0]["name"])
    print("Price:", "$" + json_contents['@graph'][0]["offers"]["price"])


url = "https://www.target.com/p/hp-14-34-chromebook-laptop-with-chrome-os-intel-processor-4gb-ram-32gb-flash-storage-teal-14a-na0012tg/-/A-82569454#lnk=sametab"
search(url)

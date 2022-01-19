from bs4 import BeautifulSoup
import requests
import sys
import re

def search(URL):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})
    
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    title = soup.find("h1", attrs={"class": "product-title"})
    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)


    price_code = soup.findAll("li", attrs={"class": "price-current"})
    pricea = []
    pricea.append(price_code[0].find("strong").string)
    pricea.append(price_code[0].find("sup").string)
    price = '$'
    price += ''.join(pricea)
    print(price)


def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    URL = "https://www.newegg.com/p/pl?d=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    # number = 0
    for link in soup.find_all('a', attrs={'href': re.compile("-_-Product")}):
        # if 'amazon' in link:
        links.append(link.get('href'))
        # number += 1
    resultlink = links[0]
    return resultlink


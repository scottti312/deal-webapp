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

    img = soup.find("img", attrs={"class": "product-view-img-original"})
    print(img.get("src"))


def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})

    URL = "https://www.newegg.com/p/pl?d=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    for link in soup.find_all('a', attrs={'href': re.compile("-_-Product")}):
        links.append(link.get('href'))
    resultlink = links[0]
    return resultlink

if '.com' in sys.argv[1]:
    search(sys.argv[1])
else:
    print(find(sys.argv[1]))
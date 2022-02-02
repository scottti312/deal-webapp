from bs4 import BeautifulSoup
import requests
import sys
import re
 
def search(URL):
    HEADERS = ({'User-Agent':
                    # 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0',
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})
 
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    title = soup.find("span", attrs={"id": 'productTitle'})
    title_value = title.string
    title_string = title_value.strip().replace(',', '')
    print(title_string)
 
    price_code = soup.findAll("span", attrs={"class": "a-price a-text-price a-size-medium apexPriceToPay"})
    
    pricea = []
    for p in price_code:
        pricea.append(p.find("span", attrs={"class": "a-offscreen"}))
    if not pricea:
        alt_price = soup.find("span", attrs={"class": "a-offscreen"})
        print(alt_price.string)
    if pricea:
        price = pricea[0]
        price_string = price.string
        print(price.string)

    # return price_string, title_string

# This url will contain the product search (amazon.com/this+product+search)
def find(query):
    HEADERS = ({'User-Agent':
                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.137 Safari/537.36',
                    'Accept-Language': 'en-US, en;q=0.5'})
    
    URL = "https://www.amazon.com/s?k=" + query
    webpage = requests.get(URL, headers=HEADERS)
    soup = BeautifulSoup(webpage.content, "lxml")

    links = []
    # number = 0
    for link in soup.find_all('a', attrs={'href': re.compile("(^(?!.*http).*)qid=")}):
        print(link)
        # links.append(link.get('href'))
        # number += 1
    # resultlink = "https://www.amazon.com" + links[0]
    # return resultlink

find('wh-1000xm4')